package java8.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: young
 * @create: 2019/4/9 14:29
 * @desc: 字节操作
 * InputStream 和 OutputStream
 */
public class ByteTest {
    public static void main(String[] args) {
        String src = "E:\\JDcard.csv";
        String dist = "C:\\Users\\Administrator\\Desktop\\study\\JDcard.csv";
        copyFile(src, dist);
    }

    /**
     * 实现文件复制
     * @param src  复制文件
     * @param dist 目标文件
     */
    private static void copyFile(String src, String dist) {
        try (FileInputStream in = new FileInputStream(src); FileOutputStream out = new FileOutputStream(dist)) {
            byte[] buffer = new byte[20 * 1024];
            int cnt;

            // read() 最多读取 buffer.length 个字节
            // 返回的是实际读取的个数
            // 返回 -1 的时候表示读到 eof，即文件尾
            while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, cnt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 装饰者模式
     * Java I/O 使用了装饰者模式来实现。以 InputStream 为例，
     *
     * InputStream 是抽象组件；
     * FileInputStream 是 InputStream 的子类，属于具体组件，提供了字节流的输入操作；
     * FilterInputStream 属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如 BufferedInputStream 为 FileInputStream 提供缓存的功能。
     * 
     */
}
