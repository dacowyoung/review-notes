package java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: young
 * @create: 2019/4/18 14:51
 * @desc: AwaitSignal相关
 * 
 * java.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，
 * 可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。
 * 
 * 相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。
 */
public class AwaitSignalTest {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await(1, TimeUnit.SECONDS);
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        AwaitSignalTest test = new AwaitSignalTest();
        executor.execute(() -> test.after());
        executor.execute(() -> test.before());
        executor.shutdown();
    }

}

