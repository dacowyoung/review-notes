package java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: young
 * @create: 2019/4/10 15:04
 * @desc: 基础线程机制
 */
public class BaseThreadTest {
    /**
     * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
     * 主要有三种 Executor：
     * CachedThreadPool：一个任务创建一个线程；
     * FixedThreadPool：所有任务只能使用固定大小的线程；
     * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    /**
                     * Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。
                     * sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。
                     * 线程中抛出的其它异常也同样需要在本地进行处理。
                     */
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("running!....");
            });
        }
        executor.shutdown();

        /**
         * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
         * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
         * main() 属于非守护线程。
         * 使用 setDaemon() 方法将一个线程设置为守护线程。
         */
        Thread thread = new Thread(() -> {
            System.out.println("daemon is running!...");
        });
        thread.setDaemon(true);
        thread.start();

    }

}
