package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * qrcode分享元
 *
 * @author yk_xing
 */
@ApiModel(value = "AccountQrcode对象", description = "qrcode分享元")
public class AccountQrcode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("编码")
    private String qrcodeId;

    @ApiModelProperty("分享图片id")
    private Long fileId;

    public Long getAccountId() {
        return accountId;
    }

    public AccountQrcode setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
    public String getQrcodeId() {
        return qrcodeId;
    }

    public AccountQrcode setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
        return this;
    }
    public Long getFileId() {
        return fileId;
    }

    public AccountQrcode setFileId(Long fileId) {
        this.fileId = fileId;
        return this;
    }

    @Override
    public String toString() {
        return "AccountQrcode{" +
            "accountId=" + accountId +
            ", qrcodeId=" + qrcodeId +
            ", fileId=" + fileId +
        "}";
    }
}
