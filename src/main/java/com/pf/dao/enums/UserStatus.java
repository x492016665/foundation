package com.pf.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户
 * @author yk_xing
 */
public enum UserStatus {
    DISABLED(0, "已注销"),
    WORKING(1, "工作中"),
    REST(2, "休息");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, UserStatus> cacheEnum = new HashMap<>();

    static {
        UserStatus[] list = UserStatus.values();
        for(UserStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    UserStatus(Integer code, String name) {
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

    public static UserStatus getByCode(Integer code) {
       return cacheEnum.get(code);
    }
}
