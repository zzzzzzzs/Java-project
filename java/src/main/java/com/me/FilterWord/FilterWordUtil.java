package com.me.FilterWord;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveProcessor;
import cn.hutool.dfa.SensitiveUtil;
import cn.hutool.dfa.StopChar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author zs
 * @date 2021/12/8
 * 过滤词工具
 */
public class FilterWordUtil {

    static {
        // 将系统自带的停顿词清除
        StopChar.STOP_WORD.clear();
    }
    // 初始化过滤词
    public static void init(final Collection<String> sensitiveWords) {
        // false 为了确保过滤词加载完成
        SensitiveUtil.init(sensitiveWords, false);
    }

    // 将过滤词去掉
    public static String filter(String text, boolean isGreedMatch) {
        String s = SensitiveUtil.sensitiveFilter(text, isGreedMatch, new SensitiveProcessor() {
            @Override
            public String process(FoundWord foundWord) {
                int length = foundWord.getFoundWord().length();
                StringBuilder sb = new StringBuilder(length);
                return sb.toString();
            }
        });
        return s;
    }


    public static void main(String[] args) {
        String text = "下午好，好消息来啦 \uD83C\uDF89\n" +
                "辰颐物语公司出台新政策啦，加盟水果老板之前不是交338嘛，现在有第二个通道，现在免费加盟[呲牙] \n" +
                "\n" +
                "吃过我们水果的朋友我优先来通知，现在有100多个品种，要办个会员吗？";
        FileReader fileReader = new FileReader("filter-word.txt");
        List<String> list = fileReader.readLines();
        List<String> filterWordList = new ArrayList<>();
        filterWordList.addAll(list);
        // 加载过滤词
        FilterWordUtil.init(filterWordList);
        String filter = FilterWordUtil.filter(text, false);
        System.out.println(filter);
    }
}
