package java8.base;

/**
 * @author: young
 * @create: 2019/3/28 17:26
 * @desc: 关于异常
 * Throwable 可以用来表示任何可以作为异常抛出的类，分为两种： Error 和 Exception。
 * 其中 Error 用来表示 JVM 无法处理的错误，
 * Exception 分为两种：
 *      受检异常 ：需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
 *      非受检异常 ：是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复
 */
public class OfException {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        try { // try监控区域               
            if (b == 0) throw new ArithmeticException(); // 通过throw语句抛出异常  
            System.out.println("a/b的值是：" + a / b);
            System.out.println("this will not be printed!");
        }
        catch (ArithmeticException e) { // catch捕捉异常  
            System.out.println("程序出现异常，变量b不能为0！");
        }
        System.out.println("程序正常结束。");
    }
}
