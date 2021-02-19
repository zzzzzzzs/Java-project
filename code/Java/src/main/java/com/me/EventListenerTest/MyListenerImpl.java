package com.me.EventListenerTest;

import java.util.Collection;
import java.util.HashSet;

class MyListenerImpl implements MyListener {
    private String msg;
    public MyListenerImpl(String msg){
        this.msg=msg;
    }
    @Override
    public void onEvent(){
        System.out.println(msg);
    }
}

class MainTest{
    private static Collection<MyListener> listeners=new HashSet<>();
    public static void register(MyListener listener){
        listeners.add(listener);
    }
    public static void eventHappens(){
        for(MyListener listener:listeners){
            listener.onEvent();
        }
    }
    public static void main(String[] args) {
        register(new MyListenerImpl("listener 1 called"));
        register(new MyListenerImpl("listener 2 called"));
        System.out.println("calling event");
        eventHappens();
    }
}
