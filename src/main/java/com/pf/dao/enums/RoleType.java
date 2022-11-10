package com.pf.dao.enums;

import com.pf.dao.entity.Role;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色
 * @author yk_xing
 */
public enum RoleType {
    DEFAULT(100, "其他"),
    ENGINEER(50, "工程师"),
    SERVICE(51, "技术客服"),
    BUSINESS(52, "商务"),
    FINANCE(53, "财务"),
    ADMIN(2, "管理员"),
    SUPERADMIN(1, "超级管理员");

    private Integer code;

    private String name;

    private static final Map<Integer, String> cacheName = new HashMap<>();

    private static final Map<Integer, RoleType> cacheEnum = new HashMap<>();

    private static final List<RoleType> defaultRoles = new ArrayList<>();

    private static final Role SUPER_ADMIN_ROLE = new Role();

    private static final Role ADMIN_ROLE = new Role();

    private static final Set<Long> adminRoleIds = new HashSet<>(2);

    static {
        RoleType[] list = RoleType.values();
        for (RoleType item : list) {
            cacheName.put(item.code, item.name);
            cacheEnum.put(item.code, item);
            if (item.getCode() >= RoleType.ENGINEER.getCode()) {
                defaultRoles.add(item);
            }
        }

        RoleType superAdmin = RoleType.SUPERADMIN;
        SUPER_ADMIN_ROLE.setRoleType(superAdmin.getCode());
        SUPER_ADMIN_ROLE.setId(new Long(superAdmin.getCode()));
        SUPER_ADMIN_ROLE.setName(superAdmin.getName());

        adminRoleIds.add(new Long(superAdmin.getCode()));

        RoleType admin = RoleType.ADMIN;
        ADMIN_ROLE.setRoleType(admin.getCode());
        ADMIN_ROLE.setId(new Long(admin.getCode()));
        ADMIN_ROLE.setName(admin.getName());

        adminRoleIds.add(new Long(admin.getCode()));
    }

    RoleType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Set<Long> getAdminRoleIds() {
        return adminRoleIds;
    }

    public static boolean isInner(Long roleId) {
        if (roleId == null) {
            return false;
        }
        return getNameByCode(roleId.intValue()) != null;
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

    public static RoleType getByCode(Integer code) {
        return cacheEnum.get(code);
    }

    public static List<RoleType> defaultRoleList() {
        return defaultRoles;
    }

    public static List<Integer> defaultRoleCodeList() {
        return defaultRoleList().stream()
                .map(RoleType::getCode)
                .collect(Collectors.toList());
    }

    public static Role getSuperAdminRole() {
        return SUPER_ADMIN_ROLE;
    }

    public static Role getAdminRole() {
        return ADMIN_ROLE;
    }

    public static boolean isAdminRole(Long roleId) {
        return ADMIN_ROLE.getId().equals(roleId);
    }

    public static boolean isSuperAdminRole(Long roleId) {
        return SUPER_ADMIN_ROLE.getId().equals(roleId);
    }

    public static boolean isAdminRole(List<Long> roleIds) {
        if (roleIds == null) {
            return false;
        }
        return roleIds.stream().anyMatch(RoleType::isAdminRole);
    }

    public static boolean isSuperAdminRole(List<Long> roleIds) {
        if (roleIds == null) {
            return false;
        }
        return roleIds.stream().anyMatch(RoleType::isSuperAdminRole);
    }

    public static boolean isService(List<Long> roleIds) {
        if (roleIds == null) {
            return false;
        }
        return roleIds.stream().anyMatch((x) -> new Long(SERVICE.getCode()).equals(x));
    }

    public static boolean isEnginner(List<Long> roleIds) {
        if (roleIds == null) {
            return false;
        }
        return roleIds.stream().anyMatch((x) -> new Long(ENGINEER.getCode()).equals(x));
    }
}
