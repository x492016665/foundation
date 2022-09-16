package ${package.Controller};

import com.pf.service.${table.serviceName};
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pf.utils.result.Result;
import com.pf.pojo.response.PageResult;
import com.pf.utils.result.WebMessage;
import springfox.documentation.annotations.ApiIgnore;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#assign _moduleName="${package.Controller?substring(18)}"/>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${_moduleName}/${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Api(tags = "${table.comment!}")
@ApiIgnore
@Deprecated
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};
/*
    @PostMapping("/page")
    @ApiOperation(value = "分页")
    public PageResult<${entity}DTO> page(@RequestBody ${entity}DTO query) {
        QueryWrapper<${entity}> select = ${entity}Table.getQueryWrapper();
        <#list table.fields as field>
                <#if field.propertyType == "boolean">
                    <#assign getprefix="is"/>
                <#else>
                    <#assign getprefix="get"/>
                </#if>
        <#if field.propertyType == "String">
        if (StrUtil.isNotEmpty(query.${getprefix}${field.capitalName}())) {
            select.likeRight(${entity}Table.${field.name}, query.${getprefix}${field.capitalName}());
        }
        <#else>
        if (query.${getprefix}${field.capitalName}() != null) {
            select.eq(${entity}Table.${field.name}, query.${getprefix}${field.capitalName}());
        }
        </#if>
        </#list>
        IPage<${entity}> page = query.getDefaultPage();
        ${table.serviceName?uncap_first}.page(page, select);
        return PageResult.getInstance(page, ${entity}DTO.class);
    }

    @PutMapping("/save")
    @ApiOperation(value = "新增/更新", notes = "id为空新增,不为空更新")
    public void save(@RequestBody ${entity}DTO body) {
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(body, entity);
        ${table.serviceName?uncap_first}.saveOrUpdate(entity);
    }

    @DeleteMapping("/delById")
    @ApiOperation(value = "删除")
    public void delById(String id) {
        ${table.serviceName?uncap_first}.removeById(id);
        return WebMessage.success();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "详情")
    public ${entity}DTO getById(String id) {
        ${entity} entity = ${table.serviceName?uncap_first}.getById(id);
        ${entity}DTO ${entity?uncap_first} = new ${entity}DTO();
        BeanUtils.copyProperties(entity, ${entity?uncap_first});
        return WebMessage.success(${entity?uncap_first});
    }
*/
}
</#if>
