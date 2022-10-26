package com.pf.dao.enums;

import com.pf.utils.exception.SystemException;
import com.pf.utils.result.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件元数据
 * @author yk_xing
 */
public enum FileClassify {
    BILL_IMG(0, "单据图片"),
    SIGN(1, "签名"),
    HEAD(2, "头像"),
    CMS_IMG(3, "CMS图片"),
    BILL_FILE(4, "单据文件"),
    WXA(5, "小程序码");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, FileClassify> cacheEnum = new HashMap<>();

    static {
        FileClassify[] list = FileClassify.values();
        for (FileClassify item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
        }
    }

    FileClassify(Integer code, String name) {
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

    public static FileClassify getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        FileClassify one = cacheEnum.get(code);
        if (one == null) {
            throw new SystemException(StatusCode.SYSTEM_DATA_ERROR);
        }
        return one;
    }

    public boolean isImage() {
        return BILL_IMG == this || SIGN == this || HEAD == this || CMS_IMG == this || WXA == this;
    }
}
