package com.me.ByteBuddy;



/**
 * @author zs
 * @date 2021/11/19
 */
public class DynamicByteBuddyProxyTestMain {
    public static void main(String[] args) throws Exception{
        ByteBuddyProxy proxy = new ByteBuddyProxy(new Student("张三"));
        Student student = (Student) proxy.getProxy();
        student.wakeup();
        student.sleep();

        proxy = new ByteBuddyProxy(new Doctor("王教授"));
        Doctor doctor = (Doctor) proxy.getProxy();
        doctor.wakeup();
        doctor.sleep();

        proxy = new ByteBuddyProxy(new Dog("旺旺"));
        Dog dog = (Dog) proxy.getProxy();
        dog.wakeup();
        dog.sleep();

        proxy = new ByteBuddyProxy(new Cat("咪咪"));
        Cat cat = (Cat) proxy.getProxy();
        cat.wakeup();
        cat.sleep();
    }
}
