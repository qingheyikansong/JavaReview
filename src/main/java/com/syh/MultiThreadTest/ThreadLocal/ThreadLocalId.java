package com.syh.MultiThreadTest.ThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalId {
    private static final AtomicInteger nextId=new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };
    public static int get(){
        return threadId.get();
    }
    public static void remove(){
        threadId.remove();
    }
}
