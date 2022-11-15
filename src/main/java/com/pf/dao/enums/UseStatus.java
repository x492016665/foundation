package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 维护保养备件使用情况
 * @author yk_xing
 */
public enum UseStatus {
    NOUSE(0, "未使用"),
    USED(1, "使用"),
    REPAIR(3, "坏件归还维修库"),
    STORE(4, "好件归还成品库");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, UseStatus> cacheEnum = new HashMap<>();

    static {
        UseStatus[] list = UseStatus.values();
        for (UseStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    UseStatus(Integer code, String name) {
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

    public static UseStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        UseStatus one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
