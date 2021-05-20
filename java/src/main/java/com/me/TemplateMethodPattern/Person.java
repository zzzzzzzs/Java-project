package com.me.TemplateMethodPattern;


public abstract class Person<T> implements Action<T> {
    public T name;

    public Person() {
    }

    public Person(T name) {
        this.name = name;
    }

    // TODO　这里也可以使用抽象方法，但是面写接口编程
    public void doSomething(T o, T u){
        eat(o);
        play(u);
    }

//    public abstract void eat(T o);

//    public abstract void play(T u);
}
