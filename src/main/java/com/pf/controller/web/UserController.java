package com.pf.controller.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pf.dao.entity.Account;
import com.pf.dao.entity.District;
import com.pf.dao.entity.Role;
import com.pf.dao.entity.User;
import com.pf.dao.enums.UserStatus;
import com.pf.dao.mapper.UserMapper;
import com.pf.pojo.dto.session.WebSessionUser;
import com.pf.pojo.dto.system.PrivilegePointDTO;
import com.pf.pojo.dto.system.UserDTO;
import com.pf.pojo.dto.system.UserPageDTO;
import com.pf.pojo.response.PageResult;
import com.pf.pojo.response.system.RoleVO;
import com.pf.pojo.response.system.UserVO;
import com.pf.service.*;
import com.pf.utils.CollectionUtils;
import com.pf.utils.request.RequestContextHelper;
import com.pf.utils.swagger.SwaggerConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户 前端控制器
 *
 * @author yk_xing
 */
@RestController
@RequestMapping("${system.prefix.namespace1}/user")
@Api(tags = "系统用户")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PrivilegePointService privilegePointService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private DistrictService districtService;

    @PostMapping("/page")
    @ApiOperation(value = "用户分页")
    public PageResult<UserVO> page(@RequestBody UserPageDTO userPage) {
        long total = userService.userCount(userPage);
        if (total == 0) {
            return PageResult.empty();
        }
        List<User> users = userService.userPage(userPage);
        List<UserVO> dataList = new ArrayList<>();
        for (User user : users) {
            List<Role> roleList = userRoleService.listByUserId(user.getId());
            UserVO vo = createUserVo(user);
            Account account = accountService.getById(user.getAccountId());
            vo.setLoginName(account.getLoginName());
            vo.setRole(roleList.stream()
                    .map(this::createRoleVo)
                    .collect(Collectors.toList())
            );
            dataList.add(vo);
        }
        IPage page = userPage.getPage();
        page.setTotal(total);
        return PageResult.getInstance(page, dataList);
    }

    @PostMapping("/engineerPage")
    @ApiOperation(value = "工程师分页")
    public PageResult<UserVO> engineerPage(@RequestBody UserPageDTO userPage) {
        long total = userService.enginnerCount(userPage);
        if (total == 0) {
            return PageResult.empty();
        }
        List<User> users = userService.enginnerPage(userPage);
        List<UserVO> userList = new ArrayList<>();
        for (User user : users) {
            UserVO uv = CollectionUtils.copy(user, UserVO.class);
            uv.setUserStatusName(UserStatus.getNameByCode(uv.getUserStatus()));
            userList.add(uv);
        }
        IPage page = userPage.getPage();
        page.setTotal(total);
        return PageResult.getInstance(page, userList);
    }

    @ApiOperation(value = "密码重置", notes = SwaggerConst.URL_PARAM)
    @PutMapping("/pwdRest")
    public void pwdRest(@ApiParam(value = "用户id") @NotNull Long id) {
        User user = userService.getById(id);
        accountService.pwdReset(user.getAccountId());
    }

    @ApiOperation(value = "密码修改", notes = SwaggerConst.URL_PARAM)
    @PutMapping("/pwdChange")
    public void pwdChange(@ApiParam(value = "旧密码") @NotNull String opwd, @ApiParam(value = "新密码") @NotNull String npwd) {
        WebSessionUser sessionUser = RequestContextHelper.getSessionUser();
        Long accountId = sessionUser.getAccountId();
        accountService.pwdChange(accountId, opwd, npwd);
    }

    private RoleVO createRoleVo(Role role) {
        RoleVO roleVo = new RoleVO();
        roleVo.setId(role.getId());
        roleVo.setName(role.getName());
        return roleVo;
    }

    private UserVO createUserVo(User user) {
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUserName(user.getUserName());
        vo.setTel(user.getTel());
        vo.setDiscode(user.getDiscode());
        List<District> discodes = districtService.getCodeWithParent(user.getDiscode());
        vo.setDisNames(discodes.stream().map(District::getName).collect(Collectors.toList()));
        vo.setDiscodes(discodes.stream().map(District::getDisCode).collect(Collectors.toList()));
        vo.setUserStatus(user.getUserStatus());
        vo.setUserStatusName(UserStatus.getNameByCode(user.getUserStatus()));
        vo.setLabel(user.getLabel());
        return vo;
    }

    @PutMapping("/disable")
    @ApiOperation(value = "注销用户", notes = SwaggerConst.URL_PARAM)
    public void disable(@ApiParam(value = "用户id") @NotNull Long id) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setUserStatus(UserStatus.DISABLED.getCode());
        userService.saveUser(user);
    }


    @PutMapping("/enable")
    @ApiOperation(value = "启用用户", notes = SwaggerConst.URL_PARAM)
    public void enable(@ApiParam(value = "用户id") @NotNull Long id) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setUserStatus(UserStatus.WORKING.getCode());
        userService.saveUser(user);
    }

    @PutMapping("/save")
    @ApiOperation(value = "新增/更新", notes = "id为空新增,不为空更新")
    public void save(@RequestBody UserDTO userDto) {
        userService.saveUser(userDto);
    }

    @DeleteMapping("/delById")
    @ApiOperation(value = "删除", hidden = true)
    public void delById(Long id) {
    }

    @GetMapping("/getById")
    @ApiOperation(value = "详情")
    public UserVO getById(@ApiParam(value = "用户id") @NotNull Long id) {
        User user = userService.getById(id);
        List<Role> roleList = userRoleService.listByUserId(user.getId());
        UserVO userVO = createUserVo(user);
        userVO.setRole(roleList.stream().map(this::createRoleVo).collect(Collectors.toList()));
        PrivilegePointDTO privilegePointRoot = privilegePointService.getByUserId(id);
        userVO.setPrivilegePoint(privilegePointRoot);
        return userVO;
    }

    @ApiOperation(value = "修改头像", notes = SwaggerConst.URL_PARAM)
    @PutMapping("/changeHead")
    public void changeHead(@ApiParam(value = "文件id") @NotNull String fileId) {
        WebSessionUser sessionUser = RequestContextHelper.getSessionUser();
        Long accountId = sessionUser.getAccountId();
        accountService.changeHead(accountId, fileId);
    }
}
