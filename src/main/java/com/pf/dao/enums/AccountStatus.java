package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统账号
 * @author yk_xing
 */
public enum AccountStatus {
    DISABLED(0, "已注销"),
    ENABLE(1, "启用");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, AccountStatus> cacheEnum = new HashMap<>();

    static {
        AccountStatus[] list = AccountStatus.values();
        for(AccountStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    AccountStatus(Integer code, String name) {
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

    public static AccountStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        AccountStatus one = cacheEnum.get(code);
        if (one == null) {
           throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
