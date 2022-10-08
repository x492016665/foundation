package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统账号
 * @author yk_xing
 */
public enum AccountType {
    TEL(0, "手机号"),
    EMAIL(1, "邮箱"),
    ALL(2, "自定义");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, AccountType> cacheEnum = new HashMap<>();

    static {
        AccountType[] list = AccountType.values();
        for(AccountType item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    AccountType(Integer code, String name) {
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

    public static AccountType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        AccountType one = cacheEnum.get(code);
        if (one == null) {
           throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
