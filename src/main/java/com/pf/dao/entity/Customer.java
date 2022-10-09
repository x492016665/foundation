package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户
 *
 * @author yk_xing
 */
@ApiModel(value = "Customer对象", description = "客户")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("客户名称")
    private String userName;

    @ApiModelProperty("所属公司")
    private Long companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("性别(0-女-FEMALE|1-男-MALE|2-保密-UNKNOW)")
    private Integer gender;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("账号id")
    private Long accountId;

    @ApiModelProperty("区域")
    private String discode;

    public String getUserName() {
        return userName;
    }

    public Customer setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public Long getCompanyId() {
        return companyId;
    }

    public Customer setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
    public String getCompanyName() {
        return companyName;
    }

    public Customer setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    public Integer getGender() {
        return gender;
    }

    public Customer setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public Customer setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }
    public Long getAccountId() {
        return accountId;
    }

    public Customer setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public String getDiscode() {
        return discode;
    }

    public Customer setDiscode(String discode) {
        this.discode = discode;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "userName=" + userName +
            ", companyId=" + companyId +
            ", companyName=" + companyName +
            ", gender=" + gender +
            ", tel=" + tel +
            ", email=" + email +
            ", address=" + address +
            ", accountId=" + accountId +
            ", discode=" + discode +
        "}";
    }
}
