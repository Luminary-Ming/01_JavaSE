package com.neuedu.demo.k_io.demo02;

import java.io.*;

public class CharIO {
    public static void main(String[] args) throws IOException {
        demo5();
        PrintStream out = System.out;
        out.println(123);
        /*
            String s = new String("aaa");
            byte[] b = {97,98,99,100,65,66};
            String s2 = new String(b);
            String s3 = new String(b , 2 , 2);
         */
    }

    private static void demo5() throws IOException {
        FileWriter fw = new FileWriter("d:/fw.txt");
        fw.write("你好，字符串写出去");
        // 字符流写数据的时候，底层是将字符转成字节然后写到字节缓冲区中，并没有真正的写到底层文件中
        // 手动关闭流对象，手动调用刷新缓冲区，缓冲区写满了 这三种情况才会将缓冲区中的字节真正写出去
        fw.flush(); // 刷新缓冲区

        fw.write("你好，字符串写出去");
        fw.flush(); // 刷新缓冲区

        fw.write("你好，字符串写出去");
        fw.flush(); // 刷新缓冲区

        // 最后一定要关闭流对象
        fw.close();
    }

    private static void demo4() throws IOException {
        FileReader fr = new FileReader("d:/abc.txt");
        char[] cbuf = new char[1024];
        int len = 0;
        while ((len = fr.read(cbuf)) != -1) {
            // 处理cbuf中读取到的字符数据
            System.out.println(new String(cbuf, 0, len));
        }
        fr.close();
    }

    private static void demo3() throws IOException {
        FileReader fr = new FileReader("d:/abc.txt");
        int ch = 0;
        while ((ch = fr.read()) != -1) {
            System.out.println(ch);
        }
        fr.close();
    }

    private static void demo2() throws IOException {
        FileReader fr = new FileReader("d:/abc.txt");
        // 一次只能读取一个字符
        int ch = fr.read();
        System.out.println(ch);
    }


    private static void demo1() throws IOException {
        FileInputStream fis = new FileInputStream("d:/abc.txt");
        int ch = 0;
        while ((ch = fis.read()) != -1) {
            System.out.println(Integer.toBinaryString(ch & 0xFF));
        }
    }
}
