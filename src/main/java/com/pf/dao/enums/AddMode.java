package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 搬迁服务扩展
 * @author yk_xing
 */
public enum AddMode {
    SINGLE(1, "逐台添加"),
    MULTIPLE(2, "多台添加");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, AddMode> cacheEnum = new HashMap<>();

    static {
        AddMode[] list = AddMode.values();
        for (AddMode item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    AddMode(Integer code, String name) {
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

    public static AddMode getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        AddMode one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
