package com.neuedu.demo.k_io.demo01;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        // 获取电脑上所有根目录（盘符）
        File[] files = File.listRoots();
        for (File file : files) {
            demo7(file);
        }
    }

    public static void demo1() {
        // 将 d:1.txt 这个字符串封装成 File 对象
        File file = new File("d:/test");
        // exists 判断file表示字符串所对应文件或文件夹是否真实存在
        System.out.println(file.exists());
        // isFile 判断file表示字符串对应的是否为文件
        System.out.println(file.isFile());
        // isFile 判断file表示字符串对应的是否为文件夹
        System.out.println(file.isDirectory());
        //  isFile 判断file表示字符串表示文件或文件夹是否属于隐藏类型
        System.out.println(file.isHidden());
    }


    private static void demo2() throws IOException {
        File file = new File("d:/1.txt");
        // 创建文件夹 , 只能创建单层目录
        boolean mkdir = file.mkdir();
        System.out.println(mkdir);
        file = new File("d:/2.txt");
        // 创建文件
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
    }


    private static void demo3() {
        String dirs = "d:/";
        for (int i = 0; i < 100; i++) {
            dirs = dirs + i + "/";
        }
        System.out.println(dirs);
        File file = new File(dirs);
        boolean mkdir = file.mkdirs();
        System.out.println(mkdir);
    }

    private static void demo4() throws IOException {
        File file = new File("d:/");
        // 获取文件或文件夹的名称
        System.out.println(file.getName());
        // 获取父目录:获取到的父目录是一个字符串
        System.out.println(file.getParent());
        // 获取父目录:获取到的父目录再次封装成另外一个File对象
        File parentFile = file.getParentFile();
        System.out.println(parentFile);

        // 获取file封装的字符串中表示的路径
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        // 获取File封装的字符串表示的真实的实际路径
        System.out.println(file.getCanonicalFile());
        System.out.println(file.getCanonicalPath());

        // 获取磁盘可用空间大小
        System.out.println(file.getUsableSpace());
        // 获取磁盘剩余空间大小
        System.out.println(file.getFreeSpace());
        // 获取磁盘总大小
        System.out.println(file.getTotalSpace());
    }


    private static void demo5() {
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file);
        }
    }


    private static void demo6() {
        File file = new File("d:/");
        // 获取指定目录（文件夹）中的内容（文件、文件夹），但是将这些内容的名称获取到放在一个字符串数组中
        //String[] list = file.list();
        //for (String s : list) {
        //    System.out.println(s);
        //}
        // 获取指定目录（文件夹）中的内容（文件、文件夹），但是将这些内容的分别封装成不同的File对象，存储在File数组中
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                System.out.println(f.getName() + "是一个文件");
            } else {
                System.out.println(f.getName() + "是文件夹");
            }
        }
    }

    private static void demo7(File file) {
        // 判断传递进来的file对象不能为null，同时必须是文件夹
        if (file != null && file.isDirectory()) {
            // 获取当前文件夹中的内容（文件、文件夹）
            File[] files = file.listFiles();
            // 遍历之前需要做个判断，防止指定的目录java没有权限访问，上面的数组为null
            if (files != null) {
                for (File f : files) {
                    // 判断当前遍历出来的file对象是文件还是文件夹
                    if (f.isDirectory()) {
                        demo7(f);
                    } else {
                        System.out.println(f);
                        // 删除文件，如果是文件夹，必须保证其中没有内容
                        //f.delete();
                    }
                }
            }
        }
    }
}
