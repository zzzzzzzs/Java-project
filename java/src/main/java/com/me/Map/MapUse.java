package com.me.Map;

import com.me.bean.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author zs
 * @date 2021/9/20.
 map使用方法
 */
public class MapUse {

    @Data
    @AllArgsConstructor
    static class StudentScore {
        private Integer sid;
        private String scoreName;
        private Integer score;
    }

    //传统写法
    public static Map<Integer, Integer> sum1(List<StudentScore> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (StudentScore studentScore : list) {
            if (map.containsKey(studentScore.getSid())) {
                map.put(studentScore.getSid(),
                        map.get(studentScore.getSid()) + studentScore.getScore());
            } else {
                map.put(studentScore.getSid(), studentScore.getScore());
            }
        }
        return map;
    }
    //merger写法
    public static Map<Integer, Integer> sum2(List<StudentScore> list) {
        Map<Integer, Integer> map = new HashMap<>();
        list.stream().forEach(studentScore -> map.merge(studentScore.getSid()
                , studentScore.getScore(), Integer::sum));
        return map;
    }
    // TODO  map merge
    @Test
    public void MapMerge(){
        List<StudentScore> list = new ArrayList<>();
        list.add(new StudentScore(1, "chinese", 110));
        list.add(new StudentScore(1, "english", 120));
        list.add(new StudentScore(1, "math", 135));
        list.add(new StudentScore(2, "chinese", 99));
        list.add(new StudentScore(2, "english", 100));
        list.add(new StudentScore(2, "math", 133));
        list.add(new StudentScore(3, "chinese", 88));
        list.add(new StudentScore(3, "english", 140));
        list.add(new StudentScore(3, "math", 90));
        list.add(new StudentScore(4, "chinese", 108));
        list.add(new StudentScore(4, "english", 123));
        list.add(new StudentScore(4, "math", 114));
        list.add(new StudentScore(5, "chinese", 116));
        list.add(new StudentScore(5, "english", 133));
        list.add(new StudentScore(5, "math", 135));

        System.out.println(sum1(list));
        System.out.println(sum2(list));
    }

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
