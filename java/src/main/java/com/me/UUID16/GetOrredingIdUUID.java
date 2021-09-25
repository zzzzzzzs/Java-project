package com.me.UUID16;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * @author zs
 *@date 2021/9/6.
java 生成 16 位 ID
 */
public class GetOrredingIdUUID {
    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

    @Test
    public void uuid(){
        String orderingID = getOrderIdByUUId();
        System.out.println(orderingID);
    }

    @Test
    public void uuid1(){
        long id = Thread.currentThread().getId();
        System.out.println(id);
    }

}
