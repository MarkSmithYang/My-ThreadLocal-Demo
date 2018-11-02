package com.yb.threadlocal.demo;

import org.springframework.stereotype.Service;

/**
 * @author yangbiao
 * @Description:测试demo的类-->具体的调用在测试类
 * @date 2018/11/2
 */
@Service
public class MyTest {

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
