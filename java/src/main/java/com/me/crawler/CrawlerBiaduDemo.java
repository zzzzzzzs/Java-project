package com.me.crawler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.List;

/**
 * @author zs
 * @date 2021/12/8
 */
class CrawlerBiaduDemo {
    public static void main(String[] args) {
        String s = HttpUtil.get("https://www.baidu.com/s?wd=%E7%AC%AC%E4%B8%80%EF%BC%9A%E5%88%86%E6%9E%90%E8%B0%83%E7%A0%94TDOA%E5%AE%9A%E4%BD%8D%E7%AE%97%E6%B3%95%E7%A0%94%E7%A9%B6%E5%BA%94%E7%94%A8%E7%8E%B0%E7%8A%B6%E3%80%82");
                                                       // class="result c-container new-pmd"
        List<String> title1 = ReUtil.findAll("<div class=\"result.*?c-container new-pmd.*?>.*?</div>", s, 0);
        for (int i = 0; i < title1.size(); i++) {
            System.out.println("asdasdasdsadasdas:" + i);
            System.out.println(title1.get(i));
        }

        System.out.println(title1.size());
    }
}
