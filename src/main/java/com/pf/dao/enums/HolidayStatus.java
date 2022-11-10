package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户假期
 * @author yk_xing
 */
public enum HolidayStatus {
    REST(1, "休息中"),
    STOP(2, "终止");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, HolidayStatus> cacheEnum = new HashMap<>();

    static {
        HolidayStatus[] list = HolidayStatus.values();
        for (HolidayStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    HolidayStatus(Integer code, String name) {
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

    public static HolidayStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        HolidayStatus one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
