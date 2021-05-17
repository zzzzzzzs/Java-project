---
title: EventListener监听器
date: 2021-02-20 00:07:53
tags: java
categories: java
---



监听器是一个包含方法的类，该类所包含的方法在发生某些操作时将被调用。`java.util.EventListener` 是所有监听器都应实现的标记接口（无任何方法的接口）。

实际上，它甚至没有添加任何功能，但是对于某些IDE和代码分析工具可能会有所帮助。

因此，如果要创建自己的自定义事件（触发），则需要在事件发生时以某种方式调用所有监听器方法。

例如，您可以像下面这样进行操作：

首先，为您的监听器创建一个扩展了的接口`EventListener`：

<!--more-->

MyListener.java

```java
public interface MyListener extends EventListener{
    void onEvent();
}
```

然后，您可以需要实现该接口MyListener

```java
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
```

然后，您将需要创建一种机制来注册和调用所有事件：

```java
private Collection<MyListener> listeners=new HashSet<>();
public void register(MyListener listener){
    listeners.add(listener);
}
public void eventHappens(){
    for(MyListener listener:listeners){
        listener.onEvent();
    }
}
```



您可以使用注册

```java
register(new MyListenerImpl("Event occurs"));
```

每当`eventHappens()`调用时（例如：收到网络数据包就可以调用它），所有注册的监听器都将被执行。

例如：

```java
register(new MyListenerImpl("listener 1 called"));
register(new MyListenerImpl("listener 2 called"));
System.out.println("calling event");
eventHappens();
```

会打印

> calling event
>
> listener 1 called
>
> listener 2 called

上述代码在：https://github.com/zzzzzzzs/Java-self-study





上述的过程主要是：

1、首先有一个继承`java.util.EventListener`的接口，该接口里面有一个没有实现的空方法。

2、实现该接口，将该接口里面的方法实现。

3、然后用一个集合注册、统一管理监听器。

4、写一个机制来调用所有事件。

> 要习惯在创建的对象中实现方法。有很多时候都是这么用的。





其实监听器是一种模式，更方便将程序抽象，遵循了ocp原则，方便维护。



有的时候会用到事件循环，来触发监听器。

Here is yuque doc card, click on the link to view:https://www.yuque.com/zhaoshuo-zrdce/go0yss/drtb7g



我看过的一个rxtxcomm的串口包，它的实现在native方法中实现了事件循环eventloop，读取到串口的数据，然后触发监听器。



Here is yuque doc card, click on the link to view:https://www.yuque.com/zhaoshuo-zrdce/iay8wp/rsca8d









参考链接：https://stackoverflow.com/questions/60931587/what-is-a-java-util-eventlistener