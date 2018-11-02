package com.yb.threadlocal.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangbiao
 * @Description:我的ThreadLocal的demo小类-->具体的调用在测试类
 * @date 2018/11/1
 */
public class MyTheradLocal {
    //定义ThreadLocal建议使用private static可避免弱引用带来的内存泄漏的问题,
    //(因为很少有人记得用完之后使用remove去一出它)
    //为了避免出现hash冲突,建议每个线程只存储一个变量,如果存储多个就需要创建多个ThreadLocal,越多的
    //ThreadLocal就越容易出现hash冲突问题,所以用map作为存储的变量,就能存储很多的变量在map里,这个就是
    //变相的多存储了多个变量,还不会引起hash冲突,多好啊
    private static final ThreadLocal<Map<Object, Object>> threadLocal = new ThreadLocal<Map<Object, Object>>() {
        //匿名内部类是一个继承了该类(包括抽象类)或者实现了该接口的子类匿名对象,在我们看来它似乎没存在过似的,但是却完成了
        //实现接口的方法或者重写父类的方法的功能,并通过多态赋值给父类或者接口,这样就不需要在单独创建一个类再引用进来
        @Override
        protected Map<Object, Object> initialValue() {
            //初始化值,当ThreadLocal没有set值或者刚remove,获取到的就是这个初始化的值
            return new HashMap<Object, Object>();
        }
    };

    /**
     * 根据key获取value值
     *
     * @param key
     * @return
     */
    public static Object getValue(Object key) {
        //获取线程里的变量
        Map<Object, Object> objectMap = threadLocal.get();
        //判断变量是否存在
        if (objectMap == null) {
            return null;
        }
        return objectMap.get(key);
    }

    /**
     * 通过key来存储value值
     *
     * @param key
     * @param value
     */
    public static void setValue(Object key, Object value) {
        //获取线程的变量
        Map<Object, Object> objectMap = threadLocal.get();
        if (objectMap == null) {
            objectMap = new HashMap<>();
            //存储值
            objectMap.put(key, value);
            //把变量添加到当前线程
            threadLocal.set(objectMap);
        } else {
            //存储值
            objectMap.put(key, value);
        }
    }

    /**
     * 移除key的value
     * @param key
     */
    public static void removeValue(Object key){
        //获取线程变量
        Map<Object, Object> objectMap = threadLocal.get();
        if(objectMap!=null){
            objectMap.remove(key);
        }
    }

    /**
     * 重置清除数据
     */
    public static void resetValue(){
        Map<Object, Object> objectMap = threadLocal.get();
        if(objectMap!=null){
           objectMap.clear();
        }
    }
}
