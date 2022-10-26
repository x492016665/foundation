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
@TableName("t_privilege")
@ApiModel(value = "Privilege对象", description = "系统权限")
public class Privilege extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限点名称")
    private String name;

    @ApiModelProperty("权限点编码")
    private String privilegeCode;

    @ApiModelProperty("父菜权限点")
    private String parentCode;

    @ApiModelProperty("权限分类(0-菜单-MENU|1-按钮-BUTTON)")
    private Integer classify;

    @ApiModelProperty("级别")
    private Integer hierarchical;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }
    public Integer getHierarchical() {
        return hierarchical;
    }

    public void setHierarchical(Integer hierarchical) {
        this.hierarchical = hierarchical;
    }

    @Override
    public String toString() {
        return "Privilege{" +
            "name=" + name +
            ", privilegeCode=" + privilegeCode +
            ", parentCode=" + parentCode +
            ", classify=" + classify +
            ", hierarchical=" + hierarchical +
        "}";
    }
}
