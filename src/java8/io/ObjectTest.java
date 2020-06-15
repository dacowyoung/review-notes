package java8.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author: young
 * @create: 2019/4/9 15:28
 * @desc: 对象操作
 * Serializable
 * <p>
 * 序列化
 * 序列化就是将一个对象转换成字节序列，方便存储和传输。
 * <p>
 * 序列化：ObjectOutputStream.writeObject()
 * 反序列化：ObjectInputStream.readObject()
 * 不会对静态变量进行序列化，因为序列化只是保存对象的状态，静态变量属于类的状态。
 */
public class ObjectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        A a = new A(123, "abc", "transient");
        String objectFile = "C:\\Users\\Administrator\\Desktop\\study\\a";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objectFile));
        out.writeObject(a);
        out.close();
        System.out.println(a);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objectFile));
        A a1 = (A) in.readObject();
        in.close();
        System.out.println(a1);
    }

    /**
     * Serializable
     * 序列化的类需要实现 Serializable 接口，它只是一个标准，没有任何方法需要实现，但是如果不去实现它的话而进行序列化，会抛出异常。
     */
    static class A implements Serializable {
        int x;
        String y;
        /**
         * transient
         * transient 关键字可以使一些属性不会被序列化。
         * ArrayList 中存储数据的数组 elementData 是用 transient 修饰的，因为这个数组是动态扩展的，并不是所有的空间都被使用，因此就不需要所有的内容都被序列化。
         * 通过重写序列化和反序列化方法，使得可以只序列化数组中有内容的那部分数据。
         * private transient Object[] elementData;
         */
        transient String c;

        public A(int x, String y, String c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        public int getX() {
            return x;
        }

        public A setX(int x) {
            this.x = x;
            return this;
        }

        public String getY() {
            return y;
        }

        public A setY(String y) {
            this.y = y;
            return this;
        }

        public String getC() {
            return c;
        }

        public A setC(String c) {
            this.c = c;
            return this;
        }

        @Override
        public String toString() {
            return "A{" +
                    "x=" + x +
                    ", y='" + y + '\'' +
                    ", c='" + c + '\'' +
                    '}';
        }
    }
}
