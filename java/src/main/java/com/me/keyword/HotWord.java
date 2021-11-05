package com.me.keyword;


import cn.hutool.core.io.file.FileReader;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author zs
 * @date 2021/10/30
 */
public class HotWord {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("Corpus.txt");
        List<String> lines = fileReader.readLines();

        FileReader hotWords = new FileReader("mydic.dic");
        List<String> hotWordsList = hotWords.readLines();

        Set<String> hotWordSet = new HashSet<>(hotWordsList);

        long startTime = System.currentTimeMillis();

        lines.parallelStream().forEach(new Consumer<String>() {
            @SneakyThrows
            @Override
            public void accept(String line) {
                List<String> splits = KeywordUtil.analyze(line);
                for (String ss : splits) {
                    if (hotWordSet.contains(ss)) {
                        System.out.println(line + "包含热词:" + ss);
                    }
                }
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println(lines.size() + "条数据使用了：" + (endTime - startTime) + "ms");

    }
}
