package com.hq.test.interfaces.impl;

import com.hq.test.interfaces.Pay;
import org.springframework.stereotype.Service;

/**
 * @author lichaojie
 * @date 2022/9/9 14:07
 * @ClassName WeiXin
 **/
@Service(value = "WeiXin")
public class WeiXin implements Pay {

    @Override
    public void pay() {
        System.out.println("微信支付...");
    }
}
