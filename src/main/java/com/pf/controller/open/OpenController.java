package com.pf.controller.open;

import com.pf.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yk_xing
 */
@RestController
@RequestMapping("${system.prefix.namespace4}/quotation")
public class OpenController {

    @RequestMapping("/html")
    @ApiOperation("开放接口")
    public Result html(String sence, String code) {
        return new Result();
    }
}
