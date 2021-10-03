package com.me.similarity;
import org.junit.Test;
import org.xm.Similarity;
import org.xm.tendency.word.HownetWordTendency;
/**
 * @author zs
 *@date 2021/9/30.

 */
public class demo1 {
    @Test
    public void getTendency() throws Exception {
        HownetWordTendency hownet = new HownetWordTendency();
        String word = "喜欢";
        double sim = hownet.getTendency(word);
        System.out.println(word + ":" + sim);
//        System.out.println("混蛋:" + hownet.getTendency("混蛋"));
    }


    public static void main(String[] args) {
        double result = Similarity.cilinSimilarity("电动车", "自行车");
        System.out.println(result);

        String word = "混蛋";
        HownetWordTendency hownetWordTendency = new HownetWordTendency();
        result = hownetWordTendency.getTendency(word);
        System.out.println(word + "  词语情感趋势值：" + result);
    }
}
