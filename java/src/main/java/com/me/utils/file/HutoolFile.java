package com.me.utils.file;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author zs
 *@date 2021/8/9.

 */
public class HutoolFile {
    public static String parseSoId(String soId) {
        String key = soId;
        String[] split = soId.split(",");
        for (String str : split) {
            if (!str.contains(":")) {
                key = str;
            }
        }
        return key.split(",")[0];

    }

    //    public static void main(String[] args) {
//        String ss = "4437718961659966,11063152:4437718961659966";
//        String s = parseSoId(ss);
//        System.out.println(s);
//    }
    public static void main1(String[] args) {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("C:\\Users\\simeitol\\Desktop\\simeitolPARAM_FORM_ERROR2021-10-28.txt");
        List<String> lines = fileReader.readLines();
        for (String ele : lines) {
//            if (!ele.equals("") && ele.contains(":")) {
//                String[] split = ele.split(",");
//                for (int i = 0; i < split.length; i++) {
//                    if (!split[i].contains(":")) {
//                        ele = split[i].split(",")[0];
//                    }
//                }
//            }
            String s = parseSoId(ele);
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("C:\\Users\\simeitol\\Desktop\\simeitolPARAM_FORM_ERROR2021-10-28.txt");
        String ss = fileReader.readString();
        JSONArray objects = JSON.parseArray(ss);
        for (int i = 0; i < objects.size(); i++) {
            //通过数组下标取到object，使用强转转为JSONObject，之后进行操作
            JSONObject object = (JSONObject) objects.get(i);
            System.out.println(object);
        }
    }
}

