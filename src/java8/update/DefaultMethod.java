package java8.update;

/**
 * @author: young
 * @create: 2019/3/29 14:38
 * @desc: 默认方法
 * 默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
 * 我们只需在方法名前面加个 default 关键字即可实现默认方法。
 * 目的是为了解决接口的修改与现有的实现不兼容的问题。
 */
public class DefaultMethod {
    public static void main(String[] args) {
        DefaultMethod method = new DefaultMethod();
        Vehicle car = method.new Car();
        car.print();
    }

    interface Vehicle {
        default void print() {
            System.out.println("我是一辆车!");
        }

        static void blowHorn() {
            System.out.println("按喇叭!");
        }
    }

    interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车!");
        }
    }

    class Car implements Vehicle, FourWheeler {

        @Override
        public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }
}
