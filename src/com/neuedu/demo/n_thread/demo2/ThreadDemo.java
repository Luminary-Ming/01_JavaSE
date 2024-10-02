package com.neuedu.demo.n_thread.demo2;


class MyRunnable implements Runnable {
    public static int i = 100;
    // 自定义的锁
    private static final Object lock = new Object();

    @Override
    public void run() {
        /*
            同步代码块：当某个线程进入 synchronized 修饰的代码块时，其他线程将无法进入，直到该线程执行完毕并释放锁。
            synchronized( 锁对象 ) 同步代码块中的锁对象是唯一的，这个锁可以是：
              ● synchronized (MyRunnable.class)  字节码文件对象，类只加载一次。
              ● synchronized (this)              也可以使用 this，但必须保证多个线程访问同步代码块时，这个锁 this 都是相同的对象。
              ● synchronized (lock)              自定一个对象，保证始终唯一。
         */
        synchronized (lock) {
            while (i > 0) {
                System.out.println(Thread.currentThread().getName() + "---> i= " + i);
            }
        }
    }

    /*-------------------------- 同步方法：使用 synchronized 关键字修饰的方法 ----------------------------------------*/
    /*
        同步方法：锁对象是 this
     */
    public synchronized void synchronizedFn() {
        while (i > 0) {
            System.out.println(Thread.currentThread().getName() + "---> i= " + i);
        }
    }

    /*
        静态同步方法：锁对象是类的字节码文件对象
     */
    public static synchronized void staticSynchronizedFn() {
        while (i > 0) {
            System.out.println(Thread.currentThread().getName() + "---> i= " + i);
        }
    }
}


public class ThreadDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        // 修改线程对象的名称，线程的默认名称是 Thread-0，Thread-1，Thread-2，.......
        thread1.setName("修改的名字aaa");
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
    }
}

/*
    线程的安全问题：
        CPU 在线程调度时是随机的，当多个线程同时共享一个全局变量，或者静态变量，并且执行写的操作时，可能会发生数据冲突的问题。

    解决线程安全问题的方式：
     ● 使用同步机制：使用 synchronized 关键字：它可以用于修饰方法或代码块。当某个线程进入 synchronized 修饰的方法或代码块时，
       其他线程将无法进入，直到该线程执行完毕并释放锁。
         ● 同步代码块：在需要同步的代码块前加上 synchronized 关键字，并指定一个锁对象。只有持有该锁对象的线程才能进入同步代码块执行。
         ● 同步方法：使用 synchronized 关键字修饰的方法，同步方法的锁对象是固定的，
             - 对于非静态方法，锁对象是 this；
             - 对于静态方法，锁对象是当前方法所属的类的字节码文件（即类对象）。


    什么是死锁？怎样防⽌死锁?
      ● 简短回答：死锁是指两个或多个线程在相互等待对方释放锁，从而导致都无法继续执行的情况。
      ● 举例回答：当线程 A 持有独占锁a，并尝试去获取独占锁 b 的同时，线程 B 持有独占锁 b，并尝试获取独占锁 a 的情况下，
        就会发⽣ AB 两个线程由于互相持有对⽅需要的锁，⽽发⽣的阻塞现象，我们称为死锁。
      防止死锁：
        1. 尽量减少同步的代码块。
        2. 减少锁的粒度：锁的粒度越细，系统的并发性能就越好。
           因此，我们应该尽量只同步那些真正需要同步的代码块或方法，而不是将整个方法或类都进行同步。
        3. 尽量使⽤ Java.util.concurrent 并发类代替⾃⼰⼿写锁。
        4. 尽量使⽤ tryLock(long timeout, TimeUnit unit)的⽅法(ReentrantLock、ReentrantReadWriteLock)，
           设置超时时间，超时可以退出防⽌死锁。

 */