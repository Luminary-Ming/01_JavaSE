package com.neuedu.demo.n_thread.demo3;
/*-------------------------------- 线程间通信 --------------------------------*/
/**
 * 线程的资源类
 */
class Resource {
    // 存储的容器
    private final Object[] data = new Object[1];
    // 同步锁
    private final Object lock = new Object();

    /**
     * 存储资源
     */
    public void setData(Object obj) {
        // 添加同步代码块，保证线程在存资源的时候，取资源的线程要等待；取资源的时候，存资源的线程要等待。
        synchronized (lock) {
            // 数据存到容器里
            data[0] = obj;
            System.out.println(Thread.currentThread().getName() + " -> 存储的数据 -> " + data[0]);
        }
    }

    /**
     * 获取资源
     */
    public void getData() {
        // 这个同步代码块使用的锁和上面同步代码块使用的锁一致，保证线程存的时候不能取；取的时候不能存。
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " <- 取走的数据 <- " + data[0]);
            // 数据被取走了
            data[0] = null;
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
        while(true){
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
        while(true){
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
        Thread thread1 = new Thread(setResourceThread);
        Thread thread2 = new Thread(getResourceThread);
        // 开启线程
        thread1.start();
        thread2.start();
    }
}