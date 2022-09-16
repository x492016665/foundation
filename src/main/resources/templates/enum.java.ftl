package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * ${table.comment!}
 * @author ${author}
 */
public enum ${enum.className} {
<#list enum.fields as field>
    ${field.property}(${field.code}, "${field.name}")<#if field_has_next>,<#else>;</#if>
</#list>

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, ${enum.className}> cacheEnum = new HashMap<>();

    static {
        ${enum.className}[] list = ${enum.className}.values();
        for (${enum.className} item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    ${enum.className}(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCode() {
        return this.code;
    }

    public static String getNameByCode(Integer code) {
        return cacheName.get(code);
    }

    public static ${enum.className} getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        ${enum.className} one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
