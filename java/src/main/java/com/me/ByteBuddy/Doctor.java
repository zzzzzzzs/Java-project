package com.me.ByteBuddy;

import cn.hutool.core.util.StrUtil;

public class Doctor implements Person{

    private String name;

    public Doctor() {
    }

    public Doctor(String name) {
        this.name = name;
    }

    @Override
    public void wakeup() {
        System.out.println(StrUtil.format("医生[{}]早晨醒来啦", name));
    }

    @Override
    public void sleep() {
        System.out.println(StrUtil.format("医生[{}]晚上睡觉啦", name));
    }
}
