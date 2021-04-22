package com.syh.MultiThreadTest.ThreadLocal;

import java.util.ArrayList;
import java.util.List;

//Recurrence复现,重现
public class ThreadLocalMemoryLeakRecurrence {
    public ThreadLocal<List<Object>> threadId=new ThreadLocal<List<Object>>(){
        @Override
        protected List<Object> initialValue() {
            List<Object> list = new ArrayList<>();
            for (int i=0;i<10000;i++){
                list.add(String.valueOf(i));
            }
            return list;
        }
    };
    public List<Object> get(){
        return threadId.get();
    }
    public void remove(){
        threadId.remove();
    }

    public static void main(String[] args) {
        ThreadLocalMemoryLeakRecurrence memory = new ThreadLocalMemoryLeakRecurrence();
        incrementSameThreadId(memory);
        System.out.println("GC前：key:" + memory.threadId);
        System.out.println("GC前：value-size:" + memory.get().size());
        // 设置为null，调用gc并不一定触发垃圾回收，但是可以通过java提供的一些工具进行手工触发gc回收。
//        memory.threadId = null;
        System.gc();

        System.out.println("GC后：key:" + memory.threadId);
//        System.out.println("GC后：value-size:" + memory.get().size());

        // 模拟线程一直运行
        while (true) {
        }
    }
    private static void incrementSameThreadId(ThreadLocalMemoryLeakRecurrence memory){
        try {
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread()+"_"+i+";threadId:"+memory.threadId.get());
            }
        }finally {
            ThreadLocalId.remove();
        }
    }
}
