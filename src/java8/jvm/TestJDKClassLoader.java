package java8.jvm;

import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

/**
 * @author young
 * @since 2020/9/17 15:03
 */
public class TestJDKClassLoader {

    public static void main(String[] args) {
        //引导类加载器
        System.out.println(String.class.getClassLoader());
        //ext扩展类加载器
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        //应用程序类加载器
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println("===============================================================");

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("appClassLoader : " + appClassLoader);
        System.out.println("extClassLoader : " + appClassLoader.getParent());
        System.out.println("bootstrapLoader : " + appClassLoader.getParent().getParent());

        System.out.println("=========================bootstrapLoader加载以下文件：===================================");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urLs).forEach(System.out::println);

        System.out.println("=========================extClassloader加载以下文件：===================================");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("=========================appClassloader加载以下文件：===================================");
        System.out.println(System.getProperty("java.class.path"));
    }
}
