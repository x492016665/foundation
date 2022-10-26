package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 文件元数据
 *
 * @author yk_xing
 */
@ApiModel(value = "FileMetadata对象", description = "文件元数据")
public class FileMetadata extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("md5")
    private String md5;

    @ApiModelProperty("sha1")
    private String sha1;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("文件分类(0-单据图片-BILL_IMG|1-签名-SIGN|2-头像-HEAD|3-CMS图片-CMS_IMG|4-单据文件-BILL_FILE|5-小程序码-WXA)")
    private Integer fileClassify;

    @ApiModelProperty("相对路径")
    private String path;

    @ApiModelProperty("后缀")
    private String suffix;

    public String getMd5() {
        return md5;
    }

    public FileMetadata setMd5(String md5) {
        this.md5 = md5;
        return this;
    }
    public String getSha1() {
        return sha1;
    }

    public FileMetadata setSha1(String sha1) {
        this.sha1 = sha1;
        return this;
    }
    public String getName() {
        return name;
    }

    public FileMetadata setName(String name) {
        this.name = name;
        return this;
    }
    public Long getFileSize() {
        return fileSize;
    }

    public FileMetadata setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }
    public Integer getFileClassify() {
        return fileClassify;
    }

    public FileMetadata setFileClassify(Integer fileClassify) {
        this.fileClassify = fileClassify;
        return this;
    }
    public String getPath() {
        return path;
    }

    public FileMetadata setPath(String path) {
        this.path = path;
        return this;
    }
    public String getSuffix() {
        return suffix;
    }

    public FileMetadata setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public String toString() {
        return "FileMetadata{" +
            "md5=" + md5 +
            ", sha1=" + sha1 +
            ", name=" + name +
            ", fileSize=" + fileSize +
            ", fileClassify=" + fileClassify +
            ", path=" + path +
            ", suffix=" + suffix +
        "}";
    }
}
