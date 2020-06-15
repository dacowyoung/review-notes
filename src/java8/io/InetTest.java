package java8.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * @author: young
 * @create: 2019/4/9 15:46
 * @desc: 网络操作
 * Java 中的网络支持：
 * <p>
 * InetAddress：用于表示网络上的硬件资源，即 IP 地址；
 * URL：统一资源定位符；
 * Sockets：使用 TCP 协议实现网络通信；
 * Datagram：使用 UDP 协议实现网络通信。
 */
public class InetTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com/");
        /* 字节流 */
        InputStream is = url.openStream();
        /* 字符流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        /* 提供缓冲功能 */
        BufferedReader reader = new BufferedReader(isr);

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    /**
     * Sockets
     * ServerSocket：服务器端类
     * Socket：客户端类
     * 服务器和客户端通过 InputStream 和 OutputStream 进行输入输出。
     * 
     * Datagram
     * DatagramSocket：通信类
     * DatagramPacket：数据包类
     */
}
