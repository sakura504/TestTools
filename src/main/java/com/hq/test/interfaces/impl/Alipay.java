package com.hq.test.interfaces.impl;

import com.hq.test.interfaces.Pay;
import org.springframework.stereotype.Service;

/**
 * @author lichaojie
 * @date 2022/9/9 14:11
 * @ClassName Alipay
 **/
@Service("Alipay")
public class Alipay implements Pay {

    @Override
    public void pay() {
        System.out.println("支付宝支付...");
    }
}
