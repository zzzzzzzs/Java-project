package com.me.Iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author zs
 * @date 2021/9/21.
 * 迭代器，使用泛型，可以操纵多个类型
 */

public class MyIterator<E> implements java.lang.Iterable {

    private Object[] elem = new Object[5];//泛型没有数组，因此使用Object数组
    private int size;


    public int size() {
        return this.size;
    }

    public boolean add(E ele) {
        if (this.size == elem.length) {
            //String数组容量不够了，就扩容
            elem = Arrays.copyOf(elem, elem.length + 5);
        }
        elem[size++] = ele;
        return true;
    }

    /*	 ...其他方法get、remove、clear等方法就不再赘述了...	 */

    public Iterator<E> iterator() {

        return new Iterator<E>() {  //创建Iterator迭代器接口的实现类（没有名称）的对象
            //计数器：指针、游标, 指向当前访问过的元素
            private int coursor = -1;

            //判断是否存在下一个
            public boolean hasNext() {
                return (coursor + 1) < size;
            }

            //获取下一个
            public E next() {
                coursor++;
                return (E) elem[coursor];
            }

            //删除下一个
            public void remove() {
                //移动数组的元素
                System.arraycopy(elem, coursor + 1, elem, coursor, size - coursor - 1);
                size--;
                //退回上一个访问的元素,即可实现多次删除
                this.coursor--;
            }
        };
    }

    public static void main(String[] args) {
        MyIterator<String> s = new MyIterator<String>();
        s.add("111");
        s.add("222");
        s.add("222");
        //System.out.println(s.size());
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        MyIterator<Integer> s2 = new MyIterator<Integer>();
        s2.add(1);  //int-->Integer自动装箱
        s2.add(2);
        for (Object i : s2) {
            System.out.println(i);
        }
    }
}
