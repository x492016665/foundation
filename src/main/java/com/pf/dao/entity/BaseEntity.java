package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public abstract class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 有效标记(1:有效,0:无效)
     */
    @JsonIgnore
    @TableLogic(value = "1", delval = "0")
    private Integer recordStatus;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createSysTm;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateSysTm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer isRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getCreateSysTm() {
        return createSysTm;
    }

    public void setCreateSysTm(Date createSysTm) {
        this.createSysTm = createSysTm;
    }

    public Date getUpdateSysTm() {
        return updateSysTm;
    }

    public void setUpdateSysTm(Date updateSysTm) {
        this.updateSysTm = updateSysTm;
    }

    public void updateClean() {
        this.createSysTm = null;
        this.recordStatus = null;
        this.updateSysTm = null;
    }
}
