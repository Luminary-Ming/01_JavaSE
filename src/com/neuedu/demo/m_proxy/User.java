package com.neuedu.demo.m_proxy;

public class User implements UserBehave{

    @Override
    public void sing(String singName) {
        System.out.println("唱一首" + singName);
    }

    @Override
    public void dance() {
        System.out.println("跳舞");
    }

    public void sleep(){
        System.out.println("睡觉");
    }
}
