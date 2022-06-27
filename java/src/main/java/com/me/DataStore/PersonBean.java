package com.me.DataStore;

import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonBean {
    String tableName;
    String tableAlias;
    String columnName;
    String columnAlias;
    Integer level;

    // 唯一标识符
    String uuid;
}
