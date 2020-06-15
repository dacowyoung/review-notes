package java8.io;

import java.io.File;

/**
 * @author: young
 * @create: 2019/4/9 13:47
 * @desc: 磁盘操作
 * 从 Java7 开始，可以使用 Paths 和 Files 代替 File。
 * 
 * Java 的 I/O 大概可以分成以下几类：
 * 磁盘操作：File
 * 字节操作：InputStream 和 OutputStream
 * 字符操作：Reader 和 Writer
 * 对象操作：Serializable
 * 网络操作：Socket
 * 新的输入/输出：NIO
 */
public class FileTest {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\Administrator\\Desktop\\study\\review-notes");
        listAllFile(dir);
    }

    private static void listAllFile(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFile(file);
        }
    }
}
