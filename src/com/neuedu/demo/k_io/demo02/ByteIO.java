package com.neuedu.demo.k_io.demo02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteIO {
    public static void main(String[] args) throws IOException {
        demo4();
    }

    private static void demo4() throws IOException {
        FileInputStream fis = new FileInputStream("d:/abc.txt");
        // io流中读取文件数据的话，定义的数组容量一般是1024的整数倍
        byte[] buf = new byte[1024];
        // 每次读取的字节个数
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        fis.close();
    }

    private static void demo3() throws IOException {
        // 如果关联的文件不存在，这时程序会报错
        FileInputStream fis = new FileInputStream("d:/abc.txt");
        int a = 0;
        // 循环中的read，一次只能读取一个字节数据
        while ((a = fis.read()) != -1) {
            System.out.println(a);
        }
        fis.close();
    }

    private static void demo2() throws IOException {
        // 创建一个文件字节输出流，如果输出流关联的文件不存在，它会创建
        // 如果文件存在，并且文件中已经有数据，默认会覆盖
        FileOutputStream fos = new FileOutputStream("d:/abc.txt", true);
        fos.write(97);
        String str = "创建一个文件字节输出流";
        byte[] bytes = str.getBytes();
        fos.write(bytes);
        fos.close();
    }

    private static void demo1() throws IOException {
        // 创建一个文件字节输出流，如果输出流关联的文件不存在，它会创建
        FileOutputStream fos = new FileOutputStream("d:/abc.txt");
        fos.write(97);
        fos.write(98);
        fos.write(65);
        fos.write(66);
        // 字节输出流的write方法虽然接收的int值，但是本质只能写出一个字节数据
        // 如果给出的数据超过一个字节，最终只会写出二进制中最后8个二进制数位
        fos.write(353);
        // 关闭流对象
        fos.close();
    }
}
