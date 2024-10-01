package com.neuedu.demo.k_io.demo02;

import java.io.*;

public class ObjectDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        demo2();
    }

    private static void demo2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/person.obj"));
        Object o = ois.readObject();
        System.out.println(o);
    }

    private static void demo1() throws IOException {
        Person p = new Person(1, "zhangsan", 20, "男");
        System.out.println(p);
        // 将对象写到磁盘中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/person.obj"));
        oos.writeObject(p);
        oos.close();
    }
}
