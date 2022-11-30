package com.pf.controller.mini;

import com.pf.pojo.dto.system.SystemParamDTO;
import com.pf.service.SystemParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统参数 前端控制器
 *
 * @author yk_xing
 */
@RestController
@RequestMapping("/${system.prefix.namespace2}/systemParam")
@Api(tags = "系统参数")
public class MiniSystemParamController {

    @Autowired
    private SystemParamService systemParamService;

    @ApiOperation(value = "通过分类获取系统参数列表", notes = SystemParamService.nodes)
    @GetMapping("/listByClassify")
    public List<SystemParamDTO> listByClassify(String classify) {
        return systemParamService.listByClassify(classify);
    }
}
