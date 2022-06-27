package com.me.DataStore;

import com.google.common.base.Function;
import com.jparams.store.Store;
import com.jparams.store.index.Index;
import com.jparams.store.index.KeyMapper;
import com.jparams.store.memory.MemoryStore;
import com.jparams.store.query.Query;

import java.util.Optional;

public class DataStoreTest {
  public static void main(String[] args) {
    Store<PersonBean> dataStore = new MemoryStore();
    dataStore.index("tableName", PersonBean::getTableName);
    dataStore.index("tableAlias", PersonBean::getTableAlias);
    dataStore.index("columnName", PersonBean::getColumnName);
    dataStore.index("columnAlias", PersonBean::getColumnAlias);
    dataStore.index("level", PersonBean::getLevel);
    dataStore.index("uuid", PersonBean::getUuid);
    KeyMapper<String, PersonBean> getColumnAlias = PersonBean::getColumnAlias;
    System.out.println(getColumnAlias);

    dataStore.add(
        new PersonBean("info", "t1", null, null, 1, "d79c5b07-4f33-41a4-88d1-001bceb51431"));
    dataStore.get(
        Query.where("uuid", "d79c5b07-4f33-41a4-88d1-001bceb51431")
            .and("tableName", "info")
            .and("tableAlias", "t1"));
    System.out.println(dataStore.get(Query.where("columnName", null)));
  }
}
