package com.pf.dao.table;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pf.dao.entity.UserRole;

/**
 * 用户角色
 *
 * @author yk_xing
 */
public class UserRoleTable extends BaseEntityTable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    public static final String role_id = "ROLE_ID";

    /**
     * 用户id
     */
    public static final String user_id = "USER_ID";


    public static QueryWrapper<UserRole> getQueryWrapper(UserRole entity) {
        QueryWrapper<UserRole> wrapper = BaseEntityTable.getQueryWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    public static UpdateWrapper<UserRole> getUpdateWrapper(UserRole entity) {
        UpdateWrapper<UserRole> wrapper = BaseEntityTable.getUpdateWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    private static void wrapper(Compare wrapper, UserRole entity) {
        if (null != entity.getRoleId()) {
            wrapper.eq(role_id, entity.getRoleId());
        }
        if (null != entity.getUserId()) {
            wrapper.eq(user_id, entity.getUserId());
        }
    }
}
