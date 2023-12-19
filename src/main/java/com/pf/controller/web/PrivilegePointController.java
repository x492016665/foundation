package com.pf.controller.web;

import cn.hutool.core.util.StrUtil;
import com.pf.config.aspect.privilege.Privilege;
import com.pf.dao.entity.PrivilegePoint;
import com.pf.dao.enums.RoleType;
import com.pf.pojo.dto.system.PrivilegeCodeDTO;
import com.pf.pojo.dto.system.PrivilegePointDTO;
import com.pf.service.PrivilegePointService;
import com.pf.service.RolePrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 系统权限 前端控制器
 *
 * @author yk_xing
 */
@RestController
@RequestMapping("${system.prefix.namespace1}/privilegePoint")
@Api(tags = "系统权限")
public class PrivilegePointController {

    @Autowired
    private PrivilegePointService privilegePointService;
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @Privilege
    @GetMapping("/getByUserId")
    @ApiOperation("获取指定用户权限")
    public PrivilegeCodeDTO getByUserId(@NotNull Long userId) {
        List<Long> userPrivilegePoints = privilegePointService.getProvilegeCodeByUserId(userId);
        Long adminRoleId = new Long(RoleType.ADMIN.getCode());
        List<Long> adminRolePrivilegeCodes = privilegePointService.getProvilegeCodeByRoleIds(Arrays.asList(adminRoleId));
        List<PrivilegePoint> adminRolePrivilegePoints = privilegePointService.listByIds(adminRolePrivilegeCodes);
        List<Long> checkCodes = new ArrayList<>();
        List<Long> hafCheckCodes = new ArrayList<>();
        for (PrivilegePoint privilegePoint : adminRolePrivilegePoints) {
            int checkCount = getChildCount(privilegePoint, userPrivilegePoints);
            if (checkCount > 0) {
                int count = getChildCount(privilegePoint, adminRolePrivilegeCodes);
                if (count == checkCount) {
                    checkCodes.add(privilegePoint.getPrivilegeCode());
                } else {
                    hafCheckCodes.add(privilegePoint.getPrivilegeCode());
                }
            }
        }
        PrivilegeCodeDTO privilegeCode = new PrivilegeCodeDTO();
        privilegeCode.setCheckCodes(checkCodes);
        privilegeCode.setHalfCheckCodes(hafCheckCodes);
        setRootCheck(privilegeCode);
        return privilegeCode;
    }

    private int getChildCount(PrivilegePoint target, List<Long> privilegePoints) {
        int hierarchical = target.getHierarchical();
        String leftCode = getLeftCode(target.getPrivilegeCode(), hierarchical);
        int count = 0;
        for (Long privilegeCode : privilegePoints) {
            if (leftCode.equals(getLeftCode(privilegeCode, hierarchical))) {
                count++;
            }
        }
        return count;
    }

    private String getLeftCode(Long privilegeCode, int hierarchical) {
        return StrUtil.sub(Objects.toString(privilegeCode), 0, hierarchical * 2);
    }

    @Privilege
    @GetMapping("/getByRoleIds")
    @ApiOperation("获取多个角色权限")
    public PrivilegeCodeDTO getByRoleIds(Long[] roleIds) {
        List<Long> rolePrivilegePoints = privilegePointService.getProvilegeCodeByRoleIds(Arrays.asList(roleIds));
        Long adminRoleId = new Long(RoleType.ADMIN.getCode());
        List<Long> adminRolePrivilegeCodes = privilegePointService.getProvilegeCodeByRoleIds(Arrays.asList(adminRoleId));
        List<PrivilegePoint> adminRolePrivilegePoints = privilegePointService.listByIds(adminRolePrivilegeCodes);
        List<Long> checkCodes = new ArrayList<>();
        List<Long> hafCheckCodes = new ArrayList<>();
        for (PrivilegePoint privilegePoint : adminRolePrivilegePoints) {
            int checkCount = getChildCount(privilegePoint, rolePrivilegePoints);
            if (checkCount > 0) {
                int count = getChildCount(privilegePoint, adminRolePrivilegeCodes);
                if (count == checkCount) {
                    checkCodes.add(privilegePoint.getPrivilegeCode());
                } else {
                    hafCheckCodes.add(privilegePoint.getPrivilegeCode());
                }
            }
        }
        PrivilegeCodeDTO privilegeCode = new PrivilegeCodeDTO();
        privilegeCode.setCheckCodes(checkCodes);
        privilegeCode.setHalfCheckCodes(hafCheckCodes);
        setRootCheck(privilegeCode);
        return privilegeCode;
    }

    private void setRootCheck(PrivilegeCodeDTO privilegeCode) {
        List<Long> checkCodes = privilegeCode.getCheckCodes();
        List<Long> hafCheckCodes = privilegeCode.getHalfCheckCodes();
        //判断根权限是否全选
        if(checkCodes.size() > 0) {
            PrivilegePointDTO root = privilegePointService.getRoot();
            if(hafCheckCodes.isEmpty()) {
                checkCodes.add(root.getPrivilegeCode());
            } else {
                hafCheckCodes.add(root.getPrivilegeCode());
            }
        }
    }

    @Privilege
    @GetMapping("/getUserPoint")
    @ApiOperation("获取用户待授权的权限列表")
    public PrivilegePointDTO getUserPoint() {
        Long adminRoleId = new Long(RoleType.ADMIN.getCode());
        return privilegePointService.getByRoleId(adminRoleId);
    }
}
