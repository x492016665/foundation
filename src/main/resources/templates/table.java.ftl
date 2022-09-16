package com.pf.dao.table;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pf.dao.entity.${entity};

/**
 * ${table.comment!}
 *
 * @author ${author}
 */
public class ${entity}${key} extends BaseEntityTable {
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;

</#if>
    <#list table.fields as field>
    /**
     * ${field.comment}
     */
    public static final String ${field.name} = "${field.name?upper_case}";

    </#list>

    public static QueryWrapper<${entity}> getQueryWrapper(${entity} entity) {
        QueryWrapper<${entity}> wrapper = BaseEntityTable.getQueryWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    public static UpdateWrapper<${entity}> getUpdateWrapper(${entity} entity) {
        UpdateWrapper<${entity}> wrapper = BaseEntityTable.getUpdateWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    private static void wrapper(Compare wrapper, ${entity} entity) {
    <#list table.fields as field>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>
        if (null != entity.${getprefix}${field.capitalName}()) {
            wrapper.eq(${field.name}, entity.${getprefix}${field.capitalName}());
        }
       </#list>
    }
}
