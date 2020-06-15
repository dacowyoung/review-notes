package java8.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:       young
 * @create:       2019/6/20 13:40
 * @desc:         测试
 */
public class StopTest {
    public static void main(String[] args) {
        Stop a = new Stop("1", "a", 1);
        Stop b = new Stop("2", "b", 2);
        Stop c = new Stop("3", "c", 3);
        Stop d = new Stop("4", "d", 4);
        List<Stop> stops = new ArrayList<>();
        stops.add(a);
        stops.add(b);
        stops.add(c);
        stops.add(d);

        RoutePusher pusher = new RoutePusher(stops);

        pusher.action("1",1,1);
        pusher.action("1",2,2);
        pusher.action("2",1,2);
        pusher.action("2",2,2);
        pusher.action("2",1,2);
        pusher.action("2",2,2);
        pusher.action("3",1,2);
        pusher.action("3",2,2); 
        pusher.action("4",1,2);
        pusher.action("4",2,2);

    }
}
