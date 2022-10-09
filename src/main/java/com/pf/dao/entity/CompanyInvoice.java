package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公司发票
 *
 * @author yk_xing
 */
@ApiModel(value = "CompanyInvoice对象", description = "公司发票")
public class CompanyInvoice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联人员")
    private Long customerId;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("税号")
    private String dutyParagraph;

    @ApiModelProperty("发票类型(1-普通发票-NORMAL|0-专票-SPECIAL)")
    private Integer invoiceType;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("电话号码")
    private String tel;

    @ApiModelProperty("开户银行")
    private String bank;

    @ApiModelProperty("银行账户")
    private String bankAccount;

    @ApiModelProperty("联系人")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    private String contactTel;

    @ApiModelProperty("寄件地址")
    private String mailAddress;

    public Long getCustomerId() {
        return customerId;
    }

    public CompanyInvoice setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
    public Long getCompanyId() {
        return companyId;
    }

    public CompanyInvoice setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
    public String getName() {
        return name;
    }

    public CompanyInvoice setName(String name) {
        this.name = name;
        return this;
    }
    public String getDutyParagraph() {
        return dutyParagraph;
    }

    public CompanyInvoice setDutyParagraph(String dutyParagraph) {
        this.dutyParagraph = dutyParagraph;
        return this;
    }
    public Integer getInvoiceType() {
        return invoiceType;
    }

    public CompanyInvoice setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }
    public String getAddress() {
        return address;
    }

    public CompanyInvoice setAddress(String address) {
        this.address = address;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public CompanyInvoice setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public String getBank() {
        return bank;
    }

    public CompanyInvoice setBank(String bank) {
        this.bank = bank;
        return this;
    }
    public String getBankAccount() {
        return bankAccount;
    }

    public CompanyInvoice setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }
    public String getContactPerson() {
        return contactPerson;
    }

    public CompanyInvoice setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }
    public String getContactTel() {
        return contactTel;
    }

    public CompanyInvoice setContactTel(String contactTel) {
        this.contactTel = contactTel;
        return this;
    }
    public String getMailAddress() {
        return mailAddress;
    }

    public CompanyInvoice setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
        return this;
    }

    @Override
    public String toString() {
        return "CompanyInvoice{" +
            "customerId=" + customerId +
            ", companyId=" + companyId +
            ", name=" + name +
            ", dutyParagraph=" + dutyParagraph +
            ", invoiceType=" + invoiceType +
            ", address=" + address +
            ", tel=" + tel +
            ", bank=" + bank +
            ", bankAccount=" + bankAccount +
            ", contactPerson=" + contactPerson +
            ", contactTel=" + contactTel +
            ", mailAddress=" + mailAddress +
        "}";
    }
}
