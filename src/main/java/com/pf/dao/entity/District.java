package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 区域
 *
 * @author yk_xing
 */
@ApiModel(value = "District对象", description = "区域")
public class District extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("区域编码")
    private String disCode;

    @ApiModelProperty("区域名称")
    private String name;

    @ApiModelProperty("区域级别(0-全国-COUNTRY|1-省份-PROVINCE|2-城市-CITY|3-区-AREA|4-街道-STREET)")
    private Integer disHierarchical;

    @ApiModelProperty("父级编码")
    private String parentDisCode;

    public String getDisCode() {
        return disCode;
    }

    public District setDisCode(String disCode) {
        this.disCode = disCode;
        return this;
    }
    public String getName() {
        return name;
    }

    public District setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getDisHierarchical() {
        return disHierarchical;
    }

    public District setDisHierarchical(Integer disHierarchical) {
        this.disHierarchical = disHierarchical;
        return this;
    }
    public String getParentDisCode() {
        return parentDisCode;
    }

    public District setParentDisCode(String parentDisCode) {
        this.parentDisCode = parentDisCode;
        return this;
    }

    @Override
    public String toString() {
        return "District{" +
            "disCode=" + disCode +
            ", name=" + name +
            ", disHierarchical=" + disHierarchical +
            ", parentDisCode=" + parentDisCode +
        "}";
    }
}
