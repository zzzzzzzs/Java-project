package com.me.EnumIfElse;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @author zs
 * @date 2021/7/28.
 * 使用枚举类干掉 if else 但是方法无法传参数
 * 也可以用配置文件
 */
public enum PayChannelEnum {
    ALIPAY("ALIPAY", new AliPay()),
    WECHATPAY("WECHATPAY", new WechatPay()),
    YINLIANPAY("YINLIANPAY", new YinlianPay()),
    ;

    public String channelName;
    public PayChannel payChannel;

    private static HashMap<String, PayChannelEnum> map = new HashMap<>();


    static {
        PayChannelEnum[] values = PayChannelEnum.values();
        for (PayChannelEnum value : values) {
            map.put(value.name(), value);
        }
    }


    PayChannelEnum(String channelName, PayChannel payChannel) {
        this.channelName = channelName;
        this.payChannel = payChannel;
    }

    public static PayChannelEnum match(String channelName) {
        return map.get(channelName);
    }

//    public static PayChannelEnum match(String channelName) {
//        PayChannelEnum[] values = PayChannelEnum.values();
//        for (PayChannelEnum value : values) {
//            String name = value.name();
//            if (name != null && name.equals(channelName)) {
//                return value;
//            }
//        }
//        return null;
//    }

}
