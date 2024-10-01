package com.neuedu.demo.m_proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
 动态代理的实现方式有两种：通过代理，可以很容易地为系统添加新的功能，而不需要修改现有的代码结构。
   ● JDK 动态代理：JDK 原生实现方式，需要被代理的目标类必须实现了接口。
       它通过反射机制在运行时动态地创建代理类和代理对象，允许在不修改原始类代码的情况下为其添加额外的功能。
       它主要用于实现AOP（面向切面编程）等场景，具有很高的灵活性和扩展性。
       然而，由于其基于接口的限制和可能带来的性能开销，在使用时需要根据具体场景进行权衡。

   ● CGLIB 动态代理：通过继承被代理的目标类实现代理，不需要目标类实现接口。需要导入依赖，才能实现代理。
       CGLIB（Code Generation Library）是一个强大的、高性能的代码生成库，它可以为没有实现接口的类创建代理。
       CGLIB通过继承目标类来创建代理对象，因此它不需要目标类实现接口。CGLIB动态代理的使用相对复杂一些，但它提供了更广泛的代理能力。

    ● JDK 动态代理实现：
        使用 Proxy 类中的 newProxyInstance 方法得到代理对象
            newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
                ClassLoader loader ：类加载
                Class<?>[] interfaces ：当前被代理类实现的所有接口
                InvocationHandler h ：InvocationHandler 实例，当通过代理对象调用方法时，会被拦截，执行 InvocationHandler 中的 invoke 方法。
*/
public class ProxyDemo {
    private final User user = new User();

    @Test
    public void show() {
        // 创建出代理对象 userBehave
        UserBehave userBehave = (UserBehave) Proxy.newProxyInstance(
                // 获取被代理对象实现的类加载器
                user.getClass().getClassLoader(),
                // 获取被代理对象实现的所有接口
                user.getClass().getInterfaces(),
                // InvocationHandler 实例，当通过代理对象调用方法时，会被拦截，执行 InvocationHandler 中的 invoke 方法
                new MyInvocationHandler(user)
        );
        userBehave.sing("喜剧之王");
        userBehave.dance();
    }
}

class MyInvocationHandler implements InvocationHandler {
    private final UserBehave userBehave;

    public MyInvocationHandler(UserBehave userBehave) {
        this.userBehave = userBehave;
    }

    /*
        Object proxy ：当前的代理对象
        Method method ：正在被代理对象调用的方法
        Object[] args ：代理对象调用的方法中的所有参数
    */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理对象调用执行方法之前的添加的代码
        System.out.println("代理对象调用执行方法之前的添加的代码");
        System.out.println("method = " + method.getName());
        System.out.println(Arrays.toString(args));
        // 代理对象调用执行方法
        Object invoke = method.invoke(userBehave, args);
        // 代理对象调用执行方法之后的添加的代码
        System.out.println("代理对象调用执行方法之后的添加的代码");
        return invoke;
    }
}
