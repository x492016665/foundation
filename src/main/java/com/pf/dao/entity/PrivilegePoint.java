package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统权限
 *
 * @author yk_xing
 */
@ApiModel(value = "PrivilegePoint对象", description = "系统权限")
public class PrivilegePoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限点名称")
    private String name;

    @ApiModelProperty("权限点编码")
    private Long privilegeCode;

    @ApiModelProperty("前端资源编码")
    private String frontCode;

    @ApiModelProperty("后端资源编码")
    private String sourcePath;

    @ApiModelProperty("父菜权限点")
    private Long parentCode;

    @ApiModelProperty("权限分类(0-菜单-MENU|1-按钮-BUTTON)")
    private Integer privilegeClassify;

    @ApiModelProperty("级别")
    private Integer hierarchical;

    @ApiModelProperty("显示顺序")
    private Integer showOrder;

    public String getName() {
        return name;
    }

    public PrivilegePoint setName(String name) {
        this.name = name;
        return this;
    }
    public Long getPrivilegeCode() {
        return privilegeCode;
    }

    public PrivilegePoint setPrivilegeCode(Long privilegeCode) {
        this.privilegeCode = privilegeCode;
        return this;
    }
    public String getFrontCode() {
        return frontCode;
    }

    public PrivilegePoint setFrontCode(String frontCode) {
        this.frontCode = frontCode;
        return this;
    }
    public String getSourcePath() {
        return sourcePath;
    }

    public PrivilegePoint setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }
    public Long getParentCode() {
        return parentCode;
    }

    public PrivilegePoint setParentCode(Long parentCode) {
        this.parentCode = parentCode;
        return this;
    }
    public Integer getPrivilegeClassify() {
        return privilegeClassify;
    }

    public PrivilegePoint setPrivilegeClassify(Integer privilegeClassify) {
        this.privilegeClassify = privilegeClassify;
        return this;
    }
    public Integer getHierarchical() {
        return hierarchical;
    }

    public PrivilegePoint setHierarchical(Integer hierarchical) {
        this.hierarchical = hierarchical;
        return this;
    }
    public Integer getShowOrder() {
        return showOrder;
    }

    public PrivilegePoint setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    @Override
    public String toString() {
        return "PrivilegePoint{" +
            "name=" + name +
            ", privilegeCode=" + privilegeCode +
            ", frontCode=" + frontCode +
            ", sourcePath=" + sourcePath +
            ", parentCode=" + parentCode +
            ", privilegeClassify=" + privilegeClassify +
            ", hierarchical=" + hierarchical +
            ", showOrder=" + showOrder +
        "}";
    }
}
