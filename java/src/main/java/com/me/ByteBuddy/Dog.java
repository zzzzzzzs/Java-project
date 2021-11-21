package com.me.ByteBuddy;

import cn.hutool.core.util.StrUtil;

public class Dog implements Animal{

    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void wakeup() {
        System.out.println(StrUtil.format("小狗[{}]早晨醒来啦",name));
    }

    @Override
    public void sleep() {
        System.out.println(StrUtil.format("小狗[{}]晚上睡觉啦",name));
    }
}
