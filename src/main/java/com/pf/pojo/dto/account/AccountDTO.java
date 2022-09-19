package com.pf.pojo.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yk_xing
 */
@Data
public class AccountDTO {

    private Long id;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String loginPwd;

    @ApiModelProperty("当前状态(0-已注销-DISABLED|1-启用-ENABLE|)")
    private Integer accountStatus;

    @ApiModelProperty("账号类别(0-手机号-TEL|1-邮箱-EMAIL|2-自定义-ALL)")
    private Integer accountType;

    @ApiModelProperty("账号分类(0-内部-INNER|1-外部-OUTTER)")
    private Integer accountClass;

    @ApiModelProperty("关联id")
    private Long srcId;

    @ApiModelProperty("头像id")
    private Long fileId;
}
