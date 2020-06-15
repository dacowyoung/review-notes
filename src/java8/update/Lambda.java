package java8.update;

import java.util.Comparator;
import java.util.function.IntBinaryOperator;

/**
 * @author: young
 * @create: 2019/3/28 18:02
 * @desc: Lambda 表达式
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 */
public class Lambda {

    /**
     * (parameters) -> expression 或 (parameters) ->{ statements; }
     * <p>
     * 重要特征:
     * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
     * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
     * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
     * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
     */
    public static void main(String[] args) {
        Lambda lambda = new Lambda();

        //类型声明
        MathOperation add = (int a, int b) -> a + b;

        //不用类型声明
        MathOperation sub = (a, b) -> a - b;

        //大括号中的返回语句
        MathOperation multi = (a, b) -> {
            return a * b;
        };

        //没有大括号的返回语句
        MathOperation division = (a, b) -> a / b;

        System.out.println("10+5=" + lambda.operate(10, 5, add));
        System.out.println("10-5=" + lambda.operate(10, 5, sub));
        System.out.println("10*5=" + lambda.operate(10, 5, multi));
        System.out.println("10/5=" + lambda.operate(10, 5, division));

        GreetingService greeting = msg -> System.out.println("hello " + msg);

        greeting.sayMsg("xiuxiu");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMsg(String msg);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
