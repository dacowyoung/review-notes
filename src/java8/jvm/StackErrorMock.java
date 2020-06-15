package java8.jvm;

/**
 * @author: young
 * @create: 2020/6/8 17:38
 * @desc: StackOverflowError测试
 */
public class StackErrorMock {

    private static int index = 1;

    private void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}
