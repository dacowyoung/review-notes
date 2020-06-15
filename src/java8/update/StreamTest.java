package java8.update;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author: young
 * @create: 2019/3/29 15:08
 * @desc: 流Stream相关数据处理
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 */
public class StreamTest {
    public static void main(String[] args) {
        // forEach,limit,sorted
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // map
        List<Integer> nums = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squareNums = nums.stream().distinct().map(i -> i * i).collect(Collectors.toList());
        System.out.println(squareNums);

        //filter,Collectors
        List<String> strs = Arrays.asList("aa", "bb", "", "cc", "", "dd", "ee");
        String mergeStr = strs.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("-"));
        System.out.println(mergeStr);

        IntSummaryStatistics stats = nums.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(stats);

        System.out.println("元素总个数:" + stats.getCount());
        System.out.println("元素总和:" + stats.getSum());
        System.out.println("元素最大值:" + stats.getMax());
        System.out.println("元素最小值:" + stats.getMin());
        System.out.println("元素平均值:" + stats.getAverage());
    }
}
