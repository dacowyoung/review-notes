package java8.jvm;

/**
 * @author young
 * @since 2020/9/16 14:58
 */
public class Math {

    public static final int initial = 666;

    private static User user = new User();

    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 20;
        return c;
    }


    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
