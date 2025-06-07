package com.neuedu.demo.h_exception;

public class Exception01 {
    public static void main(String[] args){
        try {
            int[] a = new int[2];
            System.out.println(a[3]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("数组下标越界：" + e);  // 数组下标越界：java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 2
        }
        System.out.println("Out of the block");  // Out of the block
    }
}
/*
    java中常见的异常：
    RuntimeException（运行时异常）：这类异常不需要在方法签名中声明，也不需要显式捕获
        ArrayIndexOutOfBoundsException
        IndexOutOfBoundsException
        NullPointerException
        ClassCastException  类型转换错误
        NumberFormatException
    Checked Exception（编译异常）：这类异常必须在方法签名中声明，或在方法内部捕获处理
        IOException
        SQLException
        ClassNotFoundException
        FileNotFoundException
 */