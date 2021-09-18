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
     * @Description 类型转化，如果为空强制变成0
     * @Date 2021/9/17
     * @Param type 转成后的类型
     * @param: value 转化的值
     * @return 返回转成后的类型的值
     * @notice 效率很低，如果可以包装类尽量初始化为0
     **/
    public final static <T> T convert(Class<T> type, Object value) throws ConvertException {
        if ((value instanceof String && value.equals("")) || value == null) {
            value = 0;
        }
        return convert((Type) type, value);
    }

    /**
     * @author: zs
     * @Description 转化 如果对象为null或者""，则转化为默认值，只能从value类型转到value类型。只是加了判空和“”的功能
     * @Date 2021/9/18
     * @Param type
     * @param: dval 默认值
     * @param: value 需要转化的值
     * @return
     **/
    public final static <T> T conv(Object dval, Object value) {
        if (value == null || (value instanceof String && value == "")) {
            value = dval;
        }
        if(value == null){
            return (T)dval;
        }
        if(value == ""){
            return (T) dval;
        }
        return (T) value;
//        return (T) (value == null ? dval : value);
    }


    public static void main(String[] args) {
        Float flo = null;
        Double dou = 0.0;
        String ss = "";
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
//            flo = flo == null ? 0 : flo; // 4792
            ss = conv("0", ss);
//            dou = convert(Double.class, dou); // 50809
        }
        long l1 = System.currentTimeMillis();
//        Float convert = MyConvert.convert(Float.class, 0);
        System.out.println(flo);
        System.out.println(l1 - l);
    }
}
