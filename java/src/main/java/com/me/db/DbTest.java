package com.me.db;

import cn.hutool.core.map.MapUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @date 2021/12/6
 */
public class DbTest {
    public static void main(String[] args) throws SQLException {
        String sql = "select * from ods.t_wx_msg limit @num";
        Map<String, Object> paramMap = MapUtil.builder("num", (Object)10).put("id", "1").build();
        List<Entity> query = Db.use().query(sql, paramMap);
        System.out.println(query);
        int i = Db.use().execute("update user set age = ? where name = ?", 3, "张三");

    }
}
