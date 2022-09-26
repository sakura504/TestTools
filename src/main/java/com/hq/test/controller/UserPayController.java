package com.hq.test.controller;

import com.hq.test.interfaces.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lichaojie
 * @date 2022/9/9 14:08
 * @ClassName UserPayController
 **/
@RestController
@RequestMapping(value = "/user")
public class UserPayController {

    @Autowired
    private Map<String, Pay> payMap;

    @GetMapping(value = "/pay")
    public void pay(@RequestParam String key) {
        Pay pay = payMap.get(key);
        pay.pay();
    }
}
