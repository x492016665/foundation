package com.pf.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 区域
 * @author yk_xing
 */
public enum DisHierarchical {
    COUNTRY(0, "全国"),
    PROVINCE(1, "省份"),
    CITY(2, "城市"),
    AREA(3, "区"),
    STREET(4, "街道");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, DisHierarchical> cacheEnum = new HashMap<>();

    static {
        DisHierarchical[] list = DisHierarchical.values();
        for(DisHierarchical item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    DisHierarchical(Integer code, String name) {
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

    public static DisHierarchical getByCode(Integer code) {
       return cacheEnum.get(code);
    }
}
