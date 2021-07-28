package com.me.EnumIfElse;

/**
 * @author zs
 * @date 2021/7/28.
 */
public class WechatPay extends PayChannel {

    @Override
    public void process() {
        System.out.println("微信支付渠道。。。");
    }
}
