package com.hq.test.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author lichaojie
 * @date 2021/11/1 15:41
 * @ClassName Task
 **/
@Configuration
@EnableScheduling
public class Task {


    /**
     * 每天凌晨0点触发
     */
    @Scheduled(cron = "0/5 10 8 * * ?")
    public void test() {
        System.out.println(LocalDateTime.now());
    }
}
