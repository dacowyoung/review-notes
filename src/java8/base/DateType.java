package java8.base;

/**
 * @author: young
 * @create: 2019/3/28 10:31
 * @desc: 基础数据类型
 */
public class DateType {
    /**
     * byte/8 
     * char/16
     * short/16
     * int/32
     * float/32
     * long/64
     * double/64
     * boolean/~
     */
    public static void main(String[] args) {
        // 基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。
        // 装箱
        Byte b = 1; // 缓存范围是-128~127
        Character c = 1; // 缓存范围是0~127
        Short s = 1; // 缓存范围是-128~127
        Integer i = 1; // 缓存范围是-128~127,唯一可以修改缓存范围的包装类,VM options 加入参数-XX:AutoBoxCacheMax=7777
        Float f = 1f;
        Long l = 1l; // 缓存范围是-128~127
        Double d = 1d;
        Boolean bool = true;
        // 拆箱
        byte by = b;
        char ch = c;
        short sh = s;
        int in = i;
        float fl = f;
        long lo = l;
        double dou = d;
        boolean boo = bool;

    }
    /**
     * 1.所有的POJO类属性必须使用包装数据类型 
     * 2.RPC方法的返回值和参数必须使用包装数据类型。
     * 3.所有的局部变量推荐使用基本数据类型。
     */
}
