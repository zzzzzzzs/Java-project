package com.me.utils.GetResource;

import com.me.keyword.WordUtils;

/**
 * @author zs
 * @date 2021/11/8
 */
public class GetResourceUtil {
    public static void main(String[] args) {
        String path = WordUtils.class.getResource("/").toString();
        System.out.println("path = " + path);
    }
}
