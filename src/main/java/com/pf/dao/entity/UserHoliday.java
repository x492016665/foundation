package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户假期
 *
 * @author yk_xing
 */
@ApiModel(value = "UserHoliday对象", description = "用户假期")
public class UserHoliday extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    @ApiModelProperty("休息天数")
    private Integer days;

    @ApiModelProperty("有效标记(1-休息中-REST|2-终止-STOP)")
    private Integer holidayStatus;

    @ApiModelProperty("申请时间")
    private Date applyTime;

    public Long getUserId() {
        return userId;
    }

    public UserHoliday setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public Date getStartDate() {
        return startDate;
    }

    public UserHoliday setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    public Date getEndDate() {
        return endDate;
    }

    public UserHoliday setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    public Integer getDays() {
        return days;
    }

    public UserHoliday setDays(Integer days) {
        this.days = days;
        return this;
    }
    public Integer getHolidayStatus() {
        return holidayStatus;
    }

    public UserHoliday setHolidayStatus(Integer holidayStatus) {
        this.holidayStatus = holidayStatus;
        return this;
    }
    public Date getApplyTime() {
        return applyTime;
    }

    public UserHoliday setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserHoliday{" +
            "userId=" + userId +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", days=" + days +
            ", holidayStatus=" + holidayStatus +
            ", applyTime=" + applyTime +
        "}";
    }
}
