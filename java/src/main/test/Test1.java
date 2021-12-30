import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * @author zs
 *@date 2021/9/14.

 */
public class Test1 {

    public static void main(String[] args) throws IOException, InterruptedException {



        String url = "https://simeitol-app.oss-cn-hangzhou.aliyuncs.com/ecommerce/pro/pcm/img/微信图片_20211216111414.jpg";
        byte[] bytes = IOUtils.toByteArray(url);
        OutputStream out = new FileOutputStream("\u202AC:\\Users\\simeitol\\Desktop");
        InputStream is = new ByteArrayInputStream(bytes);
        byte[] buff = new byte[1024];
        int len = 0;
        while((len=is.read(buff))!=-1){
            out.write(buff, 0, len);
        }
        is.close();
        out.close();
    }
}

