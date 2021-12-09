package com.me.poi;


import cn.hutool.core.io.file.FileReader;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zs
 * @date 2021/12/9
 */
public class POIUtil {

    // 读取 word 内容
    public static List<String> read(InputStream is, String suffix) throws IOException {
        List<String> content = null;
        if ("doc".equals(suffix)) {
            WordExtractor extractor = new WordExtractor(is);
            content = Arrays.asList(extractor.getParagraphText());
        } else if ("docx".equals(suffix)) {
            XWPFDocument document = new XWPFDocument(is);//使用XWPF组件XWPFDocument类获取文档内容
            content = document.getParagraphs().stream().map(xwpfParagraph -> xwpfParagraph.getText()).collect(Collectors.toList());
        }
        return content;
    }

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("D:\\mess\\2_20151704053-赵硕-TDOA三维定位算法设计与实现.docx");
        InputStream is = fileReader.getInputStream();
        List<String> read = POIUtil.read(is, "docx");
        for (String ele : read) {
            System.out.println(ele);
        }
    }
}