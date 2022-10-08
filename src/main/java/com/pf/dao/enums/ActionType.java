package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 积分流水
 * @author yk_xing
 */
public enum ActionType {
    SUB(0, "减"),
    ADD(1, "加");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, ActionType> cacheEnum = new HashMap<>();

    static {
        ActionType[] list = ActionType.values();
        for (ActionType item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    ActionType(Integer code, String name) {
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

    public static ActionType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        ActionType one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
