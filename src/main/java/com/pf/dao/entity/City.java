package com.pf.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pf.dao.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市表
 *
 * @author yk_xing
 */
@ApiModel(value = "City对象", description = "城市表")
public class City  {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("城市名称")
    private String name;

    @ApiModelProperty("省份id")
    private Integer pid;

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getPid() {
        return pid;
    }

    public City setPid(Integer pid) {
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
        return "City{" +
            "name=" + name +
            ", pid=" + pid +
        "}";
    }
}
