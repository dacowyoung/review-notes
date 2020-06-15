package java8.container;

import java.util.ArrayList;

/**
 * @author: young
 * @create: 2020/6/4 16:11
 * @desc: ArrayList提供对外扩容方法
 * 向 ArrayList 添加大量元素之前最好先使用ensureCapacity 方法，以减少增量重新分配的次数。
 */
public class EnsureCapacityTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        int n = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("use ensureCapacity before ：" + (endTime - startTime));

        list = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(n);
        for (int i = 0; i < n; i++) {
            list.add(i);

        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("use ensureCapacity after：" + (endTime1 - startTime1));
    }

}
