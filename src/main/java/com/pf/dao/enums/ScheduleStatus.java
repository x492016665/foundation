package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 事项提醒
 * @author yk_xing
 */
public enum ScheduleStatus {
    UNDISPOSED(0, "未处理"),
    PROCESSED(1, "已处理");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, ScheduleStatus> cacheEnum = new HashMap<>();

    static {
        ScheduleStatus[] list = ScheduleStatus.values();
        for (ScheduleStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    ScheduleStatus(Integer code, String name) {
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

    public static ScheduleStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        ScheduleStatus one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
