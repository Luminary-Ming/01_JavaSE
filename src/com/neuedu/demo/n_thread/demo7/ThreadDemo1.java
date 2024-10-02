package com.neuedu.demo.n_thread.demo7;

/*
    线程的分类：
     用户线程，也叫普通线程
     守护线程，也叫后台线程，服务线程。GC 垃圾回收就是守护线程。
     前台线程，也叫主线程。

    JVM 在⼀个进程的所有非守护线程结束后，无论守护线程是否结束，程序就结束运行了。

    默认情况下，新创建的线程是用户线程，不是守护线程。
    但是，如果创建线程是一个守护线程，那么新创建的线程也将是一个守护线程。
    换句话说，守护线程创建的线程仍然是守护线程。
 */
class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
        // 守护线程创建的线程仍然是守护线程
        Thread thread3 = new Thread(new MyRunnable1());
        thread3.start();
        System.out.println(thread3.isDaemon());  // true
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        MyRunnable1 myRunnable = new MyRunnable1();
        // 创建线程
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        // 设置线程为守护线程
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        // 开启线程
        thread1.start();
        thread2.start();
        // 主线程执行的循环任务
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }
}
