package com.pf.dao.table;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pf.dao.entity.User;

/**
 * 系统用户
 *
 * @author yk_xing
 */
public class UserTable extends BaseEntityTable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户姓名
     */
    public static final String user_name = "USER_NAME";

    /**
     * 手机号
     */
    public static final String tel = "TEL";

    /**
     * 当前状态(0-已注销-DISABLED|1-工作中-WORKING|2-休息-REST)
     */
    public static final String user_status = "USER_STATUS";

    /**
     * 区域
     */
    public static final String discode = "DISCODE";

    /**
     * 标签
     */
    public static final String label = "LABEL";

    /**
     * 账号id
     */
    public static final String account_id = "ACCOUNT_ID";


    public static QueryWrapper<User> getQueryWrapper(User entity) {
        QueryWrapper<User> wrapper = BaseEntityTable.getQueryWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    public static UpdateWrapper<User> getUpdateWrapper(User entity) {
        UpdateWrapper<User> wrapper = BaseEntityTable.getUpdateWrapper(entity);
        wrapper(wrapper, entity);
        return wrapper;
    }

    private static void wrapper(Compare wrapper, User entity) {
        if (null != entity.getUserName()) {
            wrapper.eq(user_name, entity.getUserName());
        }
        if (null != entity.getTel()) {
            wrapper.eq(tel, entity.getTel());
        }
        if (null != entity.getUserStatus()) {
            wrapper.eq(user_status, entity.getUserStatus());
        }
        if (null != entity.getDiscode()) {
            wrapper.eq(discode, entity.getDiscode());
        }
        if (null != entity.getLabel()) {
            wrapper.eq(label, entity.getLabel());
        }
        if (null != entity.getAccountId()) {
            wrapper.eq(account_id, entity.getAccountId());
        }
    }
}
