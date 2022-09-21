package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色
 *
 * @author yk_xing
 */
@ApiModel(value = "Role对象", description = "角色")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色类型(100-其他-DEFAULT|50-工程师-ENGINEER|51-技术客服-SERVICE|52-商务-BUSINESS|53-财务-FINANCE|2-管理员-ADMIN|1-超级管理员-SUPERADMIN)")
    private Integer roleType;

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getRoleType() {
        return roleType;
    }

    public Role setRoleType(Integer roleType) {
        this.roleType = roleType;
        return this;
    }

    @Override
    public String toString() {
        return "Role{" +
            "name=" + name +
            ", roleType=" + roleType +
        "}";
    }
}
