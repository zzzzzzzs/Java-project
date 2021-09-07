package com.me.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author zs
 *@date 2021/8/6.
 * Properties 配置文件解析
 */
public class MyPropertiesUtil {

    public static Properties load(String propertieName) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(propertieName), "UTF-8"));
        return properties;
    }

    public static void main(String[] args) throws IOException {
        Properties load = MyPropertiesUtil.load("config.properties");
        System.out.println(load.getProperty("user-profile.dbname"));
    }
}
