package com.neuedu.demo.n_thread.demo1;

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("第二个线程执行->" + i);
        }
    }
}

public class ThreadDemo2 {
    // 执行 main() 方法的线程⼀般被称为主线程（Main Thread）
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());  // main
        new MyThread2().start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程执行->" + i);
        }
    }
}
/*
    运行结果是两个线程交错执行，具体细节是主线程程进入 main 方法，随代码向下执行，
    走到 new MyThread().start(); 创建了第二个线程，并且调用 start() 方法开启了线程，
    第二个线程开始执行 run() 方法，此时主线程走到了 for 循环中也开始执行循环，
    这时 CPU 就会不停切换这两个线程执行，结果就是两个线程交错执行。
 */