package com.pf.controller.web;

import com.pf.dao.entity.Account;
import com.pf.dao.enums.AccountClass;
import com.pf.dao.enums.AccountStatus;
import com.pf.pojo.dto.session.SessionUser;
import com.pf.pojo.dto.session.WebSessionUser;
import com.pf.pojo.dto.system.LoginDTO;
import com.pf.service.*;
import com.pf.service.wrapper.CaptchaService;
import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author yk_xing
 */
@Slf4j
@RestController
@Api(tags = "登录接口")
@RequestMapping("${system.prefix.namespace1}/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PrivilegePointService privilegePointService;
    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/signedIn")
    @ApiOperation("登录接口")
    public void signedIn(@RequestBody LoginDTO login, @ApiIgnore HttpSession session) {
        //校验验证码
        captchaService.validataCaptcha(login.getCaptcha(), session);
        Account account = accountService.getByLoginName(login.getLoginName());
        checkAccount(login, account);
        //密码校验
        if (!accountService.verifyPwd(login.getPwd(), account.getLoginPwd())) {
            throw new SystemException(StatusCode.USER_LOGIN);
        }
        SessionUser sessionUser = sessionService.getByAccount(account);
        sessionService.sessionUser(session, sessionUser);
    }

    private void checkAccount(LoginDTO login, Account account) {
        if (account == null) {
            log.info("账户不存在[{}]", login);
            throw new SystemException(StatusCode.USER_LOGIN);
        }
        AccountStatus accountStatus = AccountStatus.getByCode(account.getAccountStatus());
        if (AccountStatus.DISABLED == accountStatus) {
            log.info("账户缺失[{}][{}]", login, account);
            throw new SystemException(StatusCode.USER_LOGIN_ACCOUNT_DISABLE);
        }
        AccountClass accountClass = AccountClass.getByCode(account.getAccountClass());
        if (accountClass == AccountClass.OUTTER) {
            log.info("不允许的账户登录[{}][{}]", login, account);
            throw new SystemException(StatusCode.USER_LOGIN_CLASS);
        }
    }

    @GetMapping("/getCurrentLogin")
    @ApiOperation("获取登录人信息")
    public WebSessionUser getCurrentLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        WebSessionUser user = sessionService.getSessionUser(session, WebSessionUser.class);
        return user;
    }

    @GetMapping("/signedOut")
    @ApiOperation("退出登录")
    public void signedOut(@ApiIgnore HttpSession session) {
        sessionService.sessionExpired(session);
    }

    @GetMapping("/imageCaptcha")
    @ApiOperation("图形验证码")
    public Optional<String> imageCaptcha(@ApiIgnore HttpSession session) {
        return Optional.of(captchaService.getImageCaptcha(session));
    }
}
