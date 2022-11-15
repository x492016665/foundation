package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户回访单
 * @author yk_xing
 */
public enum VisitStatus {
    TO_VIST(1, "待回访"),
    VISTING(2, "回访中"),
    VIST_END(3, "回访结束");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, VisitStatus> cacheEnum = new HashMap<>();

    static {
        VisitStatus[] list = VisitStatus.values();
        for (VisitStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    VisitStatus(Integer code, String name) {
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

    public static VisitStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        VisitStatus one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
