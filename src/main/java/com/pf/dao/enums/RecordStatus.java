package com.pf.dao.enums;

/**
 * @author yk_xing
 */

public enum RecordStatus {
    VALID(1, "有效"),
    INVALID(0, "无效");

    private Integer code;

    private String name;

    RecordStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCode() {
        return this.code;
    }
}
