package com.me.DataStructure.NFA;

@FunctionalInterface
public interface Handler<T> {
    T func(T t);
}
