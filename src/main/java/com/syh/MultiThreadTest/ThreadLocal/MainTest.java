package com.syh.MultiThreadTest.ThreadLocal;

public class MainTest {
    public static void main(String[] args) {
        /**
         * 三个线程同时循环获取ThreadLocal的id
         * 查看三个线程的ThreadLocal是否会相互干扰
         */

//        incrementSameThreadId();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                incrementSameThreadId();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                incrementSameThreadId();
//            }
//        }).start();
        final ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("1");
        
        if(threadLocal.get()!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
                }
            }).start();
        }
        System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
    }
    /**
     * 测试当前线程ThreadLocal的id是否会变化
     */
    private static void incrementSameThreadId(){
        try {
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread()+"_"+i+";threadId:"+ThreadLocalId.get());
            }
        }finally {
            ThreadLocalId.remove();
        }
    }
}
