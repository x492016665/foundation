package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志
 * @author yk_xing
 */
public enum OperType {
    CREATE(1, "创建"),
    MODIFY(2, "更新"),
    STATUS(3, "状态改变");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, OperType> cacheEnum = new HashMap<>();

    static {
        OperType[] list = OperType.values();
        for (OperType item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    OperType(Integer code, String name) {
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

    public static OperType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        OperType one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
