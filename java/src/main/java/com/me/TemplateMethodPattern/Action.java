package com.me.TemplateMethodPattern;

public interface Action<T> {
    void eat(T o);
    void play(T o);
}
