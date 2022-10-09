package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 区域表
 *
 * @author yk_xing
 */
@ApiModel(value = "Area对象", description = "区域表")
public class Area {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("区域名称")
    private String name;

    @ApiModelProperty("城市id")
    private Integer pid;

    public String getName() {
        return name;
    }

    public Area setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getPid() {
        return pid;
    }

    public Area setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Area{" +
            "name=" + name +
            ", pid=" + pid +
        "}";
    }
}
