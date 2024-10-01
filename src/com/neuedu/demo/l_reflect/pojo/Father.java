package com.neuedu.demo.l_reflect.pojo;

public class Father {
    private String fatherPrivate;
    public String fatherPublic;

    public String fatherFnPublic(String msg) {
        System.out.println("fatherFnPublic 公有有参方法" + msg);
        return "fatherFnPublic";
    }
    private String fatherFnPrivate(String msg) {
        System.out.println("fatherFnPublic 私有有参方法" + msg);
        return "fatherFnPrivate";
    }

    public void fatherFnPublic() {
        System.out.println("fatherFnPublic 公有方法");
    }

    private void fatherFnPrivate() {
        System.out.println("fatherFnPrivate 私有方法");
    }

    public static void fatherFnPublicStatic() {
        System.out.println("fatherFnPublicStatic 公有静态方法");
    }

    private static void fatherFnPrivateStatic() {
        System.out.println("fatherFnPrivateStatic 私有静态方法");
    }
}
