package com.me.ByteBuddy;

import cn.hutool.core.util.StrUtil;

public class Cat implements Animal{

    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void wakeup() {
        System.out.println(StrUtil.format("小猫[{}]早晨醒来啦",name));
    }

    @Override
    public void sleep() {
        System.out.println(StrUtil.format("小猫[{}]晚上睡觉啦",name));
    }
}
