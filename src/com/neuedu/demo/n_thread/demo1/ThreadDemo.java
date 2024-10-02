package com.neuedu.demo.n_thread.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
    进程：就是计算机中运行的程序，每个进程都有自己独立的内存空间和系统资源。
    线程：是进程中用于实现执行某个逻辑（功能）的单元，它是CPU调度的基本单位，一个进程可以包含多个线程，这些线程共享进程的内存空间和资源。
    多线程：是指在一个程序中同时运行多个线程的能力。每个线程都是独立的执行路径，它们可以并发地执行代码，从而充分利用多核处理器或处理器中的多个执行单元。
    执行 main() 方法的线程⼀般被称为主线程（Main Thread）。
 */
/*-------------------- 创建线程的方式 -----------------------*/
// 1. 继承 Thread 类，重写 run() 方法，创建继承 Thread 类的对象，调用 start() 方法
class MyThread extends Thread{
    // 线程中的 run() 方法称为线程的任务方法，只有调用 start() 方法才能开启线程，去执行 run() 方法
    @Override
    public void run() {
        System.out.println("这里是线程运行的代码");
    }
}

// 2. 实现 Runnable 接口，重写 run() 方法，创建 Thread 类的对象，把实现 Runnable 接口的实现类对象传入 Thread 类的构造中，然后调用 start() 方法
class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("这里是线程运行的代码");
    }
}

// 3. 实现 Callable 接口，重写 call() 方法，使用 FutureTask 包装 Callable 接口的实现类，然后 FutureTask 对象传入 Thread 类的构造中，然后调用 start() 方法
// Callable<V> 是一个泛型接口，它中有一个泛型方法，这个方法当返回值结果出错时，引发异常
// Callable<V> 和 Runnable 的区别是：虽然都能创建线程，但是 Runnable 中的方法没有返回值，Callable<V> 中的方法有返回值（并且方法抛出异常）。
class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("这里是线程运行的代码，最后返回值返回线程任务的结果");
        return 1;
    }
}

// 4. 通过 Executors 类创建线程池，然后通过线程池去给线程分配任务。


public class ThreadDemo {
    public static void main(String[] args) {
        // 1. 继承 Thread 类，重写 run() 方法，创建继承 Thread 类的对象，调用 start() 方法
        MyThread myThread = new MyThread();
        myThread.start();

        // 2. 实现 Runnable 接口，重写 run() 方法，创建 Thread 类的对象，把实现 Runnable 接口的实现类对象传入 Thread 类的构造中，然后调用 start() 方法
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        // 3. 实现 Callable 接口，重写 call() 方法，使用 FutureTask 包装 Callable 接口的实现类，然后 FutureTask 对象传入 Thread 类的构造中，然后调用 start() 方法
        // 使用 FutureTask 包装 Callable（FutureTask 底层实现了 Runnable 接口）
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        // 创建线程并调用 start() 方法
        Thread thread1 = new Thread(futureTask);
        thread1.start();
    }
}
/*
    Thread 类中常见的构造方法：
      ● Thread()                              创建线程对象
      ● Thread(Runnable target)               使用 Runnable 对象创建线程对象
      ● Thread(String name)                   创建线程对象，并命名
      ● Thread(Runnable target, String name)  使用 Runnable 对象创建线程对象，并命名

    Thread 类中几个常见的方法
      ● start()                          使该线程开始执行，Java虚拟机会调用该线程的 run() 方法
      ● getId()                          ID，ID是线程的唯⼀标识，不同线程不会重复
      ● getName()                        名称，名称是各种调试工具用到
      ● getState()                       状态，状态表示线程当前所处的⼀个情况
      ● getPriority()                    优先级，优先级高的线程理论上来说更容易被调度到
      ● setDaemon(boolean on)            将此线程标记为守护线程（也叫后台线程，服务线程）
      ● setPriority(Thread.MAX_PRIORITY) 设置优先级大小
      ● isDaemon()                       是否守护线程，JVM 在⼀个进程的所有非守护线程结束后，无论守护线程是否结束，程序就结束运行了。
      ● isAlive()                        是否存活，run方法是否运行结束了
      ● interrupt()                      线程中断
      ● isInterrupted()                  是否被中断
      ● static Thread currentThread()    返回当前线程对象引用
      ● static void sleep(long millis)   休眠当前线程（以毫秒数为单位）
      ● stop()                           强制线程停止执行（该方法已弃用）

    在类 java.lang.Object 中声明的方法：
      ● clone()                  创建并返回此对象的副本
      ● equals(Object obj)       指示某个其他对象是否“等于”这个对象
      ● finalize()               通知 GC 垃圾回收（该方法已弃用）
      ● getClass()               返回 Object 的运行时类（就是 .class 文件）
      ● hashCode()               返回对象的哈希码值
      ● toString()               返回对象的字符串表示形式
      ● wait()                   导致当前线程等待直到被唤醒
      ● wait(long timeoutMillis) 导致当前线程等待直到它被唤醒，通常是被 notified 或 interrupted 唤醒，或者直到经过一定的实时时间
      ● notify()                 唤醒在此对象的监视器上等待的单个线程
      ● notifyAll()              唤醒在此对象的监视器上等待的所有线程

------------------------- 面试题 ----------------------------

   1.线程的生命周期：
     ● 创建（New）       使用 new 关键字创建一个线程对象
     ● 就绪（Runnable）  线程对象创建后，调用 start() 方法，进入就绪状态
     ● 运行（Running）   就绪状态的线程开始执行 run() 方法
     ● 阻塞（Blocked）   线程运行时被暂停（线程调用 sleep() 休眠当前线程；线程调用 wait() 导致当前线程等待直到被唤醒）
     ● 死亡（Dead）      run() 方法执行完成，线程正常结束；
                        线程抛出未捕获的异常或 Error；
                        调用线程的 stop() 方法强制线程停止执行。

   2.sleep() 和 wait() 有什么区别？
      ● 类的不同：sleep() 来⾃ Thread，wait() 来⾃ Object。
      ● 释放锁：  sleep() 不释放锁；wait() 释放锁。
      ● ⽤法不同：sleep() 时间到会⾃动恢复；wait() 可以使⽤ notify() / notifyAll() 直接唤醒。

   3.线程的 run() 和 start() 有什么区别？
     ● run() ⽅法是 Runnable 接⼝中的⽅法；直接调⽤ run() ⽅法，不会重新启动⼀个新的线程，⽽只是调⽤了线程中的 run() ⽅法，会这这段代码放在主线程中，顺序调⽤；
     ● start() ⽅法是 Thread 类中的⽅法；调⽤ start() ⽅法，会开启⼀个线程，执⾏ run() ⽅法中的代码.
     ● start() ⽅法⽤于启动线程，run() ⽅法⽤于执⾏线程的运⾏时代码。
     ● run() 可以重复调⽤，⽽ start() 只能调⽤⼀次.

   4.守护线程是什么？
      守护线程是运⾏在后台的⼀种特殊进程。它独⽴于控制终端，并且周期性地执⾏某种任务或等待处理某些发⽣的事件。
      在 Java 中垃圾回收线程就是特殊的守护线程。

   5.线程池中 submit() 和 execute() ⽅法有什么区别：
      ● execute()：只能执⾏ Runnable 类型的任务
      ● submit(): 可以执⾏ Runnable 和 Callable 类型的任务

 */
