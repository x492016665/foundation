package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系人
 *
 * @author yk_xing
 */
@ApiModel(value = "Contacts对象", description = "联系人")
public class Contacts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("联系人姓名")
    private String userName;

    @ApiModelProperty("联系人电话")
    private String tel;

    @ApiModelProperty("区域")
    private String discode;

    @ApiModelProperty("联系人地址")
    private String address;

    @ApiModelProperty("默认使用")
    private Boolean defaultUseing;

    @ApiModelProperty("客户id")
    private Long customerId;

    @ApiModelProperty("公司id")
    private Long companyId;

    public String getUserName() {
        return userName;
    }

    public Contacts setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public Contacts setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public String getDiscode() {
        return discode;
    }

    public Contacts setDiscode(String discode) {
        this.discode = discode;
        return this;
    }
    public String getAddress() {
        return address;
    }

    public Contacts setAddress(String address) {
        this.address = address;
        return this;
    }
    public Boolean getDefaultUseing() {
        return defaultUseing;
    }

    public Contacts setDefaultUseing(Boolean defaultUseing) {
        this.defaultUseing = defaultUseing;
        return this;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public Contacts setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
    public Long getCompanyId() {
        return companyId;
    }

    public Contacts setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    @Override
    public String toString() {
        return "Contacts{" +
            "userName=" + userName +
            ", tel=" + tel +
            ", discode=" + discode +
            ", address=" + address +
            ", defaultUseing=" + defaultUseing +
            ", customerId=" + customerId +
            ", companyId=" + companyId +
        "}";
    }
}
