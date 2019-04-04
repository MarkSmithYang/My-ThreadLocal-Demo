package com.yb.threadlocal.demo;

import org.springframework.stereotype.Service;

/**
 * @author yangbiao
 * @Description:测试demo的类-->具体的调用在测试类
 * @date 2018/11/2
 */
@Service
public class MyTest {

    //ThreadLocal有一个子类InheritableThreadLocal,使用这个变量就可以轻松的在子线程中依旧使用父线程中的本地变量
    //一般会把从jwt解析出的用户放进这个里面,这样就能在服务的其他地方获取到用户信息了

    /**
     * 为ThreadLocal赋值
     */
    public void setThreadLocal() {
        MyTheradLocal.setValue("1", "jack");
        MyTheradLocal.setValue("2", "rose");
        MyTheradLocal.setValue("3", "tom");
    }

    /**
     * 获取ThreadLocal的值get
     */
    public Object getThreadLocal() {
        Object value = MyTheradLocal.getValue("1");
        return value;
    }

    /**
     * 获取ThreadLocal的值find
     */
    public Object findThreadLocal() {
        Object value = MyTheradLocal.getValue("2");
        new Thread(() -> {
            Object value1 = MyTheradLocal.getValue("3");
            System.err.println(value1);
            System.err.println("水水水水1" + Thread.currentThread());
        }).run();
        new Thread(() -> {
            Object value1 = MyTheradLocal.getValue("3");
            System.err.println(value1);
            System.err.println("水水水水2" + Thread.currentThread());
        }).run();
        return value;
    }

}
