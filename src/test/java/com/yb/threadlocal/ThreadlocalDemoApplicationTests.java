package com.yb.threadlocal;

import com.yb.threadlocal.demo.MyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadlocalDemoApplicationTests {

    @Autowired
    private MyTest myTest;

    @Test
    public void contextLoads1() {
        myTest.setThreadLocal();
        System.err.println("赋值成功");
    }

    @Test
    public void contextLoads2() {
        //必须在同一个线程里(运行时会陆续执行的代码都在一个线程-->
        //如果含有开启多线程的代码,实测那个线程也可以共享这个变量数据),才能获取到值(当然了需要实现set值)
        contextLoads1();
        Object threadLocal = myTest.getThreadLocal();
        System.err.println("contextLoads2-->"+threadLocal);
        System.err.println(Thread.currentThread());
        contextLoads3();
    }

    @Test
    public void contextLoads3() {
        Object threadLocal = myTest.findThreadLocal();
        System.err.println("contextLoads3-->"+threadLocal);
    }

}
