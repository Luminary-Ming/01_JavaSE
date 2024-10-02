package com.neuedu.demo.n_thread.demo7;
/*
    线程的优先级：优先级越高，线程越优先被执行。

    当一个线程（我们称之为“创建线程”）创建了一个新的 Thread 对象时，这个新线程的优先级默认被设置为等于创建线程的优先级。
    这意味着，如果你在一个优先级为5的线程中创建了一个新的线程，那么这个新线程的优先级也将是5。
 */
class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
        // 新线程的优先级默认等于创建线程的优先级
        Thread thread3 = new Thread(new MyRunnable2());
        thread3.start();
        System.out.println(thread3.getPriority());  // 1 或 10
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable2 myRunnable = new MyRunnable2();
        // 创建线程
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        // 设置线程优先级
        thread1.setPriority(Thread.MIN_PRIORITY);  // 1
        thread2.setPriority(Thread.MAX_PRIORITY);  // 10
        // 开启线程
        thread1.start();
        thread2.start();
    }
}