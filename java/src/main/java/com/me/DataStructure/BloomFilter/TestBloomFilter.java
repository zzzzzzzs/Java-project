package com.me.DataStructure.BloomFilter;

/**
 * @author zs
 *@date 2021/9/5.

 */
public class TestBloomFilter {
    public static void main(String[] args) {
        MyBloomFilter myNewBloomFilter = new MyBloomFilter();
        myNewBloomFilter.add("张学友");
        myNewBloomFilter.add("郭德纲");
        myNewBloomFilter.add("蔡徐鸡");
        myNewBloomFilter.add(666);
        System.out.println(myNewBloomFilter.isContain("张学友"));//true
        System.out.println(myNewBloomFilter.isContain("张学友 "));//false
        System.out.println(myNewBloomFilter.isContain("张学友1"));//false
        System.out.println(myNewBloomFilter.isContain("郭德纲"));//true
        System.out.println(myNewBloomFilter.isContain("蔡徐老母鸡"));//false
        System.out.println(myNewBloomFilter.isContain(666));//true
        System.out.println(myNewBloomFilter.isContain(888));//false
    }
}
