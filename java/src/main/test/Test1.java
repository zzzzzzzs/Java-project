import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Test1 {

  public static void main(String[] args) {
    Table<String, String, String> table = HashBasedTable.create();
    table.put("a", "b", "c");
    System.out.println(table);
  }
}
