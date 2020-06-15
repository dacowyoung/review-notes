package java8.base;

/**
 * @author: young
 * @create: 2019/3/28 14:56
 * @desc: object通用方法
 */
public class ObjMethod {

    /**
     * public native int hashCode()
     * <p>
     * public boolean equals(Object obj)
     * <p>
     * protected native Object clone() throws CloneNotSupportedException
     * <p>
     * public String toString()
     * <p>
     * public final native Class<?> getClass()
     * <p>
     * protected void finalize() throws Throwable {}
     * <p>
     * public final native void notify()
     * <p>
     * public final native void notifyAll()
     * <p>
     * public final native void wait(long timeout) throws InterruptedException
     * <p>
     * public final void wait(long timeout, int nanos) throws InterruptedException
     * <p>
     * public final void wait() throws InterruptedException
     */
    public static void main(String[] args) {
        /**
         * equals()
         * 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
         * 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。
         */
        Integer x = new Integer(1);
        Integer y = new Integer(1);
        System.out.println(x == y); //true
        System.out.println(x.equals(y)); //false

        /**
         * hashCode()
         * hashCode() 返回散列值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。
         * 在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。
         */

        /**
         * toString()
         * 默认返回 obj@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。
         */
    }
}
