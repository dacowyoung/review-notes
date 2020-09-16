package java8.jvm;

/**
 * @author young
 * @since 2020/9/16 16:05
 */
public class Son extends Father {
    static {
        System.out.println("Son static");
    }

    public Son() {
        System.out.println("Son construct method");
    }

    {
        System.out.println("Son construct code");
    }

    public static void main(String[] args) {
        new Son();
        System.out.println("end");
    }

}
