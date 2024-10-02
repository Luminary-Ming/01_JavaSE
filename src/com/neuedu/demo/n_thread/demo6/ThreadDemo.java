package com.neuedu.demo.n_thread.demo6;
/*-------------------------------- 线程间通信之 ———— Lock 接口和 Condition 接口 --------------------------------*/
/*
    为什么将 wait()、notify()、notifyAll() 方法定义在 Object 类中，而不是定义在 Thread 类中呢？
    答：线程在操作共享数据的时候，一般都是在同步代码块块或同步方法中，就意味着线程已经获取到了锁对象，只有锁对象清楚目前
    哪个线程在执行同步代码块中的代码，所以可以通过锁对象让这个线程进入等待状态，而在同步代码块中锁对象可以是任意唯一的对象，
    所以等待和唤醒的方法就只能定义在 Object 类中。

    在 JDK5 之前，线程之间的通信只能通过 wait()、notify()、notifyAll() 方法来完成。
    线程中的安全问题只能通过同步代码块或同步方法解决，而使用 synchronized 时，锁的操作
    是隐式的，获取锁和释放锁都是由 JVM 来完成。

    针对以上同步锁的获取和释放的隐式问题，JDK5 中的 Lock 接口提供了获取锁和释放锁的方法：
      ● lock()         获取锁
      ● unlock()       释放锁
      ● newCondition() 返回绑定到此 Lock 实例的新 Condition 实例
    针对线程之间的通信问题，JDK5 中使用 Condition 接口来代替 Object 类中的 wait()、notify()、notifyAll() 方法：
      ● await()        使当前线程等待，直到被唤醒
      ● signal()       唤醒一个等待线程
      ● signalAll()    唤醒所有等待的线程
    不同的 Condition 对象可以监听不同的线程，不同的 Condition 对象可以唤醒不同的线程。
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程的资源类
 */
class Resource {
    // 存储的容器，设置 100 个容器
    private final Object[] data = new Object[100];
    /*
        getIndex  取数据的下标
        setIndex  存数据的下标
        count     容器中的数据个数
     */
    private int getIndex, setIndex, count;
    // 创建 Lock 接口的实现类的对象
    private final Lock lock = new ReentrantLock();
    // 返回 Lock 接口实例绑定的监听器，用来监听存储数据的线程
    private final Condition setCondition = lock.newCondition();
    // 返回 Lock 接口实例绑定的监听器，用来监听获取数据的线程
    private final Condition getCondition = lock.newCondition();

    /**
     * 存储资源
     */
    public void setData(Object obj) {
        // 调用 lock() 方法获取锁
        lock.lock();
        try {
            // 当容器存储的数据存满时，则存储资源的线程等待，让获取资源的线程执行
            while (count == data.length) {
                try {
                    // 让存储数据的线程进入等待状态
                    setCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 数据存到容器里
            data[setIndex] = obj;
            System.out.println(Thread.currentThread().getName() + " -> 存储的数据 -> " + data[setIndex]);
            // 存数据下标 ++；数据个数 ++
            setIndex++;
            count++;
            // 如果数据存满了，则让存数据的下标归零，再重新存数据
            if (setIndex == data.length) {
                setIndex = 0;
            }
            // 存储数据的线程存储数据之后，唤醒获取数据的线程
            getCondition.signalAll();
        } finally {
            // 调用 unlock() 方法获取锁
            lock.unlock();
        }
    }

    /**
     * 获取资源
     */
    public void getData() {
        // 调用 lock() 方法获取锁
        lock.lock();
        try {
            // 当容器中没有资源时，则获取资源的线程等待，让存储资源的线程执行
            while (count == 0) {
                try {
                    // 让获取数据的线程进入等待状态
                    getCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " <- 取走的数据 <- " + data[getIndex]);
            // 数据被取走了
            data[getIndex] = null;
            // 取数据下标 ++；数据个数 --
            getIndex++;
            count--;
            // 如果数据取完了，则让取数据的下标归零，再重新取数据
            if (getIndex == data.length) {
                getIndex = 0;
            }
            // 获取数据的线程存储数据之后，唤醒存储数据的线程
            setCondition.signalAll();
        }finally{
            // 调用 unlock() 方法获取锁
            lock.unlock();
        }
    }
}

/**
 * 存储资源的线程
 */
class SetResourceThread implements Runnable {
    private final Resource resource;
    private int i = 1;

    public SetResourceThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            // 调用 setData() 方法存储资源
            resource.setData("数据 " + i);
            i++;
        }
    }
}

/**
 * 获取资源的线程
 */
class GetResourceThread implements Runnable {
    private final Resource resource;

    public GetResourceThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            // 调用 getData() 方法获取资源
            resource.getData();
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        // 创建资源类对象
        Resource resource = new Resource();
        // 创建线程任务类对象，传入同一个 Resource 资源类对象，保证存储资源和获取资源是在同一个容器中
        SetResourceThread setResourceThread = new SetResourceThread(resource);
        GetResourceThread getResourceThread = new GetResourceThread(resource);
        // 创建线程对象
        Thread thread0 = new Thread(setResourceThread);
        Thread thread1 = new Thread(setResourceThread);
        Thread thread2 = new Thread(getResourceThread);
        Thread thread3 = new Thread(getResourceThread);
        // 开启线程
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}