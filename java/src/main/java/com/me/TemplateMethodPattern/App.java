package com.me.TemplateMethodPattern;

//TODO　在父类中定义实现某一个功能的核心算法的骨架，将具体实现延迟到子类中
// 在不改变父类中核心算法骨架前提下，每一个子类中都可以有自己不同的实现。
public class App {
    public static void main(String[] args) {
        Person<String> person = new Person<String>("zs") {
            @Override
            public void eat(String o) {
                System.out.println(this.name + "喜欢吃" + o);
            }

            @Override
            public void play(String u) {
                System.out.println(this.name + "喜欢玩" + u);
            }
        };
        person.doSomething("apple", "basketball");
    }
}
