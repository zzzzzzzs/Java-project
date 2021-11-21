package com.me.ByteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ByteBuddyProxy {

    private Object bean;

    public ByteBuddyProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy() throws Exception{
        Object object = new ByteBuddy().subclass(bean.getClass())
                .method(ElementMatchers.namedOneOf("wakeup","sleep"))
                .intercept(InvocationHandlerAdapter.of(new AopInvocationHandler(bean)))
                .make()
                .load(ByteBuddyProxy.class.getClassLoader())
                .getLoaded()
                .newInstance();
        return object;
    }

    public class AopInvocationHandler implements InvocationHandler {

        private Object bean;

        public AopInvocationHandler(Object bean) {
            this.bean = bean;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            if (methodName.equals("wakeup")){
                System.out.println("早安~~~");
            }else if(methodName.equals("sleep")){
                System.out.println("晚安~~~");
            }
            return method.invoke(bean, args);
        }
    }
}
