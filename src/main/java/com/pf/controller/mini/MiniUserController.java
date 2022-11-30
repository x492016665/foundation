package com.pf.controller.mini;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pf.dao.entity.Account;
import com.pf.dao.entity.District;
import com.pf.dao.entity.User;
import com.pf.dao.enums.AccountClass;
import com.pf.pojo.dto.session.MiniSessionUser;
import com.pf.pojo.dto.session.WebSessionUser;
import com.pf.pojo.dto.system.UserDTO;
import com.pf.pojo.dto.system.UserPageDTO;
import com.pf.pojo.response.PageResult;
import com.pf.pojo.response.system.UserVO;
import com.pf.service.AccountService;
import com.pf.service.DistrictService;
import com.pf.service.UserService;
import com.pf.utils.CollectionUtils;
import com.pf.utils.FileUtils;
import com.pf.utils.swagger.SwaggerConst;
import com.pf.utils.request.RequestContextHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统用户 前端控制器
 *
 * @author yk_xing
 */
@RestController
@RequestMapping("${system.prefix.namespace2}/user")
@Api(tags = "内部用户")
public class MiniUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private DistrictService districtService;

    @PostMapping("/engineerPage")
    @ApiOperation(value = "工程师分页")
    public PageResult<UserVO> engineerPage(@RequestBody UserPageDTO userPage) {
        long total = userService.enginnerCount(userPage);
        if (total == 0) {
            return PageResult.empty();
        }
        List<User> users = userService.enginnerPage(userPage);
        List<UserVO> dataList = users.stream().map((u)->{
            UserVO user = CollectionUtils.copy(u, UserVO.class);
            List<District> discodes = districtService.getCodeWithParent(u.getDiscode());
            user.setDisNames(discodes.stream().map(District::getName).collect(Collectors.toList()));
            user.setDiscodes(discodes.stream().map(District::getDisCode).collect(Collectors.toList()));
            return user;
        }).collect(Collectors.toList());
        IPage page = userPage.getPage();
        page.setTotal(total);
        return PageResult.getInstance(page, dataList);
    }

    @ApiOperation(value = "密码修改", notes = SwaggerConst.URL_PARAM)
    @PutMapping("/pwdChange")
    public void pwdChange(@ApiParam(value = "旧密码") @NotNull String opwd, @ApiParam(value = "新密码") @NotNull String npwd) {
        WebSessionUser sessionUser = RequestContextHelper.getSessionUser();
        Long accountId = sessionUser.getAccountId();
        accountService.pwdChange(accountId, opwd, npwd);
    }

    @ApiOperation("获取当前用户名片")
    @GetMapping("/getUser")
    public UserVO getUser() {
        MiniSessionUser sessionUser = RequestContextHelper.getSessionUser();
        User user = userService.getById(sessionUser.getUserId());
        UserVO uv = CollectionUtils.copy(user, UserVO.class);
        List<District> discodes = districtService.getCodeWithParent(user.getDiscode());
        uv.setDisNames(discodes.stream().map(District::getName).collect(Collectors.toList()));
        uv.setDiscodes(discodes.stream().map(District::getDisCode).collect(Collectors.toList()));
        Optional<Account> accountOpt = accountService.getBySrcId(user.getId(), AccountClass.INNER);
        if(accountOpt.isPresent()) {
            String fileId = FileUtils.idEncode(accountOpt.get().getFileId());
            uv.setFileId(fileId);
        }
        return uv;
    }

    @ApiOperation("修改资料")
    @PutMapping("/save")
    public void save(@RequestBody UserDTO user) {
        userService.saveUser(user);
    }

    @ApiOperation(value = "修改头像", notes = SwaggerConst.URL_PARAM)
    @PutMapping("/changeHead")
    public void changeHead(@ApiParam(value = "文件id") @NotNull String fileId) {
        MiniSessionUser sessionUser = RequestContextHelper.getSessionUser();
        Long accountId = sessionUser.getAccountId();
        accountService.changeHead(accountId, fileId);
    }
}
