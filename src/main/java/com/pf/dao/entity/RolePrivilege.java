package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色权限
 *
 * @author yk_xing
 */
@ApiModel(value = "RolePrivilege对象", description = "角色权限")
public class RolePrivilege extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限编码")
    private Long privilegeCode;

    public Long getRoleId() {
        return roleId;
    }

    public RolePrivilege setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
    public Long getPrivilegeCode() {
        return privilegeCode;
    }

    public RolePrivilege setPrivilegeCode(Long privilegeCode) {
        this.privilegeCode = privilegeCode;
        return this;
    }

    @Override
    public String toString() {
        return "RolePrivilege{" +
            "roleId=" + roleId +
            ", privilegeCode=" + privilegeCode +
        "}";
    }
}
