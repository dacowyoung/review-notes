package java8.jvm;

/**
 * @author young
 * @since 2020/9/16 16:02
 */
public class Father {

    static {
        System.out.println("father static");
    }

    public Father() {
        System.out.println("father construct method");
    }

    {
        System.out.println("father construct code");
    }
}
