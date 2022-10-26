package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户
 * @author yk_xing
 */
public enum Gender {
    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOW(2, "保密");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, Gender> cacheEnum = new HashMap<>();

    static {
        Gender[] list = Gender.values();
        for(Gender item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    Gender(Integer code, String name) {
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

    public static Gender getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        Gender one = cacheEnum.get(code);
        if (one == null) {
           throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
