import org.json.JSONObject;
public class Test9 {
  public static void main(String[] args) {
    String jsonStr = "{\"id\":12,\"timestamp\":\"2020-09-15T10:00:00.000+08:00\",\"temperature\":\"25.5\"}";
    JSONObject jsonObject = new JSONObject(jsonStr);
    int id = jsonObject.getInt("id");
    String timestamp = jsonObject.getString("timestamp");
    System.out.println(id);
  }
}
