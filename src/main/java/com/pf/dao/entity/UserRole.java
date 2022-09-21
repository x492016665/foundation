package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户角色
 *
 * @author yk_xing
 */
@ApiModel(value = "UserRole对象", description = "用户角色")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("用户id")
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
    public Long getUserId() {
        return userId;
    }

    public UserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
            "roleId=" + roleId +
            ", userId=" + userId +
        "}";
    }
}
