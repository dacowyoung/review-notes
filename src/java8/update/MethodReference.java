package java8.update;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author: young
 * @create: 2019/3/29 11:08
 * @desc: 方法引用
 * 方法引用通过方法的名字来指向一个方法。
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * 方法引用使用一对冒号 :: 。
 */
public class MethodReference {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("xiuxiu");
        names.add("mingming");
        names.add("tiantian");
        names.add("junjun");
        names.add("zhuangzhuang");

        names.forEach(System.out::println);

    }
}
