# jvm
## loadClass类加载机制  
加载 >> 验证 >> 准备 >> 解析 >> 初始化 >> 使用 >> 卸载  
>1-加载：在硬盘上查找并通过IO读入字节码文件，使用到类时才会加载，例如调用类的main()方法，new对象等等，在加载阶段会在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口  
>2-验证：校验字节码文件的正确性  
>3-准备：给类的静态变量分配内存，并赋予默认值  
>4-解析: 将符号引用替换为直接引用，该阶段会把一些静态方法(符号引用，比如main()方法)替换为指向数据所存内存的指针或句柄等(直接引用)，这是所谓的静态链接过程(类加载期间完成)，动态链接是在程序运行期间完成的将符号引用替换为直接引用   
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
类被加载到方法区中后主要包含 运行时常量池、类型信息、字段信息、方法信息、类加载器的引用、对应class实例的引用等信息。
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
>1-引导类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的核心类库，比如rt.jar、charsets.jar等  
>2-扩展类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的ext扩展目录中的JAR类包  
>3-应用程序类加载器：负责加载ClassPath路径下的类包，主要就是加载你自己写的那些类  
>4-自定义加载器：负责加载用户自定义路径下的类包


## 类加载器初始化过程：
>1-创建JVM启动器实例sun.misc.Launcher,单例模式(饿汉式),保证一个JVM虚拟机内只有一个sun.misc.Launcher实例  
>2-在Launcher构造方法内部，其创建了两个类加载器，分别是sun.misc.Launcher.ExtClassLoader(扩展类加载器)和sun.misc.Launcher.AppClassLoader(应用类加载器)。  
>3-JVM默认使用Launcher的getClassLoader()方法返回的类加载器AppClassLoader的实例加载我们的应用程序。  
```
public class Launcher {
    private static URLStreamHandlerFactory factory = new Launcher.Factory();
    // 单例饿汉式
    private static Launcher launcher = new Launcher();
    private static String bootClassPath = System.getProperty("sun.boot.class.path");
    private ClassLoader loader;
    private static URLStreamHandler fileHandler;

    public static Launcher getLauncher() {
        return launcher;
    }

    public Launcher() {
        Launcher.ExtClassLoader var1;
        try {
            // ExtClassLoader将父加载器注册为空
            // 引导类加载器bootstrapLoader不是实际存在的,是通过URLClassPath加载核心类库的抽象概念
            var1 = Launcher.ExtClassLoader.getExtClassLoader();
        } catch (IOException var10) {
            throw new InternalError("Could not create extension class loader", var10);
        }

        try {
            // 设置默认加载器为AppClassLoader,并将父加载器注册为ExtClassLoader
            this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
        } catch (IOException var9) {
            throw new InternalError("Could not create application class loader", var9);
        }
```

## 双亲委派机制
加载某个类时会先委托父加载器寻找目标类，找不到再委托上层父加载器加载，如果所有父加载器在自己的加载类路径下都找不到目标类，则在自己的类加载路径中查找并载入目标类  
类的继承关系AppClassLoader,ExtClassLoader-->URLClassLoader-->SecureClassLoader-->ClassLoader.loadClass()  
```
// lassLoader的loadClass方法，采用递归的思路,实现了双亲委派机制
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        // 如果当前加载器父加载器不为空则委托父加载器加载该类
                        c = parent.loadClass(name, false);
                    } else {
                        // 如果当前加载器父加载器为空则委托引导类加载器加载该类
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            // resolve默认为false,不会执行
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```  

## 设计双亲委派机制的意义
>1-沙箱安全机制：自己写的java.lang.Integer.class类不会被加载，这样便可以防止核心API库被随意篡改  
>2-避免类的重复加载：当父加载器已经加载了该类时，就没有必要子ClassLoader再加载一次，保证被加载类的唯一性  

## 全盘负责委托机制
“全盘负责”是指当一个ClassLoader装载一个类时，除非显示的使用另外一个ClassLoder，该类所依赖及引用的类也由这个ClassLoader载入。  

## 自定义类加载器
自定义类加载器只需要继承 java.lang.ClassLoader 类，该类有两个核心方法，一个是loadClass(String, boolean)，实现了双亲委派机制，还有一个方法是findClass，默认实现是空方法，所以我们自定义类加载器主要是重写findClass方法。  
java.lang.ClassLoader类,注释上的demo
~~~
 * <blockquote><pre>
 *     class NetworkClassLoader extends ClassLoader {
 *         String host;
 *         int port;
 *
 *         public Class findClass(String name) {
 *             byte[] b = loadClassData(name);
 *             return defineClass(name, b, 0, b.length);
 *         }
 *
 *         private byte[] loadClassData(String name) {
 *             // load the class data from the connection
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote>
~~~



