package java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: young
 * @create: 2019/4/16 10:44
 * @desc: synchronized相关
 * 
 * Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，第一个是 JVM 实现的 synchronized，而另一个是 JDK 实现的 ReentrantLock。
 * 
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        ExecutorService executor = Executors.newCachedThreadPool();
        synchronizedExample e1 = test.new synchronizedExample();
        synchronizedExample e2 = test.new synchronizedExample();
        executor.execute(() -> e1.print());
        executor.execute(() -> e2.print());
        executor.shutdown();
        

    }

    class synchronizedExample {
        public void print() {
            // 1. 同步一个代码块
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                }
            }
        }
    }
    
    // 2. 同步一个方法,它和同步代码块一样，作用于同一个对象。
    
    // 3. 同步一个类,作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
    
    // 4. 同步一个静态方法

}
