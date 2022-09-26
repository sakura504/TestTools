package com.hq.test.config;

import com.hq.test.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lichaojie
 * @date 2021/11/1 16:49
 * @ClassName UserConfig
 **/
@Configuration
public class UserConfig {

    @Bean
    public UserService userService(){
        return new UserService();
    }
}
