package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户
 *
 * @author yk_xing
 */
@ApiModel(value = "User对象", description = "系统用户")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String tel;

    @ApiModelProperty("当前状态(0-已注销-DISABLED|1-工作中-WORKING|2-休息-REST)")
    private Integer userStatus;

    @ApiModelProperty("区域")
    private String discode;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("账号id")
    private Long accountId;

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public User setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public User setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
    public String getDiscode() {
        return discode;
    }

    public User setDiscode(String discode) {
        this.discode = discode;
        return this;
    }
    public String getLabel() {
        return label;
    }

    public User setLabel(String label) {
        this.label = label;
        return this;
    }
    public Long getAccountId() {
        return accountId;
    }

    public User setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
            "userName=" + userName +
            ", tel=" + tel +
            ", userStatus=" + userStatus +
            ", discode=" + discode +
            ", label=" + label +
            ", accountId=" + accountId +
        "}";
    }
}
