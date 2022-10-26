package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 操作日志
 *
 * @author yk_xing
 */
@ApiModel(value = "OperationLog对象", description = "操作日志")
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单据id")
    private Long orderId;

    @ApiModelProperty("单据分类(1-报价单-QUOTATION|2-派工单-DISPATCH)")
    private Integer orderClassify;

    @ApiModelProperty("操作类型(1-创建-CREATE|2-更新-MODIFY|3-STATUS-状态改变)")
    private Integer operType;

    @ApiModelProperty("操作概述")
    private String operTitle;

    @ApiModelProperty("操作人账号")
    private Long accountId;

    @ApiModelProperty("操作人")
    private String userName;

    @ApiModelProperty("操作时间")
    private Date operTime;

    @ApiModelProperty("操作内容")
    private String trace;

    @ApiModelProperty("上一个操作记录id")
    private Long prevId;

    public Long getOrderId() {
        return orderId;
    }

    public OperationLog setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
    public Integer getOrderClassify() {
        return orderClassify;
    }

    public OperationLog setOrderClassify(Integer orderClassify) {
        this.orderClassify = orderClassify;
        return this;
    }
    public Integer getOperType() {
        return operType;
    }

    public OperationLog setOperType(Integer operType) {
        this.operType = operType;
        return this;
    }
    public String getOperTitle() {
        return operTitle;
    }

    public OperationLog setOperTitle(String operTitle) {
        this.operTitle = operTitle;
        return this;
    }
    public Long getAccountId() {
        return accountId;
    }

    public OperationLog setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public OperationLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public Date getOperTime() {
        return operTime;
    }

    public OperationLog setOperTime(Date operTime) {
        this.operTime = operTime;
        return this;
    }
    public String getTrace() {
        return trace;
    }

    public OperationLog setTrace(String trace) {
        this.trace = trace;
        return this;
    }
    public Long getPrevId() {
        return prevId;
    }

    public OperationLog setPrevId(Long prevId) {
        this.prevId = prevId;
        return this;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
            "orderId=" + orderId +
            ", orderClassify=" + orderClassify +
            ", operType=" + operType +
            ", operTitle=" + operTitle +
            ", accountId=" + accountId +
            ", userName=" + userName +
            ", operTime=" + operTime +
            ", trace=" + trace +
            ", prevId=" + prevId +
        "}";
    }
}
