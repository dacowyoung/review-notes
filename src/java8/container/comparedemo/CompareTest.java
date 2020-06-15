package java8.container.comparedemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: young
 * @create: 2020/6/8 16:13
 * @desc: 比较
 */
public class CompareTest {

    public static void main(String[] args) {
        comparator();
        comparable();
    }

    private static void comparator() {
        List<Integer> list = Arrays.asList(-1, 3, 4, 5, -7, -9, 2);
        System.out.println("initial list: ");
        System.out.println(list);

        // void reverse(List list)：反转
        Collections.reverse(list);
        System.out.println("Collections.reverse(list):");
        System.out.println(list);
        // void sort(List list),按⾃然排序的升序排序

        Collections.sort(list);
        System.out.println("Collections.sort(list):");
        System.out.println(list);

        // 定制排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("customize order rule：");
        System.out.println(list);

    }

    private static void comparable() {
        TreeMap<Person, String> pdata = new TreeMap<Person, String>(new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        pdata.put(new Person("张三", 30), "zhangsan");
        pdata.put(new Person("李四", 20), "lisi");
        pdata.put(new Person("王五", 10), "wangwu");
        pdata.put(new Person("⼩红", 5), "xiaohong");
        // 得到key的值的同时得到key所对应的值
        Set<Person> keys = pdata.keySet();
        for (Person key : keys) {
            System.out.println(key.getAge() + "-" + key.getName());
        }
    }
}
