package com.neuedu.demo.l_reflect.test;

import com.neuedu.demo.l_reflect.pojo.Son;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 Java 反射（reflect）：是一种强大的机制，它允许程序运行时通过分析类的字节码文件（.class文件）来访问和操作类和类的对象中的属性和方法。
 这在很多框架和库中被广泛使用，例如Spring框架的依赖注入。（编译时：源代码编译成字节码的过程，运行时：JVM加载字节码的过程）

 在 jdk 中使用 Class 类来表示任意被加载的 .class 文件，每个 .class 文件都是 Class 这个类的一个实例。

 java.lang.reflect 是 Java 反射机制的核心包，提供了多种类和接口，来操作类和类的对象中的属性和方法的。
    1. java.lang.Class：表示类的对象，提供了获取类信息的方法，如属性、方法、构造函数等。
        ● getFields()：                          获取本类和父类中的所有共有属性
        ● getDeclaredFields()：                  获取本类中的所有属性，包括私有属性
        ● getMethods()：                         获取本类所有公共方法，包括父类中的公有方法
        ● getDeclaredMethods()：                 获取本类所有声明的方法，包括私有方法。
        ● getConstructors()：                    获取本类所有公共构造函数。
        ● getDeclaredConstructors()：            获取本类所有声明的构造函数，包括私有构造函数。
        ● getSuperclass()：                      获取类的父类。
        ● getInterfaces()：                      获取类实现的所有接口。

    2. java.lang.reflect.Field：表示类的属性，提供了访问和修改属性值的方法。
        ● get(Object obj)：                      获取指定对象的属性值。
        ● set(Object obj, Object value)：        设置指定对象的属性值。
        ● getType()：                            获取属性的数据类型。
        ● getModifiers()：                       获取属性的修饰符（如 public、private）。

    3. java.lang.reflect.Method：表示类的方法，提供了调用方法的能力。
        ● invoke(Object obj, Object... args)：   调用指定对象的方法。
        ● getReturnType()：                      获取方法的返回类型。
        ● getParameterTypes()：                  获取方法的参数类型。
        ● getModifiers()：                       获取方法的修饰符（如 public、private）。

    4. java.lang.reflect.Constructor：表示类的构造函数，提供了创建对象的能力。
        ● newInstance(Object... param)：         创建一个新实例，使用指定的构造函数参数。
        ● getParameterTypes()：                  获取构造函数的参数类型。
        ● getModifiers()：                       获取构造函数的修饰符（如 public、private）。



*/
public class ReflectDemo {
    @Test
    public void demo01() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        System.out.println(sonClass);  // class com.neuedu.demo.l_reflect.pojo.Son

        Class<?> listClass = Class.forName("java.util.List");
        System.out.println(listClass);  // interface java.util.List

        // 通过当前类的对象获取这个类对应的 Class 类的对象（字节码文件对象）
        Son son = new Son();
        Class<? extends Son> sonClass2 = son.getClass();

        // 通过 类.class 属性获取这个类对应的 Class 类的对象
        Class<Son> sonClass3 = Son.class;
    }

    @Test
    public void demo02() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // 获取 Son.class 文件中的公有成员变量（包括父类中的公有属性）
        Field sonPublic = sonClass.getField("sonPublic");
        // jdk 8 创建类对象，'newInstance()' is deprecated 已被弃用
        Object obj = sonClass.newInstance();
        // jdk9 以上推荐使用来创建类对象，基于构造创建对象
        Object obj2 = sonClass.getDeclaredConstructor().newInstance();
        sonPublic.set(obj2, "亚索");
        System.out.println(obj2);
    }

    @Test
    public void demo03() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // 获取 Son.class 文件中的所有成员变量（包括私有属性）
        Field sonPrivate = sonClass.getDeclaredField("sonPrivate");
        // 取消对私有属性权限的访问检查
        sonPrivate.setAccessible(true);
        Object obj = sonClass.getDeclaredConstructor().newInstance();
        sonPrivate.set(obj, "永恩");
        System.out.println(obj);
    }

    @Test
    public void demo04() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // 获取本类和父类中的所有共有属性
        Field[] fields = sonClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("=======================================");
        // 获取本类中的所有属性，包括私有属性
        Field[] declaredFields = sonClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    @Test
    public void demo05() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // Son.class 的有参构造
        Constructor<?> constructor = sonClass.getConstructor(String.class,String.class);
        Object obj = constructor.newInstance("亚索", "永恩");
        System.out.println(obj);
    }

    @Test
    public void demo06() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // 获取 Son.class 中的公有方法（包括父类中的公有方法）
        Method sonFnPublic = sonClass.getMethod("sonFnPublic", String.class);
        // 获取 Son.class 中的实例对象
        Object obj = sonClass.getConstructor().newInstance();
        // 传入一个对象和方法中的参数，调用函数，返回值是 value
        Object value = sonFnPublic.invoke(obj, "我是方法中的参数");
        System.out.println(value);
    }

    @Test
    public void demo07() throws Exception {
        // 使用 Class 类中的 forName 方法加载某个类的.class文件，获取这个类的信息
        Class<?> sonClass = Class.forName("com.neuedu.demo.l_reflect.pojo.Son");
        // 获取 Son.class 中的所有方法（包括私有方法）
        Method sonFnPrivate = sonClass.getDeclaredMethod("sonFnPrivate");
        // // 取消对私有方法权限的访问检查
        sonFnPrivate.setAccessible(true);
        Object obj = sonClass.getDeclaredConstructor().newInstance();
        Object value = sonFnPrivate.invoke(obj);
        System.out.println(value);
    }
}
