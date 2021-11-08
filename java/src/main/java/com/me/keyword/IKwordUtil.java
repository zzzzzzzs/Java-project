package com.me.keyword;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2021/10/30
 * 分词工具类
 */
public class IKwordUtil {
    public static List<String> analyze(String text) throws IOException {
        List keywordList = new ArrayList();
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
        Lexeme lexeme;
        while ((lexeme = ikSegmenter.next()) != null) {
            keywordList.add(lexeme.getLexemeText());
        }
        return keywordList;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = analyze("动漫迷");
        System.out.println(list);
    }
}
