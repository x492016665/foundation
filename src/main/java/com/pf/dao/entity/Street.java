package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 街道表
 *
 * @author yk_xing
 */
@ApiModel(value = "Street对象", description = "街道表")
public class Street {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("街道名称")
    private String name;

    @ApiModelProperty("区域id")
    private Integer pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Street setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getPid() {
        return pid;
    }

    public Street setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    @Override
    public String toString() {
        return "Street{" +
            "name=" + name +
            ", pid=" + pid +
        "}";
    }
}
