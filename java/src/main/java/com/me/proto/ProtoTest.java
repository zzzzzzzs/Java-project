package com.me.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class ProtoTest {
    public static void main(String[] args) {

        //初始化数据
        DemoProto.Demo.Builder demo = DemoProto.Demo.newBuilder();
        demo.setId(1)
                .setCode("001")
                .setName("张三")
                .build();

        //序列化
        DemoProto.Demo build = demo.build();
        //转换成字节数组
        byte[] s = build.toByteArray();
        System.out.println("protobuf数据bytes[]:" + Arrays.toString(s));
        System.out.println("protobuf序列化大小: " + s.length);

        DemoJava demoJava = new DemoJava();
        demoJava.setId(1);
        demoJava.setCode("001");
        demoJava.setName("张三");
        //转换成字节数组
        byte[] jj = toByteArray(demoJava);
        System.out.println("Java 对象数据bytes[]:" + Arrays.toString(jj));
        System.out.println("Java 对象序列化大小: " + jj.length);


        DemoProto.Demo demo1 = null;
        String jsonObject = null;
        try {
            //反序列化
            demo1 = DemoProto.Demo.parseFrom(s);
            //转 json
            jsonObject = JsonFormat.printer().print(demo1);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("Json格式化结果:\n" + jsonObject);
        System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);
    }

    /**
     * 将Object对象转byte数组
     * @param obj byte数组的object对象
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
