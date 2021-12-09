package com.me.crawler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.List;

/**
 * @author zs
 * @date 2021/12/8
 */
class CrawlerBingDemo {
    public static void main(String[] args) {
        String s = HttpUtil.get("https://cn.bing.com/search?q=%E8%A6%81%E6%89%BE%E5%88%B0%E9%82%A3%E4%BA%9B%E6%8B%90%E7%82%B9%EF%BC%8C%E5%8F%AF%E4%BB%A5%E9%80%9A%E8%BF%87%E7%8C%9C%E6%B5%8B%E4%B8%80%E4%B8%AA%E5%88%9D%E5%A7%8B%E7%82%B9%EF%BC%8C%E7%84%B6%E5%90%8E%E7%94%A8%E5%B8%B8%E7%94%A8%E7%9A%84%E6%A2%AF%E5%BA%A6%E4%B8%8B%E9%99%8D%E6%B3%95%E6%88%96%E8%80%85%E9%AB%98%E6%96%AF%E7%89%9B%E9%A1%BF%E6%B3%95%E6%B1%82%E8%A7%A3%E3%80%82");
        List<String> all = ReUtil.findAll("<li class=\"b_algo\".*?>.*?</li>", s, 0);
        for (int i = 0; i < all.size(); i++) {
            System.out.println("asdasdasdsadasdas:" + i);
            System.out.println(all.get(i));
        }
        System.out.println(all.size());
    }
}
