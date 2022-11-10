package com.pf.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 搬迁服务扩展
 * @author yk_xing
 */
public enum RemovalScale {
    LESS(0, "小于n台"),
    MORE(1, "大于n台");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, RemovalScale> cacheEnum = new HashMap<>();

    static {
        RemovalScale[] list = RemovalScale.values();
        for(RemovalScale item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    RemovalScale(Integer code, String name) {
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

    public static RemovalScale getByCode(Integer code) {
       return cacheEnum.get(code);
    }
}
