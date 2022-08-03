
package com.me.Calcite;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.rel.RelWriter;
import org.apache.calcite.rel.externalize.RelWriterImpl;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlExplainLevel;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperatorTable;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.fun.SqlLibrary;
import org.apache.calcite.sql.fun.SqlLibraryOperatorTableFactory;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.statistic.MapSqlStatisticProvider;
import org.apache.calcite.tools.*;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

/**
 * @author zs
 * @date 2022/3/4
 */
public class CalciteDateAdd {
  public static void main(String[] args) throws SqlParseException, ValidationException, RelConversionException, SQLException {
    SchemaPlus rootSchema = Frameworks.createRootSchema(true);
    ReflectiveSchema schema = new ReflectiveSchema(new CalciteDateAdd());
    SchemaPlus hr = rootSchema.add("HR", schema);
    SqlParser.Config insensitiveParser = SqlParser.configBuilder()
            .setCaseSensitive(false)
            .build();
    SqlOperatorTable opTab =
            SqlLibraryOperatorTableFactory.INSTANCE.getOperatorTable(
                    EnumSet.of(SqlLibrary.STANDARD,SqlLibrary.POSTGRESQL));
    FrameworkConfig config = Frameworks.newConfigBuilder()
            .statisticProvider(MapSqlStatisticProvider.INSTANCE)
            .parserConfig(insensitiveParser)
            .defaultSchema(hr)
            .operatorTable(opTab)
            .build();
    Planner planner = Frameworks.getPlanner(config);
    SqlNode sqlNode = planner.parse("SELECT REVERSE('abc')");
    System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
    SqlNode sqlNodeValidated = planner.validate(sqlNode);
    System.out.println(sqlNodeValidated.toSqlString(OracleSqlDialect.DEFAULT));
    RelRoot relRoot = planner.rel(sqlNodeValidated);
    RelNode relNode = relRoot.project();
    final RelWriter relWriter = new RelWriterImpl(new PrintWriter(System.out), SqlExplainLevel.ALL_ATTRIBUTES, false);
    relNode.explain(relWriter);
    PreparedStatement run = RelRunners.run(relNode);
    ResultSet resultSet = run.executeQuery();

    System.out.println("Result:");
    while (resultSet.next()) {
      for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
        System.out.print(resultSet.getObject(i)+",");
      }
      System.out.println();
    }
  }
}
