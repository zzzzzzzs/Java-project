package com.me.io;
import static com.me.io.IOUtils.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @description: 测试：QPS 131
 * 使用线程池做优化，让Poller线程，处理Selector的线程解放出来去做其他工作：处理读写事件即可
 * 线程池用于处理耗时的操作，这样就将两个Poller线程解脱出来了，他们不需要阻塞去执行复杂业务
 *
 * 主线程：等待客户端链接，接收到请求后将请求放入到Poller中
 * Poller线程：处理读写事件，并将复杂的耗时操作放到线程池中
 * 线程池：用于处理耗时操作，处理完毕后将数据和通道注册到Poller中的Selector中
 *
 */
public class NIOWithSelectorTPEDemo {
  private static final int POLLER_NUM = 1;
  private static Poller[] pollers;
  private static ThreadPoolExecutor poolExecutor = buildThreadPoolExecutor();


  public static ServerSocketChannel createServerSocketChannel() throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(true);
    serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT), BACK_LOG);
    return serverSocketChannel;
  }

  public static void initPoller() throws IOException {
    pollers = new Poller[POLLER_NUM];
    for (int i = 0; i < pollers.length; i++) {
      Poller poller = new Poller();
      poller.init();
      pollers[i] = poller;
    }
  }

  public static void startPoller() {
    for (int i = 0; i < pollers.length; i++) {
      pollers[i].start();
    }
  }

  public static class Poller extends Thread {
    private Selector selector;
    private BlockingQueue<SocketChannel> socketChannelBlockingQueue = new LinkedBlockingQueue<>();
    private AtomicBoolean atomicBoolean = new AtomicBoolean();

    @Override
    public void run() {
      while(true) {
        try {
          while(true) {
            SocketChannel socketChannel = socketChannelBlockingQueue.poll();
            if (socketChannel != null) {
              poolExecutor.execute(() -> {
                try {
                  socketChannel.configureBlocking(false);
                  doSomeWork();
                  String resp = buildHttpResp();
                  socketChannel.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(resp.getBytes()));
                } catch (Exception e) {
                  e.printStackTrace();
                }
              });
              continue;
            }
            break;
          }
          atomicBoolean.set(true);
          selector.select(1000);
          Set<SelectionKey> selectionKeys = selector.selectedKeys();
          Iterator<SelectionKey> iterator = selectionKeys.iterator();
          while (iterator.hasNext()) {
            SelectionKey next = iterator.next();
            if (next.isWritable()) {
              SocketChannel sc = (SocketChannel) next.channel();

              try {
                sc.write((ByteBuffer) next.attachment());
              } catch (Exception e) {
              }
            } else {
              System.out.println("未知SelectionKey");
            }
            iterator.remove();
          }
        } catch (Exception e) {
        }
      }
    }

    public void init() throws IOException {
      selector = Selector.open();
    }

    public void addSocketChannel(SocketChannel socketChannel) {
      try {
        socketChannelBlockingQueue.put(socketChannel);
        if (atomicBoolean.get()) {
          selector.wakeup();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public static void main(String[] args) throws Exception {
      ServerSocketChannel serverSocketChannel = createServerSocketChannel();
      System.out.println("启动服务器");
      initPoller();
      startPoller();
      poolExecutor.prestartAllCoreThreads();
      long count = 0;
      int m = pollers.length - 1;


      for (; ; ) {
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println(socketChannel.getRemoteAddress());
        pollers[(int) (count++ & m)].addSocketChannel(socketChannel);
      }


    }
  }
}
