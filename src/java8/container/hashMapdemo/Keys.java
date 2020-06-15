package java8.container.hashMapdemo;

import java.util.HashMap;

/**
 * @author: young
 * @create: 2020/6/8 11:45
 * @desc: 避免频繁的GC, 不变的Key实例缓存
 */
public class Keys {
    private static final int MAX_KEY = 10_000_000;
    private static final Key[] KEYS_CACHE = new Key[MAX_KEY];

    static {
        for (int i = 0; i < MAX_KEY; ++i) {
            KEYS_CACHE[i] = new Key(i);
        }
    }

    private static Key of(int value) {
        return KEYS_CACHE[value];
    }

    private static void test(int mapSize) {
        HashMap<Key, Integer> map = new HashMap<Key, Integer>(mapSize);
        for (int i = 0; i < mapSize; ++i) {
            map.put(Keys.of(i), i);
        }
        
        //获取纳秒
        long beginTime = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            map.get(Keys.of(i));
        }
        long endTime = System.nanoTime();
        System.out.println("mapSize: " + mapSize + " ,cost time: " + (endTime - beginTime));
    }

    public static void main(String[] args) {
        for (int n = 10; n <= 10_000_000; n *= 10) {
            test(n);
        }
    }
}
