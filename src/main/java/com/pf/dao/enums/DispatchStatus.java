package com.pf.dao.enums;

import cn.hutool.core.util.StrUtil;
import com.pf.utils.TextSimilarityHelper;
import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 派工单
 * @author yk_xing
 */
public enum DispatchStatus {
    TO_DISPATCH(1, "待派工"),
    DISPATCH(10, "已派工"),
    RECIVE(20, "已接单"),
    FINISH(30, "已完成"),
    CANCEL(40, "已取消");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, DispatchStatus> cacheEnum = new HashMap<>();

    private static final Map<String, DispatchStatus> cacheNameEnum = new HashMap<>();

    static {
        DispatchStatus[] list = DispatchStatus.values();
        for (DispatchStatus item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
            cacheNameEnum.put(item.name, item);
        }
    }

    DispatchStatus(Integer code, String name) {
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

    public static DispatchStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        DispatchStatus one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }

    public boolean isFinish() {
        return this == DispatchStatus.FINISH;
    }

    public boolean isUnFinish() {
        return this != DispatchStatus.FINISH;
    }

    public static List<Integer> getUnFinishStatus() {
        return Arrays.asList(TO_DISPATCH.getCode(), DISPATCH.getCode(), RECIVE.getCode());
    }

    public static Set<Integer> queryByName(String dispatchStatusStr) {
        if(StrUtil.isEmpty(dispatchStatusStr)) {
            return Collections.EMPTY_SET;
        }
        Set<String> names = cacheNameEnum.keySet();
        List<String> similars = TextSimilarityHelper.similar(dispatchStatusStr, names);
        return similars.stream().map(cacheNameEnum::get)
                .map(DispatchStatus::getCode)
                .collect(Collectors.toSet());
    }
}
