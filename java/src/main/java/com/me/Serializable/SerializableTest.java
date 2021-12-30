package com.me.Serializable;

import cn.hutool.core.io.file.FileWriter;
import com.me.bean.SerializableBean;

import java.io.*;

/**
 * @author zs
 * @date 2021/12/22
 * 这个例子证明了flink中clean()。如果外部类为null，那么可以序列化成功
 * 外部类为null，但是内部类不为null，gc是无法回收的。
 */
public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableBean serializableBean = new SerializableBean();
        // 测试，为null的时候不实现Serializable也可以序列化成功
//        serializableBean = null;
        //创建 ObjectOutputStream 输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\simeitol\\Desktop\\111.txt"));
        oos.writeObject(serializableBean);
        //创建一个ObjectInputStream()输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\simeitol\\Desktop\\111.txt"));
        SerializableBean t = (SerializableBean) ois.readObject();
        System.out.println(t);
    }
}
