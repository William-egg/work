package ThreadLrearn;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *1。  首先是进程和线程的区别
    * 进程是资源分配的基本单位，线程是CPU调度的基本单位。
    * 通信方面，进程需要IPC机制进行通信，而线程共享内存通信比较简单
    * 进程具有独立的资源
 * 2。  线程的创建
    * 继承Thread类
    * 实现Runnable接口
    * 使用Callable和FutureTask
    * 使用线程池
**/
public class main {
    static class MyThread extends Thread {
        @Override
        public void run() {
            getThreadInfo();
        }
    }
    public static void getThreadInfo() {
        Thread t = Thread.currentThread();
        System.out.println("Thread name: " + t.getName());
        System.out.println("Thread ID: " + t.getId());
        System.out.println("Thread state: " + t.getState());
        System.out.println("Thread priority: " + t.getPriority());
        System.out.println("Thread is alive: " + t.isAlive());
        System.out.println("======================");
    }
    public static void main(String[] args) throws Exception {
        // 1. 继承Tread类
        MyThread myThread = new MyThread();
        myThread.start(); // 启动线程
        myThread.join();
        System.out.println("main Thread is running");

        // 2. 实现Runnable接口
        Thread t2 = new Thread(()->{
            getThreadInfo();
        });
        t2.start();

        // 3. 使用Callable和FutureTask
        Callable myC = ()->{
          return Thread.currentThread().getName();
        };//因为这里是个接口类，可以直接这么干。
        FutureTask<String> futureTask = new FutureTask<>(myC);
        Thread t3 = new Thread(futureTask);
        t3.start();
        String s = futureTask.get(); // 阻塞主线程，直到子线程执行完毕
        System.out.println(s);
    }
}
