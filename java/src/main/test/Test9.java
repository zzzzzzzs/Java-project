// 一致性哈希


import com.google.common.graph.GraphBuilder;

public class Test9 {
  public static void main(String[] args) {
      GraphBuilder<Object> directed = GraphBuilder.directed();
      directed.allowsSelfLoops(false);
  }
}
