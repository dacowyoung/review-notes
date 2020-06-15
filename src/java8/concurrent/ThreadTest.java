package java8.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: young
 * @create: 2019/4/10 14:25
 * @desc: 创建线程
 * <p>
 * 线程状态转换
 * 新建（New）-> 创建后尚未启动。
 * 可运行（Runnable）-> 可能正在运行，也可能正在等待 CPU 时间片。包含了操作系统线程状态中的 Running 和 Ready。
 * 阻塞（Blocked）-> 等待获取一个排它锁，如果其线程释放了锁就会结束此状态。
 * 无限期等待（Waiting）-> 等待其它线程显式地唤醒，否则不会被分配 CPU 时间片。
 * 限期等待（Timed Waiting）-> 无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。
 * 死亡（Terminated）-> 可以是线程结束任务之后自己结束，或者产生了异常而结束。
 */
public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadTest test = new ThreadTest();
        MyRunnable runnable = test.new MyRunnable();
        Thread mr = new Thread(runnable, "myRunnable");
        mr.start();

        MyCallable callable = test.new MyCallable();
        FutureTask<String> ft = new FutureTask<>(callable);
        Thread mc = new Thread(ft, "myCallable");
        mc.start();
        System.out.println(ft.get());

        MyThread thread = test.new MyThread("myThread");
        thread.start();

    }


    /**
     * 有三种使用线程的方法：
     * 实现 Runnable 接口；
     * 实现 Callable 接口；
     * 继承 Thread 类。
     * 实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。可以说任务是通过线程驱动从而执行的。
     * 
     * 实现接口 VS 继承 Thread
     * 实现接口会更好一些，因为：
     * Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；
     * 类可能只要求可执行就行，继承整个 Thread 类开销过大。
     */
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable is running...");
        }
    }

    class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "MyCallable is running...";
        }
    }

    class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("MyThread is running...");
        }
    }
    
    

}
