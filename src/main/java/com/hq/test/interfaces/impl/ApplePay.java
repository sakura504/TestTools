package com.hq.test.interfaces.impl;

import com.hq.test.interfaces.Pay;
import org.springframework.stereotype.Service;

/**
 * @author lichaojie
 * @date 2022/9/9 14:12
 * @ClassName ApplePay
 **/
@Service(value = "ApplePay")
public class ApplePay implements Pay {

    @Override
    public void pay() {
        System.out.println("苹果支付...");
    }
}
