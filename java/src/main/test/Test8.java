import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author zs
 * @date 2021/9/26.
 */
public class Test8 {
  public static void main(String[] args) {
    String url = "https://jk-mgw-h.simeitol.com/mgr/admin/myjk/qywx/getQyUserInfoByQyUserid";
    HashMap<String, String> headers = new HashMap<>();
    headers.put("myjk-pt", "pc_h5_glht");
    headers.put("myjk-ct", "h5");
    headers.put("myjk-version", "1.5.1.0");
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("qywxAppCode", "swplus");
    paramMap.put("qyUserid", "wmQx6jJwAAkrQiWMdC7iUC7BGX_F8inQ");
    String result = HttpUtil.createGet(url).addHeaders(headers).form(paramMap).execute().body();
    JSONObject jsonObject = JSONObject.parseObject(result);
    JSONObject external_contact = jsonObject.getJSONObject("external_contact");

    System.out.println(result);
  }
}
