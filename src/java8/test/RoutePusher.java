package java8.test;

import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: young
 * @create: 2019/6/20 10:06
 * @desc: 路线推送器
 */
public class RoutePusher {

    private HashMap<String, Stop> route;

    private int size;

    private String index;

    public RoutePusher() {
    }

    public RoutePusher(List<Stop> stops) {
        size = stops.size();
        route = new HashMap<>(size);
        for (int i = 0; i < this.size - 1; i++) {
            stops.get(i).setNext(stops.get(i + 1));
        }
        for (Stop stop : stops) {
            route.put(stop.getId(), stop);
        }
    }

    public void action(String id, Integer operType, Integer operFrom) {
        Stop stop = route.get(id);
        if (Objects.isNull(stop)) {
            System.out.println("该班次路线不包含此站点");
            return;
        }
        if (stop.getLeave() && stop.getReach()) {
            System.out.println("该站点已操作完毕");
            return;
        }
        if (operType == StopEnum.OperType.REACH.getCode() && stop.getReach()) {
            System.out.println("该站点到达操作已完毕");
            return;
        }
        if (operType == StopEnum.OperType.LEAVE.getCode() && stop.getLeave()) {
            System.out.println("该站点离开操作已完毕");
            return;
        }
        
        if (operType == StopEnum.OperType.REACH.getCode()) {
            //todo
            index = stop.getId();
            stop.setReach(true);
            System.out.println(stop + "[push]已经到达");
        }

        if (operType == StopEnum.OperType.LEAVE.getCode() && stop.hasNext()) {
            //todo
            stop.setLeave(true);
            System.out.println(stop.getNext() + "[push]即将到达");
        }
    }

}
