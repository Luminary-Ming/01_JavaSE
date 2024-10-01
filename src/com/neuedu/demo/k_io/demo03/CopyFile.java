package com.neuedu.demo.k_io.demo03;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        demo2();
    }

    private static void demo2() throws IOException {
        BufferedInputStream bufr = new BufferedInputStream(new FileInputStream("d:/1.zip"));
        BufferedOutputStream bufo = new BufferedOutputStream(new FileOutputStream("e:/111.zip"));
        Long start = System.currentTimeMillis();
        int ch = 0;
        while ((ch = bufr.read()) != -1) {
            bufo.write(ch);
        }
        // 获取当前系统时间
        Long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start));
        bufo.close();
        bufr.close();
    }

    public static void demo1() throws IOException {
        FileInputStream fis = new FileInputStream("d:/1.zip");
        FileOutputStream fos = new FileOutputStream("e:/111.zip");
        // 获取当前系统时间
        Long start = System.currentTimeMillis();
        int ch = 0;
        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }
        // 获取当前系统时间
        Long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start));
        fos.close();
        fis.close();
    }
}
