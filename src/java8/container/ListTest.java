package java8.container;

import java.util.Arrays;

/**
 * @author: young
 * @create: 2020/6/4 10:50
 * @desc: list测试
 */
public class ListTest {

    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        // public void add(int index, E element)
        //elementData:源数组;index:源数组中的起始位置;elementData：目标数组；index + 1：目标数组中的起始位置； size - index：要复制的数组元素的数量；
        System.arraycopy(a, 2, a, 3, 3);
        a[2] = 99;
        for (int i : a) {
            System.out.println(i);
        }

        // Arrays.copyOf内部实现为System.arraycopy
        // 调用之前 int[] copy = new int[newLength]; 进行扩容操作 
        int[] b = new int[3];
        b[0] = 0;
        b[1] = 1;
        b[2] = 2;
        int[] c = Arrays.copyOf(a, 10);
        System.out.println("c.length: " + c.length);
    }
}
