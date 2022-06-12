package com.me.Calcite;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

/**
 * @author zs
 * @date 2022/3/4
 */
public class CalciteTest {
  public static void main(String[] args) {
    SchemaPlus rootSchema = Frameworks.createRootSchema(true);
    final FrameworkConfig config =
        Frameworks.newConfigBuilder()
            .parserConfig(
                SqlParser.configBuilder()
                    .setParserFactory(SqlParserImpl.FACTORY)
                    .setCaseSensitive(false)
                    .setQuoting(Quoting.BACK_TICK)
                    .setQuotedCasing(Casing.TO_UPPER)
                    .setUnquotedCasing(Casing.TO_UPPER)
                    .setConformance(SqlConformanceEnum.DEFAULT)
                    .build())
            .build();

    String sql =
        "with su_tb as (select t2.user_id, t2.data_id, t1.status_label, t1.quality_label, t1.temperature_label, t2.create_time\n"
            + "               from dam_meta_data t1\n"
            + "                        left join dam_meta_data_subscribe t2 on t1.id = t2.data_id),\n"
            + "     co_tb as (select t2.user_id, t2.data_id, t1.status_label, t1.quality_label, t1.temperature_label, t2.create_time\n"
            + "               from dam_meta_data t1\n"
            + "                        left join dam_meta_data_collection t2 on t1.id = t2.data_id)\n"
            + "select su_tb.status_label,\n"
            + "       su_tb.quality_label,\n"
            + "       su_tb.temperature_label,\n"
            + "       su_tb.create_time as su_time,\n"
            + "       co_tb.create_time as co_time\n"
            + "from su_tb,\n"
            + "     co_tb\n"
            + "where su_tb.user_id = co_tb.user_id\n"
            + "  and su_tb.data_id = co_tb.data_id";
    SqlParser parser = SqlParser.create(sql, config.getParserConfig());
    try {
      SqlNode sqlNode = parser.parseStmt();

      // 然后解析出来的sqlNode中的属性就可以了
      System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
