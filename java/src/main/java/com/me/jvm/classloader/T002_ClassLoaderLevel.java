package com.me.jvm.classloader;

/**
 * @author zs
 * @date 2022/1/10
 */

public class T002_ClassLoaderLevel {
    public static void main(String[] args) {
        //查看是谁load到内存的，执行结果null，为什么为空呢，Bootstrap使用c++实现的，Java里并没有class和他对应
//        System.out.println(String.class.getClassLoader());
//        //这个是我们核心类库某个包里的类执行结果null，为什么他也是空值呢，因为他也是被我们Bootstrap类加载器加载的，这个时候到了最顶层了
//        System.out.println(sun.awt.HKSCS.class.getClassLoader());
//        //这个类是位于ext目录下某个jar文件里面,当你调用它执行结果也就是sun.misc.launcher$ExtClassLoader
//        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
//        //这个是我们自己写的ClassLoader加载器，它是由sun.misc.launcher$AppClassLoader加载的
//        System.out.println(T002_ClassLoaderLevel.class.getClassLoader());
//        //他是一个Ext的ClassLoader调用他的getclass（）他本身也是一个class，然后调用他的getClassLoader，他的ClassLoader又是谁，就这个ClassLoader的ClassLoader使我们最顶级的ClassLoaderBootstrap，执行结果为null
//        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
//        System.out.println(T002_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());
    }
}

