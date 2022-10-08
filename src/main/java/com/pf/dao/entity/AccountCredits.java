package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 积分流水
 *
 * @author yk_xing
 */
@ApiModel(value = "AccountCredits对象", description = "积分流水")
public class AccountCredits extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("积分值")
    private Integer credits;

    @ApiModelProperty("积分动作(0-其他-OTHER|1-二维码分享-QRCODE)")
    private Integer creditsAction;

    @ApiModelProperty("动作值")
    private String actionId;

    @ApiModelProperty("动作时间")
    private Date actionTime;

    @ApiModelProperty("动作类型(0-减-SUB|1-加-ADD)")
    private Integer actionType;

    public Long getAccountId() {
        return accountId;
    }

    public AccountCredits setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public Integer getCredits() {
        return credits;
    }

    public AccountCredits setCredits(Integer credits) {
        this.credits = credits;
        return this;
    }
    public Integer getCreditsAction() {
        return creditsAction;
    }

    public AccountCredits setCreditsAction(Integer creditsAction) {
        this.creditsAction = creditsAction;
        return this;
    }
    public String getActionId() {
        return actionId;
    }

    public AccountCredits setActionId(String actionId) {
        this.actionId = actionId;
        return this;
    }
    public Date getActionTime() {
        return actionTime;
    }

    public AccountCredits setActionTime(Date actionTime) {
        this.actionTime = actionTime;
        return this;
    }
    public Integer getActionType() {
        return actionType;
    }

    public AccountCredits setActionType(Integer actionType) {
        this.actionType = actionType;
        return this;
    }

    @Override
    public String toString() {
        return "AccountCredits{" +
            "accountId=" + accountId +
            ", credits=" + credits +
            ", creditsAction=" + creditsAction +
            ", actionId=" + actionId +
            ", actionTime=" + actionTime +
            ", actionType=" + actionType +
        "}";
    }
}
