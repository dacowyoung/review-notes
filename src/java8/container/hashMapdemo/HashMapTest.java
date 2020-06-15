package java8.container.hashMapdemo;

/**
 * @author:       young
 * @create:       2020/6/4 16:28
 * @desc:         hashMap测试类
 */
public class HashMapTest {
    
    
    public static void main(String[] args) {
        // HashMap初始化容量2的幂次方 tableSizeFor
        int n = 199;
        System.out.println(n+" : "+Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(n+" : "+Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n+" : "+Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n+" : "+Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n+" : "+Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n+" : "+Integer.toBinaryString(n));
    }
}
