# jvm
##loadClass类加载机制
加载 >> 验证 >> 准备 >> 解析 >> 初始化 >> 使用 >> 卸载  
>1-加载：在硬盘上查找并通过IO读入字节码文件，使用到类时才会加载，例如调用类的main()方法，new对象等等，在加载阶段会在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口  
>2-验证：校验字节码文件的正确性  
>3-准备：给类的静态变量分配内存，并赋予默认值  
>4-解析:将符号引用替换为直接引用，该阶段会把一些静态方法(符号引用，比如main()方法)替换为指向数据所存内存的指针或句柄等(直接引用)，这是所谓的静态链接过程(类加载期间完成)，动态链接是在程序运行期间完成的将符号引用替换为直接引用   
>5-初始化：对类的静态变量初始化为指定的值，执行静态代码块
```
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
```
反编译:执行命令javap -c Math.class
```
Compiled from "Math.java"
public class java8.jvm.Math {
  public static final int initial;

  public java8.jvm.Math();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public int compute();
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_2
       3: istore_2
       4: iload_1
       5: iload_2
       6: iadd
       7: bipush        20
       9: imul
      10: istore_3
      11: iload_3
      12: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class java8/jvm/Math
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #4                  // Method compute:()I
      12: pop
      13: return

  static {};
    Code:
       0: new           #5                  // class java8/jvm/User
       3: dup
       4: invokespecial #6                  // Method java8/jvm/User."<init>":()V
       7: putstatic     #7                  // Field user:Ljava8/jvm/User;
      10: return
}

```
## 类加载器
>1.引导类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的核心类库，比如rt.jar、charsets.jar等  
>2.扩展类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的ext扩展目录中的JAR类包  
>3.应用程序类加载器：负责加载ClassPath路径下的类包，主要就是加载你自己写的那些类  
>4.自定义加载器：负责加载用户自定义路径下的类包
