package com.syh.MultiThreadTest.ThreadLocal;

import java.lang.ref.WeakReference;

/**
 * 强、弱引用测试
 * 强引用Object o=new Object()创建在堆中的对象被直接引用,程序结束前不会被gc()
 * 弱引用通过WeakReference<String> wr = new WeakReference<>(new String("Test ThreadLocal Memory Leak"))创建引用，
 * 并且引用的对象没有再被强引用
 */
public class StrongWeakReferenceTest {
    public static void main(String[] args) {
        //强引用测试
//        String s = new String("Test ThreadLocal Memory Leak");
//        WeakReference<String> wr = new WeakReference<>(s);
        //弱引用测试
        WeakReference<String> wr = new WeakReference<>(new String("Test ThreadLocal Memory Leak"));
        String s=wr.get();
        System.out.println();
        System.gc();
        if(wr.get()==null){
            System.out.println("WeakReference已经被GC回收");
        }else {
            System.out.println(wr.get());
        }
    }
}
