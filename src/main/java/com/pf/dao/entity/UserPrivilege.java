package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户权限
 *
 * @author yk_xing
 */
@ApiModel(value = "UserPrivilege对象", description = "用户权限")
public class UserPrivilege extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("权限编码")
    private Long privilegeCode;

    public Long getUserId() {
        return userId;
    }

    public UserPrivilege setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public Long getPrivilegeCode() {
        return privilegeCode;
    }

    public UserPrivilege setPrivilegeCode(Long privilegeCode) {
        this.privilegeCode = privilegeCode;
        return this;
    }

    @Override
    public String toString() {
        return "UserPrivilege{" +
            "userId=" + userId +
            ", privilegeCode=" + privilegeCode +
        "}";
    }
}
