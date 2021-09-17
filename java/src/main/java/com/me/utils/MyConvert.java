package com.me.utils;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConvertException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * @author: zs
 * @Description 类型类转
 * @Date 2021/9/17
 **/
@Slf4j
public class MyConvert extends Convert {

    /**
     * @author: zs
     * @Description 类型转化
     * @Date 2021/9/17
     * @Param type 转成后的类型
     * @param: value 转化的值
     * @return 返回转成后的类型的值
     **/
    public static <T> T convert(Class<T> type, Object value) throws ConvertException {
        value = value == null ? 0 : value;
        return convert((Type) type, value);
    }


    public static void main(String[] args) {
        Float convert = MyConvert.convert(Float.class, 0);
        System.out.println(convert);
    }
}
