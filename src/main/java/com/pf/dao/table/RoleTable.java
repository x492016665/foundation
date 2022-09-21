package com.pf.dao.table;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pf.dao.entity.Role;

/**
 * 角色
 *
 * @author yk_xing
 */
public class RoleTable extends BaseEntityTable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    public static final String name = "NAME";

    /**
     * 角色类型(100-其他-DEFAULT|50-工程师-ENGINEER|51-技术客服-SERVICE|52-商务-BUSINESS|53-财务-FINANCE|2-管理员-ADMIN|1-超级管理员-SUPERADMIN)
     */
    public static final String role_type = "ROLE_TYPE";


    public static QueryWrapper<Role> getQueryWrapper(Role entity) {
        QueryWrapper<Role> wrapper = BaseEntityTable.getQueryWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    public static UpdateWrapper<Role> getUpdateWrapper(Role entity) {
        UpdateWrapper<Role> wrapper = BaseEntityTable.getUpdateWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    private static void wrapper(Compare wrapper, Role entity) {
        if (null != entity.getName()) {
            wrapper.eq(name, entity.getName());
        }
        if (null != entity.getRoleType()) {
            wrapper.eq(role_type, entity.getRoleType());
        }
    }
}
