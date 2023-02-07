package com.pf.config.aspect.privilege;

import org.springframework.core.annotation.AliasFor;

/**
 * @author yk_xing
 */
public @interface Privilege {

    @AliasFor("value")
    PrivilegeType type() default PrivilegeType.PRIVILEGE_POINT;

    @AliasFor("type")
    PrivilegeType value() default PrivilegeType.PRIVILEGE_POINT;

}
