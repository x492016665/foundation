package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 微信用户
 *
 * @author yk_xing
 */
@ApiModel(value = "WxUser对象", description = "微信用户")
public class WxUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("手机号")
    private String tel;

    @ApiModelProperty("微信全局id")
    private String unionId;

    @ApiModelProperty("小程序openId")
    private String maOpenId;

    @ApiModelProperty("微信公众号openId")
    private String mpOpenId;

    @ApiModelProperty("用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息")
    private Boolean subscribe;

    @ApiModelProperty("用户的语言")
    private String language;

    @ApiModelProperty("用户关注时间")
    private Long subscribeTime;

    @ApiModelProperty("公众号运营者对粉丝的备注")
    private String remark;

    @ApiModelProperty("用户所在的分组ID")
    private Integer groupid;

    @ApiModelProperty("用户被打上的标签ID列表")
    private String tagIds;

    @ApiModelProperty("用户特权信息")
    private String privileges;

    @ApiModelProperty("用户关注的渠道来源")
    private String subscribeScene;

    @ApiModelProperty("用户关注的渠道来源（开发者自定义）")
    private String qrScene;

    @ApiModelProperty("二维码扫码场景描述（开发者自定义）")
    private String qrSceneStr;

    @ApiModelProperty("用户关注/取关时间")
    private Date subscribeStatusTime;

    public Long getAccountId() {
        return accountId;
    }

    public WxUser setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public WxUser setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public String getUnionId() {
        return unionId;
    }

    public WxUser setUnionId(String unionId) {
        this.unionId = unionId;
        return this;
    }
    public String getMaOpenId() {
        return maOpenId;
    }

    public WxUser setMaOpenId(String maOpenId) {
        this.maOpenId = maOpenId;
        return this;
    }
    public String getMpOpenId() {
        return mpOpenId;
    }

    public WxUser setMpOpenId(String mpOpenId) {
        this.mpOpenId = mpOpenId;
        return this;
    }
    public Boolean getSubscribe() {
        return subscribe;
    }

    public WxUser setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
        return this;
    }
    public String getLanguage() {
        return language;
    }

    public WxUser setLanguage(String language) {
        this.language = language;
        return this;
    }
    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public WxUser setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public WxUser setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public Integer getGroupid() {
        return groupid;
    }

    public WxUser setGroupid(Integer groupid) {
        this.groupid = groupid;
        return this;
    }
    public String getTagIds() {
        return tagIds;
    }

    public WxUser setTagIds(String tagIds) {
        this.tagIds = tagIds;
        return this;
    }
    public String getPrivileges() {
        return privileges;
    }

    public WxUser setPrivileges(String privileges) {
        this.privileges = privileges;
        return this;
    }
    public String getSubscribeScene() {
        return subscribeScene;
    }

    public WxUser setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
        return this;
    }
    public String getQrScene() {
        return qrScene;
    }

    public WxUser setQrScene(String qrScene) {
        this.qrScene = qrScene;
        return this;
    }
    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public WxUser setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
        return this;
    }
    public Date getSubscribeStatusTime() {
        return subscribeStatusTime;
    }

    public WxUser setSubscribeStatusTime(Date subscribeStatusTime) {
        this.subscribeStatusTime = subscribeStatusTime;
        return this;
    }

    @Override
    public String toString() {
        return "WxUser{" +
            "accountId=" + accountId +
            ", tel=" + tel +
            ", unionId=" + unionId +
            ", maOpenId=" + maOpenId +
            ", mpOpenId=" + mpOpenId +
            ", subscribe=" + subscribe +
            ", language=" + language +
            ", subscribeTime=" + subscribeTime +
            ", remark=" + remark +
            ", groupid=" + groupid +
            ", tagIds=" + tagIds +
            ", privileges=" + privileges +
            ", subscribeScene=" + subscribeScene +
            ", qrScene=" + qrScene +
            ", qrSceneStr=" + qrSceneStr +
            ", subscribeStatusTime=" + subscribeStatusTime +
        "}";
    }
}
