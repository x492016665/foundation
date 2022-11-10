package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志
 * @author yk_xing
 */
public enum OrderClassify {
    QUOTATION(1, "报价单"),
    DISPATCH(2, "派工单");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, OrderClassify> cacheEnum = new HashMap<>();

    static {
        OrderClassify[] list = OrderClassify.values();
        for (OrderClassify item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    OrderClassify(Integer code, String name) {
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

    public static OrderClassify getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        OrderClassify one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }
}
