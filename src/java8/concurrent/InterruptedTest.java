package java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: young
 * @create: 2019/4/10 15:37
 * @desc: 中端
 * 一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束。
 */
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sleep2s("thread is running!...");
        });

        t1.start();
        /**
         * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。
         * 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
         */
        t1.interrupt();
        System.out.println("main is running!...");


        Thread t2 = new Thread(() -> {
            /**
             * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
             * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
             */
            while (!Thread.interrupted()) {
                System.out.println("thread is running!...");
            }
            System.out.println("thread end...");
        });
        t2.start();
        Thread.sleep(10);
        t2.interrupt();


        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            sleep2s("running!...");
        });
        /**
         * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
         */
        executor.shutdownNow();

        /**
         * 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程
         */
        Future<?> ft = executor.submit(() -> {
            sleep2s("running!...");
        });
        ft.cancel(true);

    }

    private static void sleep2s(String s) {
        try {
            Thread.sleep(2000);
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
