package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统账号
 *
 * @author yk_xing
 */
@ApiModel(value = "Account对象", description = "系统账号")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String loginPwd;

    @ApiModelProperty("当前状态(0-已注销-DISABLED|1-启用-ENABLE|)")
    private Integer accountStatus;

    @ApiModelProperty("账号类别(0-手机号-TEL|1-邮箱-EMAIL|2-自定义-ALL)")
    private Integer accountType;

    @ApiModelProperty("账号分类(0-内部-INNER|1-外部-OUTTER)")
    private Integer accountClass;

    @ApiModelProperty("关联id")
    private Long srcId;

    @ApiModelProperty("头像id")
    private Long fileId;

    public String getLoginName() {
        return loginName;
    }

    public Account setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }
    public String getLoginPwd() {
        return loginPwd;
    }

    public Account setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
        return this;
    }
    public Integer getAccountStatus() {
        return accountStatus;
    }

    public Account setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }
    public Integer getAccountType() {
        return accountType;
    }

    public Account setAccountType(Integer accountType) {
        this.accountType = accountType;
        return this;
    }
    public Integer getAccountClass() {
        return accountClass;
    }

    public Account setAccountClass(Integer accountClass) {
        this.accountClass = accountClass;
        return this;
    }
    public Long getSrcId() {
        return srcId;
    }

    public Account setSrcId(Long srcId) {
        this.srcId = srcId;
        return this;
    }
    public Long getFileId() {
        return fileId;
    }

    public Account setFileId(Long fileId) {
        this.fileId = fileId;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
            "loginName=" + loginName +
            ", loginPwd=" + loginPwd +
            ", accountStatus=" + accountStatus +
            ", accountType=" + accountType +
            ", accountClass=" + accountClass +
            ", srcId=" + srcId +
            ", fileId=" + fileId +
        "}";
    }
}
