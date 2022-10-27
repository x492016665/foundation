package com.pf.config;

import com.pf.config.property.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yk_xing
 */
@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties("system")
public class SystemConfig {

    /**
     * 文件存储
     */
    private LocalStoreProperty localStore;

    /**
     * 事务提醒
     */
    private ScheduleRemindProperty remind;

    /**
     * 登录信息
     */
    private LoginProperty login;

}
