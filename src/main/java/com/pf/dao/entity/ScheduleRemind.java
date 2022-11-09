package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 事项提醒
 *
 * @author yk_xing
 */
@ApiModel(value = "ScheduleRemind对象", description = "事项提醒")
public class ScheduleRemind extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("报价单id")
    private Long quotationId;

    @ApiModelProperty("报价单编号")
    private String quotationCode;

    @ApiModelProperty("派工单id")
    private Long dispatchId;

    @ApiModelProperty("报价单编号")
    private String dispatchCode;

    @ApiModelProperty("提醒内容")
    private String context;

    @ApiModelProperty("事务类型(0-报价提醒-QUOTED|10-派工提醒工程师-DISPATCH_ENGINEER|11-派工提醒客服-DISPATCH_SERVICE|20-开票提醒-INVOICE|30-收款提醒-PAYEE|40-入库提醒-STORAGE)")
    private Integer scheduleType;

    @ApiModelProperty("处理状态(0-未处理-UNDISPOSED|1-已处理-PROCESSED)")
    private Integer scheduleStatus;

    @ApiModelProperty("事务日期")
    private String dateTime;

    @ApiModelProperty("完成时间")
    private Date processTime;

    public Long getUserId() {
        return userId;
    }

    public ScheduleRemind setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public Long getQuotationId() {
        return quotationId;
    }

    public ScheduleRemind setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
        return this;
    }
    public String getQuotationCode() {
        return quotationCode;
    }

    public ScheduleRemind setQuotationCode(String quotationCode) {
        this.quotationCode = quotationCode;
        return this;
    }
    public Long getDispatchId() {
        return dispatchId;
    }

    public ScheduleRemind setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
        return this;
    }
    public String getDispatchCode() {
        return dispatchCode;
    }

    public ScheduleRemind setDispatchCode(String dispatchCode) {
        this.dispatchCode = dispatchCode;
        return this;
    }
    public String getContext() {
        return context;
    }

    public ScheduleRemind setContext(String context) {
        this.context = context;
        return this;
    }
    public Integer getScheduleType() {
        return scheduleType;
    }

    public ScheduleRemind setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
        return this;
    }
    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public ScheduleRemind setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
        return this;
    }
    public String getDateTime() {
        return dateTime;
    }

    public ScheduleRemind setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
    public Date getProcessTime() {
        return processTime;
    }

    public ScheduleRemind setProcessTime(Date processTime) {
        this.processTime = processTime;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleRemind{" +
            "userId=" + userId +
            ", quotationId=" + quotationId +
            ", quotationCode=" + quotationCode +
            ", dispatchId=" + dispatchId +
            ", dispatchCode=" + dispatchCode +
            ", context=" + context +
            ", scheduleType=" + scheduleType +
            ", scheduleStatus=" + scheduleStatus +
            ", dateTime=" + dateTime +
            ", processTime=" + processTime +
        "}";
    }
}
