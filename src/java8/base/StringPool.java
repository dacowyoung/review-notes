package java8.base;

/**
 * @author: young
 * @create: 2019/3/28 11:25
 * @desc: 字符串相关
 */
public class StringPool {
    /**
     * String 被声明为final 因此它不可被继承。
     * 在Java8 中,String 内部使用 char 数组存储数据。
     * 在Java9 之后,String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
     * value 数组被声明为final，这意味着value 数组初始化之后就不能再引用其它数组.并且String 内部没有改变value 数组的方法，因此可以保证String 不可变。
     * <p>
     * 不可变的好处
     * 1.可以缓存hash值
     * 2.StringPool的需要
     * 3.安全性
     * 4.线程安全
     * <p>
     * String不可变,StringBuffer和StringBuilder可变
     * String不可变因此是线程安全的
     * StringBuilder不是线程安全的
     * StringBuffer是线程安全的，内部使用synchronized进行同步
     */
    public static void main(String[] args) {
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2); // false,new String()的方式新建了两个不同字符串
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println(s3 == s4); // true,s3和s4是通过s1.intern()方法取得一个字符串引用,intern()首先把s1引用的字符串放到String Pool中,然后返回这个字符串引用。
        String s5 = "bbb";
        String s6 = "bbb";
        System.out.println(s5 == s6); // true,如果是采用 "bbb" 这种字面量的形式创建字符串,会自动地将字符串放入String Pool中。

        //在Java7之前String Pool被放在运行时常量池中,它属于永久代.而在Java 7String Pool被移到堆中.这是因为永久代的空间有限,在大量使用字符串的场景下会导致OutOfMemoryError错误。
    }

    /**
     * new String("abc")
     * 使用这种方式一共会创建两个字符串对象（前提是String Pool中还没有"abc"字符串对象）。
     * "abc"属于字符串字面量，因此编译时期会在String Pool中创建一个字符串对象，指向这个"abc"字符串字面量；
     * 而使用 new 的方式会在堆中创建一个字符串对象。
     */
}
