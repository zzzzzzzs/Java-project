package com.me.JsonExtra;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zs
 * @date 2021/11/4
 */
public class JsonExtraExample {
    public static void main(String[] args) {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("JsonTestFile.json");
        String jsonStr = fileReader.readString();
        JSONObject json = JSON.parseObject(jsonStr);
        JSONObject hits = json.getJSONObject("hits");
        JSONArray hits1 = hits.getJSONArray("hits");
        for (int i = 0; i < hits1.size(); i++) {
            JSONObject jsonObject = hits1.getJSONObject(i);
            String string = jsonObject.getJSONObject("_source").getString("server_msg_id");
            System.out.println(string);
        }
    }
}
