package com.neuedu.demo.l_reflect.pojo;


public class Son extends Father {
    private String sonPrivate;
    public String sonPublic;

    public Son() {
        System.out.println("Person 的空参构造");
    }

    public Son(String sonPrivate, String sonPublic) {
        this.sonPrivate = sonPrivate;
        this.sonPublic = sonPublic;
        System.out.println(sonPrivate + "--Person 的有参构造--" + sonPublic);
    }

    public String sonFnPublic(String msg) {
        System.out.println("sonFnPublic 公有有参方法" + msg);
        return "sonFnPublic";
    }
    private String sonFnPrivate(String msg) {
        System.out.println("sonFnPublic 私有有参方法" + msg);
        return "sonFnPrivate";
    }

    public void sonFnPublic() {
        System.out.println("sonFnPublic 公有方法");
    }

    private void sonFnPrivate() {
        System.out.println("sonFnPrivate 私有方法");
    }

    public static void sonFnPublicStatic() {
        System.out.println("sonFnPublicStatic 公有静态方法");
    }

    private static void sonFnPrivateStatic() {
        System.out.println("sonFnPrivateStatic 私有静态方法");
    }


    public String getSonPrivate() {
        return sonPrivate;
    }

    public void setSonPrivate(String sonPrivate) {
        this.sonPrivate = sonPrivate;
    }

    public String getSonPublic() {
        return sonPublic;
    }

    public void setSonPublic(String sonPublic) {
        this.sonPublic = sonPublic;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sonPrivate=" + sonPrivate +
                ", sonPublic='" + sonPublic + '\'' +
                '}';
    }
}
