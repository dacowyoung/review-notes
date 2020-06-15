package java8.base;

import sun.reflect.annotation.ExceptionProxy;

import javax.lang.model.element.VariableElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: young
 * @create: 2019/3/28 16:35
 * @desc: 反射相关
 * 反射 (Reflection) 是 Java 的特征之一，它允许运行中的 Java 程序获取自身的信息，并且可以操作类或对象的内部属性。
 */
public class Reflection {
    /**
     * Java 反射主要提供以下功能：
     * 在运行时判断任意一个对象所属的类；
     * 在运行时构造任意一个类的对象；
     * 在运行时判断任意一个类所具有的成员变量和方法（通过反射甚至可以调用private方法）；
     * 在运行时调用任意一个对象的方法
     * 
     * 优点:
     * 可扩展性:应用程序可以利用全限定名创建可扩展对象的实例，来使用来自外部的用户自定义类。
     * 类浏览器和可视化开发环境:一个类浏览器需要可以枚举类的成员。可视化开发环境（如 IDE）可以从利用反射中可用的类型信息中受益，以帮助程序员编写正确的代码。
     * 调试器和测试工具:调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的 API 定义，以确保一组测试中有较高的代码覆盖率。
     * 
     * 缺点:
     * 性能开销:反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。我们应该避免在经常被执行的代码或对性能要求很高的程序中使用反射。
     * 安全限制:使用反射技术要求程序必须在一个没有安全限制的环境中运行。如果一个程序必须在有安全限制的环境中运行，如 Applet，那么这就是个问题了。
     * 内部暴露:由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。
     */
    public static void main(String[] args) throws Exception {
        // 1.获得 Class 对象
        Class<?> r = Reflection.class;
        System.out.println(r);

        // 2.判断是否为某个类的实例
        boolean b = r.isInstance("123");
        System.out.println(b);

        // 3. 创建实例
        Object o = r.newInstance();
        System.out.println(o.toString());

        // 4.获取方法
        // getDeclaredMethods 方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
        System.out.println("---------getDeclaredMethods-------");
        Method[] declaredMethods = r.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }

        // getMethods 方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。
        System.out.println("---------getMethods-------");
        Method[] methods = r.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        // getMethod 方法返回一个特定的方法，其中第一个参数为方法名称，后面的参数为方法的参数对应Class的对象。
        System.out.println("---------getMethod-------");
        Method method = r.getMethod("equals", Object.class);
        System.out.println(method);

        // 5.获取构造器信息
        System.out.println("---------getConstructors-------");
        Constructor<?>[] constructors = r.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        // 6.获取类的成员变量（字段）信息
        // getDeclaredField：所有已声明的成员变量，但不能得到其父类的成员变量
        System.out.println("---------getDeclaredFields-------");
        Field[] declaredFields = r.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        // getFiled：访问公有的成员变量
        System.out.println("---------getFields-------");
        Field[] fields = r.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        // 7.调用方法
        System.out.println("---------invoke-------");
        Method add = r.getMethod("add", int.class, int.class);
        Object result = add.invoke(o, 1, 2);
        System.out.println(result);

    }

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public String a;
    private Integer b;
}
