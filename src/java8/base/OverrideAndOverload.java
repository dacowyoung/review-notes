package java8.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * @create: 2019/3/28 14:36
 * @desc: 重写与重载
 */
public class OverrideAndOverload {

    /**
     * 1.重写(Override)
     * 存在于继承体系中，指子类实现了一个与父类在方法声明上完全相同的一个方法。
     * 子类方法访问权限为 public，大于父类的 protected。
     * 子类的返回类型为 ArrayList，是父类返回类型 List 的子类。
     * 子类抛出的异常类型为 Exception，是父类抛出异常 Throwable 的子类。
     * 子类重写方法使用 @Override 注解，从而让编译器自动检查是否满足限制条件
     */
    class SuperClass {
        protected List<Integer> func() throws Throwable {
            return new ArrayList<>();
        }
    }

    class SubClass extends SuperClass {
        @Override
        public ArrayList<Integer> func() throws Exception {
            return new ArrayList<>();
        }
    }

    /**
     * 2. 重载（Overload）
     * 存在于同一个类中，方法名和返回值一致，但是参数类型、个数、顺序至少有一个不同。
     */
    class A {
        public String show(D obj) {
            return ("A and D");
        }

        public String show(A obj) {
            return ("A and A");
        }
    }

    class B extends A {
        public String show(B obj) {
            return ("B and B");
        }

        public String show(A obj) {
            return ("B and A");
        }
    }

    class C extends B {
    }

    class D extends B {
    }

    /**
     * 涉及到重写时，方法调用的优先级为：
     * this.show(O)
     * super.show(O)
     * this.show((super)O)
     * super.show((super)O)
     */
    public static void main(String[] args) {
        OverrideAndOverload o = new OverrideAndOverload();
        A a1 = o.new A();
        A a2 = o.new B();
        B b = o.new B();
        C c = o.new C();
        D d = o.new D();
        System.out.println(a1.show(b)); // A and A        
        System.out.println(a1.show(c)); // A and A    
        System.out.println(a1.show(d)); // A and D   
        
        System.out.println(a2.show(b)); // B and A    
        System.out.println(a2.show(c)); // B and A    
        System.out.println(a2.show(d)); // A and D    
        
        System.out.println(b.show(b));  // B and B    
        System.out.println(b.show(c));  // B and B    
        System.out.println(b.show(d));  // A and D    
    }
}
