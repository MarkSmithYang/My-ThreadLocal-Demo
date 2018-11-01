package com.yb.threadlocal.demo;

import java.util.Map;

/**
 * @author yangbiao
 * @Description:我的ThreadLocal的demo小类
 * @date 2018/11/1
 */
public class MyTheradLocal {
    //定义ThreadLocal建议使用private static可避免弱引用带来的内存泄漏的问题,
    //(因为很少有人记得用完之后使用remove去一出它)
    //为了避免出现hash冲突,建议每个线程只存储一个变量,如果存储多个就需要创建多个ThreadLocal,越多的
    //ThreadLocal就越容易出现hash冲突问题,所以用map作为存储的变量,就能存储很多的变量在map里,这个就是
    //变相的多存储了多个变量,还不会引起hash冲突,多好啊
    private static final ThreadLocal<Map<String,Object>> threadLocal  = new ThreadLocal<Map<String,Object>>(){

        @Override
        protected Map<String, Object> initialValue() {

            return null;
        }
    };

}
