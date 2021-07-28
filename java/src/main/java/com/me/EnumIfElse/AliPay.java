package com.me.EnumIfElse;

/**
 * @author zs
 * @date 2021/7/28.
 */
public class AliPay extends PayChannel {

    @Override
    public void process() {
        System.out.println("支付宝支付渠道。。。");
    }
}
