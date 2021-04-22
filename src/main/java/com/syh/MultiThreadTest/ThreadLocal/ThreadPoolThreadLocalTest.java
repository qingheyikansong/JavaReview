package com.syh.MultiThreadTest.ThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试要点
 * 线程消耗完时间片回到线程池中是否会先将当前的ThreadLocal移除？
 * 在线程池中使用ThreadLocal使用后线程不会自动移除线程中的ThreadLocal,所以在线程池中注意使用完ThreadLocal后要Remove
 */
public class ThreadPoolThreadLocalTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadLocal tl = new ThreadLocal();
        tl.set("1");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                tl.set("2");
                System.out.println("设置先执行了");
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("获取值线程");
                System.out.println(tl.get());
            }
        });
    }
}
