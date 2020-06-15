package java8.test;

/**
 * @author: young
 * @create: 2019/6/20 9:55
 * @desc: 站点
 */
public class Stop {
    private String id;
    private String name;
    /**
     * 站点类型,1起点.2途经.3终点
     */
    private int type;
    private Boolean reach = false;
    private Boolean leave = false;

    public Stop(){}
    
    public Stop(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getReach() {
        return reach;
    }

    public void setReach(Boolean reach) {
        this.reach = reach;
    }

    public Boolean getLeave() {
        return leave;
    }

    public void setLeave(Boolean leave) {
        this.leave = leave;
    }

    public Stop getNext() {
        return next;
    }

    public void setNext(Stop next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", reach=" + reach +
                ", leave=" + leave +
                '}';
    }

    private Stop next;

    public boolean hasNext() {
        return next != null;
    }
}
