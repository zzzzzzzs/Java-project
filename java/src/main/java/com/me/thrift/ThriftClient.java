package com.me.thrift;


import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class ThriftClient {
    public static void main(String[] args) throws Exception {
        TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TCompactProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        transport.open();

        Person person = client.getPersonByUsername("李四");
        //转换成字节数组
        byte[] jj = toByteArray(person);
        System.out.println("thrift 对象数据bytes[]:" + Arrays.toString(jj));
        System.out.println("thrift 对象序列化大小: " + jj.length);

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
        System.out.println("---------------");

        Person person1 = new Person();
        person1.setUsername("李四");
        person1.setAge(30);
        person1.setMarried(true);
        client.savePerson(person1);
    }

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
