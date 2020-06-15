package java8.base;

/**
 * @author: young
 * @create: 2019/3/28 13:45
 * @desc: 运算相关
 */
public class Operation {


    /**
     * 参数传递
     * Java 的参数是以值传递的形式传入方法中，而不是引用传递。
     */
    public static void main(String[] args) {
        Operation o = new Operation();
        Dog dog = o.new Dog("A");
        System.out.println(dog.getObjAddress());
//        func1(dog,o);
        func2(dog);
        System.out.println(dog.getObjAddress());
        System.out.println(dog.name);
    }

    /**
     * 在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中。
     * 因此在方法中使指针引用其它对象，那么这两个指针此时指向的是完全不同的对象，在一方改变其所指向对象的内容时对另一方没有影响。
     */
    private static void func1(Dog dog, Operation o) {
        System.out.println("------func1------");
        System.out.println(dog.getObjAddress());
        dog = o.new Dog("B");
        System.out.println(dog.getObjAddress());
        System.out.println(dog.name);
    }

    /**
     * 如果在方法中改变对象的字段值会改变原对象该字段值，因为改变的是同一个地址指向的内容。
     */
    private static void func2(Dog dog) {
        System.out.println("------func2------");
        dog.setName("B");
    }

    class Dog {
        private String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Dog setName(String name) {
            this.name = name;
            return this;
        }

        String getObjAddress() {
            return super.toString();
        }
    }
}
