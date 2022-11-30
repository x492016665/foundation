package com.pf.controller.mini;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.hutool.core.util.StrUtil;
import com.pf.dao.entity.Account;
import com.pf.dao.entity.WxUser;
import com.pf.dao.enums.AccountStatus;
import com.pf.pojo.dto.session.MiniSessionUser;
import com.pf.pojo.dto.system.MiniLoginDTO;
import com.pf.pojo.dto.system.SignUpDTO;
import com.pf.service.*;
import com.pf.service.wrapper.CaptchaService;
import com.pf.utils.AESUtils;
import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Optional;

/**
 * @author yk_xing
 */
@Slf4j
@RestController
@Api(tags = "小程序登录接口")
@RequestMapping("${system.prefix.namespace2}/login")
public class MiniLoginController {

    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegePointService privilegePointService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private CompanyService companyService;

    /**
     * @param code 临时登录凭证
     * @return 手机号
     */
    private String getPhoneNumber(String code) {
        try {
            WxMaUserService wxMaUserService = wxMaService.getUserService();
            WxMaPhoneNumberInfo wxMaPhoneNumberInfo = wxMaUserService.getNewPhoneNoInfo(code);
            return wxMaPhoneNumberInfo.getPhoneNumber();
        } catch (WxErrorException wxe) {
            log.error("获取手机号异常[{}]", code, wxe);
            throw new SystemException(StatusCode.MINI_GET_TEL_ERROR, code);
        }
    }

    @ApiOperation("获取电话号码")
    @GetMapping("/phoneNumber")
    public SignUpDTO phoneNumber(String code) {
        String tel = getPhoneNumber(code);
        String telCode = AESUtils.encode(tel);
        SignUpDTO signUp = new SignUpDTO();
        signUp.setTel(tel);
        signUp.setCode(telCode);
        return signUp;
    }

    @ApiOperation("小程序注册")
    @PutMapping("/signUp")
    public void signUp(@RequestBody SignUpDTO signUp) {
        String data = signUp.getCode();
        if (StrUtil.isEmpty(data)) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        String tel = signUp.getTel();
        String telCode = AESUtils.decode(data);
        if (!StrUtil.equals(tel, telCode)) {
            throw new SystemException(StatusCode.SYSTEM_ARGUMENT_ERROR);
        }
        Account account = new Account();
        account.setLoginName(signUp.getTel());
        boolean loginNameDuplicate = accountService.checkLoginName(account);
        if (loginNameDuplicate) {
            throw new SystemException(StatusCode.USER_LOGIN_NAME_EXIST, signUp.getTel());
        }
        customerService.signUp(signUp);
    }

    @ApiOperation("小程序登录")
    @PostMapping("/signedIn")
    public MiniSessionUser signIn(@RequestBody MiniLoginDTO login, @ApiIgnore HttpSession session) throws WxErrorException {
        String code = login.getCode();
        String pwd = login.getPwd();
        if (StrUtil.isEmpty(code) && StrUtil.isEmpty(pwd)) {
            throw new SystemException(StatusCode.MINI_LOGIN_DATA_ERROR);
        }
        Account account = null;
        WxMaJscode2SessionResult sessionResult = null;
        if (StrUtil.isNotEmpty(code)) {
            String telNum = getPhoneNumber(code);
            String jsCode = login.getJsCode();
            try {
                sessionResult = this.getMaSessionResult(jsCode);
            } catch (WxErrorException wxe) {
                log.error("获取用户信息异常[jsCode={}][code={}]", jsCode, code, wxe);
                throw new SystemException(StatusCode.MINI_LOGIN_ERROR);
            }
            account = accountService.getByLoginName(telNum);
            checkAccount(login, account);
        } else if (StrUtil.isNotEmpty(pwd)) {
            //校验验证码
            captchaService.validataCaptcha(login.getCaptcha(), session);
            String loginName = login.getLoginName();
            account = accountService.getByLoginName(loginName);
            checkAccount(login, account);
            String plainPwd = login.getPwd();
            if (!accountService.verifyPwd(plainPwd, account.getLoginPwd())) {
                log.info("用户密码验证失败[{}][{}]", login, account);
                throw new SystemException(StatusCode.MINI_LOGIN_ERROR);
            }
        }
        MiniSessionUser sessionUser = sessionService.getByAccount(account);
        if (sessionResult != null) {
            sessionUser.setSessionKey(sessionResult.getSessionKey());
            sessionUser.setOpenid(sessionResult.getOpenid());
            sessionUser.setUnionid(sessionResult.getUnionid());
            bindWxUser(sessionUser);
        }
        sessionService.sessionUser(session, sessionUser);
        sessionUser.setSessionId(encode(session.getId()));
        return sessionUser;
    }

    private void checkAccount(MiniLoginDTO login, Account account) {
        if (account == null) {
            log.info("账户不存在[{}]", login);
            throw new SystemException(StatusCode.MINI_LOGIN_ACCOUNT_EMPTY);
        }
        AccountStatus accountStatus = AccountStatus.getByCode(account.getAccountStatus());
        if (AccountStatus.DISABLED == accountStatus) {
            log.info("账户缺失[{}]", login);
            throw new SystemException(StatusCode.MINI_LOGIN_ACCOUNT_DISABLE);
        }
    }

    private WxMaJscode2SessionResult getMaSessionResult(String jsCode) throws WxErrorException {
        WxMaUserService wxMaUserService = wxMaService.getUserService();
        WxMaJscode2SessionResult sessionResult = wxMaUserService.getSessionInfo(jsCode);
        log.info("微信鉴权[jsCode={}]=>[WxMaJscode2SessionResult={}]", jsCode, sessionResult);
        return sessionResult;
    }

    private void bindWxUser(MiniSessionUser sessionUser) {
        if (StrUtil.isEmpty(sessionUser.getOpenid())) {
            return;
        }
        WxUser wxUser = new WxUser();
        wxUser.setMaOpenId(sessionUser.getOpenid());
        wxUser.setUnionId(sessionUser.getUnionid());
        wxUser.setAccountId(sessionUser.getAccountId());
        wxUser.setTel(sessionUser.getTel());
        wxUserService.bindWxUser(wxUser);
    }

    private String encode(String sessionId) {
        return new String(Base64.getEncoder().encode(sessionId.getBytes()));
    }

    @GetMapping("/getCurrentLogin")
    @ApiOperation("获取登录人信息")
    public MiniSessionUser getCurrentLogin(@ApiIgnore HttpSession session) {
        return sessionService.getSessionUser(session, MiniSessionUser.class);
    }

    @GetMapping("/signedOut")
    @ApiOperation("退出登录")
    public void signedOut(@ApiIgnore HttpSession session) {
        sessionService.sessionExpired(session);
    }

    @GetMapping("/getId")
    @ApiOperation(value = "获取会话id", notes = "会话id(cookie的key为SESSION)")
    public Optional<String> getSessionId(@ApiIgnore HttpSession session) {
        return Optional.of(encode(session.getId()));
    }

    @GetMapping("/imageCaptcha")
    @ApiOperation("图形验证码")
    public Optional<String> imageCaptcha(@ApiIgnore HttpSession session) {
        return Optional.of(captchaService.getImageCaptcha(session));
    }
}
