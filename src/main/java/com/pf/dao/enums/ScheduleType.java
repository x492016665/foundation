package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 事项提醒
 * @author yk_xing
 */
public enum ScheduleType {
    QUOTED(0, "报价提醒"),
    DISPATCH_ENGINEER(10, "派工提醒工程师"),
    DISPATCH_SERVICE(11, "派工提醒客服"),
    INVOICE(20, "开票提醒"),
    PAYEE(30, "收款提醒"),
    STORAGE(40, "入库提醒");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, ScheduleType> cacheEnum = new HashMap<>();

    static {
        ScheduleType[] list = ScheduleType.values();
        for (ScheduleType item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    ScheduleType(Integer code, String name) {
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

    public static ScheduleType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        ScheduleType one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
