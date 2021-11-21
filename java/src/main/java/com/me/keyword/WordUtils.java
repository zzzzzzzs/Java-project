package com.me.keyword;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.dictionary.Dictionary;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.recognition.StopWord;
import org.apdplat.word.segmentation.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zs
 * @date 2021/11/8
 */
public class WordUtils {

    private static void test1(String text) {
        //移除停用词进行分词 不要stopwords.txt里面的词汇
        List<Word> word1 = WordSegmenter.seg(text);
        //保留停用词
        List<Word> word2 = WordSegmenter.segWithStopWords(text);
        for (Word ww : word1) {
            System.out.println(ww.getText());
        }
        System.out.println("-------------");
        for (Word ww : word2) {
            System.out.println(ww.getText());
        }
    }

    private static void test2(String text) {
        while (true) {
            long start = System.currentTimeMillis();
            List<Word> word1 = WordSegmenter.seg(text);
            long cost = System.currentTimeMillis() - start;
            System.out.println("花费：" + cost + "----" + word1);
        }
    }
    private static void test3() {
        DictionaryFactory.getDictionary().clear();
        System.out.println(DictionaryFactory.getDictionary().getMaxLength());
        List<String> list = new ArrayList<>();
        list.add("你好");
        list.add("我也好");
        DictionaryFactory.getDictionary().addAll(list);
        System.out.println(DictionaryFactory.getDictionary().contains("你好"));
    }

    // TODO word bug：空指针，后面有空指针
    private static void testBug() {
        List<Word> word1 = WordSegmenter.seg("姿美堂S姐（优米） ");
    }
    public static void main(String[] args) {
//        testBug();
//        test1("不合情理");
//        test2("不合情理");
        test3();
    }
}

