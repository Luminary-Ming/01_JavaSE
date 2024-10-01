package com.neuedu.demo.k_io.demo02;

import java.io.Serializable;

public class Person implements Serializable, Cloneable {
    // 给当前的类指定序列化的版本号，这时序列化和反序列化时就不会生成新的版本号
    public static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    // transient 瞬态关键字，用于修饰类中变量，在序列化时会被忽略
    private transient Integer age;
    private static String sex;

    public Person(){}

    public Person(Integer id, String name, Integer age , String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
