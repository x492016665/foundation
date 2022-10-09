package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 文章公告
 *
 * @author yk_xing
 */
@ApiModel(value = "CmsContent对象", description = "文章公告")
public class CmsContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;

    @ApiModelProperty("创建人id")
    private Long userId;

    @ApiModelProperty("分类(1-文章-ARTICLE|2-公告-NOTICE)")
    private Integer contentClassify;

    @ApiModelProperty("状态(1-未发布-UNPUB|2-已发布-PUB)")
    private Integer contentStatus;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("发布时间")
    private Date pubTime;

    public String getTitle() {
        return title;
    }

    public CmsContent setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getContent() {
        return content;
    }

    public CmsContent setContent(String content) {
        this.content = content;
        return this;
    }
    public String getIntro() {
        return intro;
    }

    public CmsContent setIntro(String intro) {
        this.intro = intro;
        return this;
    }
    public Integer getViewCount() {
        return viewCount;
    }

    public CmsContent setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }
    public Long getUserId() {
        return userId;
    }

    public CmsContent setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public Integer getContentClassify() {
        return contentClassify;
    }

    public CmsContent setContentClassify(Integer contentClassify) {
        this.contentClassify = contentClassify;
        return this;
    }
    public Integer getContentStatus() {
        return contentStatus;
    }

    public CmsContent setContentStatus(Integer contentStatus) {
        this.contentStatus = contentStatus;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public CmsContent setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public Date getPubTime() {
        return pubTime;
    }

    public CmsContent setPubTime(Date pubTime) {
        this.pubTime = pubTime;
        return this;
    }

    @Override
    public String toString() {
        return "CmsContent{" +
            "title=" + title +
            ", content=" + content +
            ", intro=" + intro +
            ", viewCount=" + viewCount +
            ", userId=" + userId +
            ", contentClassify=" + contentClassify +
            ", contentStatus=" + contentStatus +
            ", createTime=" + createTime +
            ", pubTime=" + pubTime +
        "}";
    }
}
