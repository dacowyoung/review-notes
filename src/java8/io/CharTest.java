package java8.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author: young
 * @create: 2019/4/9 14:52
 * @desc: 字符操作
 * Reader 和 Writer
 * 不管是磁盘还是网络传输，最小的存储单元都是字节，而不是字符。但是在程序中操作的通常是字符形式的数据，因此需要提供对字符进行操作的方法。
 * <p>
 * InputStreamReader 实现从字节流解码成字符流；
 * OutputStreamWriter 实现字符流编码成为字节流
 *
 * <p>
 * 编码与解码
 * 编码就是把字符转换为字节，而解码是把字节重新组合成字符。
 * 如果编码和解码过程使用不同的编码方式那么就出现了乱码。
 * <p>
 * GBK 编码中，中文字符占 2 个字节，英文字符占 1 个字节；
 * UTF-8 编码中，中文字符占 3 个字节，英文字符占 1 个字节；
 * UTF-16be 编码中，中文字符和英文字符都占 2 个字节。
 * UTF-16be 中的 be 指的是 Big Endian，也就是大端。相应地也有 UTF-16le，le 指的是 Little Endian，也就是小端。
 * <p>
 * Java 的内存编码使用双字节编码 UTF-16be，这不是指 Java 只支持这一种编码方式，而是说 char 这种类型使用 UTF-16be 进行编码。
 * char 类型占 16 位，也就是两个字节，Java 使用这种双字节编码是为了让一个中文或者一个英文都能使用一个 char 来存储。
 */
public class CharTest {
    public static void main(String[] args) throws IOException {
        String s = "中文";
        byte[] bytes = s.getBytes("UTF-8");
        String s1 = new String(bytes, "UTF-8");
        System.out.println(s1);

        String filePath = "E:\\JDcard.csv";
        readFileContent(filePath);
    }

    /**
     * 实现逐行输出文本文件的内容
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    private static void readFileContent(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        bufferedReader.close();
    }
}
