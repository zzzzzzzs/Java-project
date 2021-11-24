package com.me.CostTime;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

/**
 * @author zs
 * @date 2021/11/24
 */
public class TimeIntervalTest {
    public static void main(String[] args) {
        final TimeInterval timer = new TimeInterval();
        // 分组1
        timer.start("1");
        ThreadUtil.sleep(1000);
        // 分组2
        timer.start("2");
        ThreadUtil.sleep(1000);
        Console.log("Timer 1 took {} ms", timer.intervalMs("1"));
        Console.log("Timer 2 took {} ms", timer.intervalMs("2"));
    }
}
