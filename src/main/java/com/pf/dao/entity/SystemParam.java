package com.pf.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统参数
 *
 * @author yk_xing
 */
@ApiModel(value = "SystemParam对象", description = "系统参数")
public class SystemParam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("系统参数分类")
    private String paramClassify;

    @ApiModelProperty("系统参数key值")
    private String paramKey;

    @ApiModelProperty("系统参数value值")
    private String paramValue;

    @ApiModelProperty("备注")
    private String remark;

    public String getParamClassify() {
        return paramClassify;
    }

    public SystemParam setParamClassify(String paramClassify) {
        this.paramClassify = paramClassify;
        return this;
    }
    public String getParamKey() {
        return paramKey;
    }

    public SystemParam setParamKey(String paramKey) {
        this.paramKey = paramKey;
        return this;
    }
    public String getParamValue() {
        return paramValue;
    }

    public SystemParam setParamValue(String paramValue) {
        this.paramValue = paramValue;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public SystemParam setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "SystemParam{" +
            "paramClassify=" + paramClassify +
            ", paramKey=" + paramKey +
            ", paramValue=" + paramValue +
            ", remark=" + remark +
        "}";
    }
}
