package com.me.Map;

import com.me.bean.Person;
import org.junit.Test;

import java.util.*;

/**
 * @author zs
 * @date 2021/9/20.
 map使用方法
 */
public class MapUse {
    // TODO  hashmap 使用lamdba表达式删除元素
    @Test
    public void MapRemove2(){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.entrySet().removeIf(entry -> entry.getValue() % 2 == 0);
        System.out.println(map);
    }

    // TODO  hashmap 删除元素
    @Test
    public void MapRemove1(){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        Map.Entry<String, Integer> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (entry.getValue() % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(map);
    }

    @Test
    public void MapIter3(){
        HashMap<Integer, Person> map= new HashMap<>();
        // TODO 只有重写 hashcode 才可以相同的key存不同的value，否则会覆盖
        map.put(1, new Person("张三", 1));
        map.put(2, new Person("李四", 2));
        map.put(3, new Person("王五", 3));
        map.put(4, new Person("赵六", 4));
        Set<Integer> integers = map.keySet();
        System.out.println(integers);
        Collection<Person> values = map.values();
        System.out.println(values);
        Person orDefault = map.getOrDefault(1, new Person("1", 1));
        System.out.println(orDefault);
    }
    @Test
    public void MapIter2(){
        HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(4,4);
        //TODO 遍历所有key
        Set<Integer> keys=map.keySet();
        Iterator<Integer> iterator1=keys.iterator();
        while (iterator1.hasNext()){
            System.out.print(iterator1.next() +", ");
        }
        System.out.println();
        System.out.println("------------------------");
        // TODO 遍历所有value
        Collection<Integer> values = map.values();
        Iterator<Integer> iterator2 = values.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next() + ", ");
        }
    }
    // TODO 遍历k,v
    @Test
    public void MapIter1(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "11");
        map.put("1", "10");
        map.put("1", "12");// 相同的key会覆盖之前的value
        map.put("2", "22");
        map.put("3", "33");
        map.forEach((k, v)->{
            System.out.println(k);
            System.out.println(v);
        });
    }
}
