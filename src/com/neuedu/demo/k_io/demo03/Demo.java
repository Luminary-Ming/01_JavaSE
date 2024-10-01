package com.neuedu.demo.k_io.demo03;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        demo2();
    }

    private static void demo2() throws IOException {
        //FileOutputStream fos = new FileOutputStream("d:/fw2.txt");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:/fw2.txt"), "gbk");
        osw.write("@Date 2024/9/11 18:20");
        osw.flush();
        osw.close();
    }

    private static void demo1() throws IOException {
        //FileReader fr = new FileReader("d:/fw.txt");
        //FileInputStream fis = new FileInputStream("d:/fw.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream("d:/fw.txt"), "utf-8");
        char[] cbuf = new char[1024];
        int len = 0;
        while ((len = isr.read(cbuf)) != -1) {
            System.out.println(new String(cbuf, 0, len));
        }
        isr.close();
        //fis.close();
    }
}
