package java8.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: young
 * @create: 2020/6/11 15:51
 * @desc: 循环壁垒, 并发小例子
 */
public class TestMutex {

    private static CyclicBarrier barrier = new CyclicBarrier(31);

    private static int a = 0;

    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 30; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment1();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        barrier.await();
        System.out.println("unlock,a=" + a);

        barrier.reset();
        a = 0;
        for (int i = 0; i < 30; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment2();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        barrier.await();
        System.out.println("lock,a=" + a);

    }

    private static void increment2() {
        mutex.lock();
        a++;
        mutex.unlock();
    }

    private static void increment1() {
        a++;
    }


}
