public class Test1 {
  String store(String str) {
    String replace = str.replace(",", ";").replace("\"", "");
    return replace;
  }
  String[] load(String str) {
    return str.split(";");
  }

  public static void main(String[] args) {

  }
}
