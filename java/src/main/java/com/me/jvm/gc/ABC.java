package com.me.jvm.gc;


/**
 * @author zs
 * @date 2022/1/6
 * 主要是为了模拟方法的调用栈
 * sleep 模拟了业务系统执行的时间
 * 在业务系统中会观察到业务系统执行的特别慢
 * 做一个压测调用 a()，设计的时候是达到 1w QPS，但实际当中只能做到 100 QPS，原因是 a() 调用特别慢，
 *  但是我们不知道系统的性能点是 a(), b() 还是 c()。有可能这个调用链非常长，到底是哪个调用链出问题了呢？
 *  这个例子是单机版的链路追踪。但是如果是微服务版，这个服务调用了另外机器上的服务（分布式链路追踪，使用 zipkin or skywalking）
 *  arthas （单机版）可以解决这种问题
 */
public class ABC {
    public static void main(String[] args) throws Exception {
        for (; ; ) {
            new ABC().a();
        }
    }

    public void a() throws Exception {
        Thread.sleep(1000);
        b();
    }

    public void b() throws Exception {
        Thread.sleep(2000);
        c();
    }

    public void c() throws Exception {
        Thread.sleep(3000);
    }
}
