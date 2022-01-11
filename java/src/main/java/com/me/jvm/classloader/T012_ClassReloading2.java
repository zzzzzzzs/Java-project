package com.me.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zs
 * @date 2022/1/11
 * 热部署小例子
 * class 改了之后，要想重新加载，重新调用一下 loadClass 就 OK 了
 */
public class T012_ClassReloading2 {
    private static class MyLoader extends ClassLoader {
        // 自定义 loadClass 打破双亲委派机制
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File f = new File("C:/work/ijprojects/JVM/out/production/JVM/" + name.replace(".", "/").concat(".class"));

            // 如果没找到，让父亲去 load，如果找到了自己去 load
            if(!f.exists()) return super.loadClass(name);

            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws Exception {
        MyLoader m = new MyLoader();
        Class clazz = m.loadClass("com.mashibing.jvm.Hello");

        m = new MyLoader();
        Class clazzNew = m.loadClass("com.mashibing.jvm.Hello");

        System.out.println(clazz == clazzNew); // false
    }
}
