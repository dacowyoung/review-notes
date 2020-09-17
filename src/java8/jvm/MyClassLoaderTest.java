package java8.jvm;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author young
 * @since 2020/9/17 16:46
 */
public class MyClassLoaderTest {

    static class MyClassLoader extends ClassLoader {

        private String classpath;

        public MyClassLoader(String classpath) {
            this.classpath = classpath;
        }

        @Override
        public Class findClass(String name) throws ClassNotFoundException {
            byte[] data = new byte[0];
            try {
                data = loadClassData(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadClassData(String name) throws IOException {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classpath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    if(!name.startsWith("java8.jvm")){
                        c =this.getParent().loadClass(name);
                    }else{
                        c =findClass(name);
                    }
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

        public static void main(String[] args) throws Exception {
            MyClassLoader classLoader = new MyClassLoader("C:/Users/dailiuyang/Desktop/study/review-notes/target/review-notes");
            Class<?> clazz = classLoader.loadClass("java8.jvm.User");
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("sout", null);
            method.invoke(obj, null);
            System.out.println(clazz.getClassLoader().getClass().getName());
        }
    }
}
