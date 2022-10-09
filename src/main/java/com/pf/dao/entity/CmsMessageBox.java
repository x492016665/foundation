package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 消息信箱
 *
 * @author yk_xing
 */
@ApiModel(value = "CmsMessageBox对象", description = "消息信箱")
public class CmsMessageBox extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("内容id")
    private Long contentId;

    @ApiModelProperty("账号id")
    private Long accountId;

    @ApiModelProperty("已读标识")
    private Boolean openRead;

    @ApiModelProperty("读取时间")
    private Date readTime;

    public Long getContentId() {
        return contentId;
    }

    public CmsMessageBox setContentId(Long contentId) {
        this.contentId = contentId;
        return this;
    }
    public Long getAccountId() {
        return accountId;
    }

    public CmsMessageBox setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public Boolean getOpenRead() {
        return openRead;
    }

    public CmsMessageBox setOpenRead(Boolean openRead) {
        this.openRead = openRead;
        return this;
    }
    public Date getReadTime() {
        return readTime;
    }

    public CmsMessageBox setReadTime(Date readTime) {
        this.readTime = readTime;
        return this;
    }

    @Override
    public String toString() {
        return "CmsMessageBox{" +
            "contentId=" + contentId +
            ", accountId=" + accountId +
            ", openRead=" + openRead +
            ", readTime=" + readTime +
        "}";
    }
}
