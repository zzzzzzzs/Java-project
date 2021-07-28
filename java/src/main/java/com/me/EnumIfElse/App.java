package com.me.EnumIfElse;

/**
 * @author zs
 * @date 2021/7/28.
 * 使用Enum代替 if else，当然也可以直接使用HashMap
 * 有一个问题就是无法传参数
 */
public class App {
    public static void main(String[] args) {
        String channelName = "ALIPAY";
        // 使用 if else 的方法
        if(channelName.equals("ALIPAY")){
            new AliPay().process();
        }else if (channelName.equals("WECHATPAY")){
            new WechatPay().process();
        }else {
            new YinlianPay().process();
        }

        PayChannelEnum match = PayChannelEnum.match(channelName);
        match.payChannel.process();

    }
}
