package com.neuedu.test;

/**
 * 基本数据类型   包装类               父类
 * byte	    java.lang.Byte      （父类Number）
 * short	java.lang.Short     （父类Number）
 * int	    java.lang.Integer   （父类Number）
 * long	    java.lang.Long      （父类Number）
 * float	java.lang.Float     （父类Number）
 * double	java.lang.Double    （父类Number）
 * boolean	java.lang.Boolean   （父类Object）
 * char	    java.lang.Character （父类Object）
 *
 * 基本数据类型的变量没有默认值，而包装类型的变量默认值是 null。
 */
public class NumberTest {
    public static void main(String[] args) {

        /**
         * public Type(type value)（Java9后已经过时）
         * Integer n1 = new Integer(123);
         * 所有包装类型 都能与 自己的基本数据类型 相互转换。
         *
         * public Type(String value)（Java9后已经过时）
         * Integer n2 = new Integer("123");
         * 除 Character 类型外，其余包装类都能把 String类型的字符串转成对应的包装类
         */
        Integer n1 = new Integer(123);
        Integer n2 = new Integer("123");
        Boolean f1 = new Boolean(true);
        Character c1 = new Character('a');


        /** Integer 转 String */
        // 方法一：Integer.toString(num) 传一个int 或 Integer 类型的数字
        String s1 = Integer.toString(5);
        // 方法二：调用 toString()方法 Integer类型的变量调用 toString()方法
        Integer i = 5;
        String s2 = i.toString();
        // 方法三：String.valueOf(num) 传一个int 或 Integer 类型的数字
        String s3 = String.valueOf(6);
        // 方法四：拼接字符串
        System.out.println(i + "");

        /** int 转 String */
        // 方法一：拼接字符串
        String s4 = 5 + "";
        // 方法二：Integer.toString(num) 传一个int 类型的数字
        String s5 = Integer.toString(7);
        // 方法三：String.valueOf(num) 传一个int 类型的数字
        String s6 = String.valueOf(7);

        /** String 转 int */
        // 方法一：Integer.parseInt(String str) 传一个字符串
        int n4 = Integer.parseInt("123");
        // 方法二：Integer.valueOf(String str) 传一个字符串
        int n5 = Integer.valueOf("123");


        /**
         * Byte、Short、Integer、Long、Float、Double 类中的常用方法：
         *
         * String toString(type i, int radix)（Float、Double没有此方法）
         * String toString(type i)
         * String toString()
         *
         * type parseType(String s, int radix)（Float、Double没有此方法）
         * type parseType(String s)
         *
         *
         * Type valueOf(String s, int radix)（Float、Double没有此方法）
         * Type valueOf(String s)
         * Type valueOf(type i)
         *
         * Byte、Short、Integer、Long、Float、Double 类转以下基本数据类型（都不能转char、boolean）
         * byte byteValue()
         * short shortValue()
         * int intValue()
         * long longValue()
         * float floatValue()
         * double doubleValue()
         */


        /**
         * Boolean 类中的常用方法：
         *
         * String toString(boolean b)
         * String toString()
         *
         * boolean parseBoolean(String s)
         *
         * Boolean valueOf(boolean b)
         * Boolean valueOf(String s)
         *
         * Boolean 包装类转自己的基本数据类型
         * boolean booleanValue()
         *
         */


        /**
         * Character 类中的常用方法：
         *
         * String toString(char c)
         * String toString()
         *
         * Character valueOf(char c)
         *
         * Character 包装类转自己的基本数据类型
         * char charValue()
         */

    }
}
