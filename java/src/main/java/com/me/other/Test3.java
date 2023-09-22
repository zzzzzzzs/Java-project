package com.me.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test3 {
    public static void main(String[] args){//猜密码
        int input[]={2,0};
        int ken =1;
        List<Integer> list = new LinkedList<>();//数组存入list
        int a[]=new int[input.length];
        Arrays.sort(input);
        for(int i=0;i<input.length;i++){
            ((LinkedList<Integer>) list).addLast(input[i]);
        }
        List<Integer> res = new LinkedList<>();
        if(ken>input.length)
            System.out.print("None");
        else {
            dfs(list, res, 0, 2);
        }
    }
    public  static  void dfs(List<Integer>list,List<Integer>res,int i,int ken){
        if(res.size()>=ken){
            for(int a:res){
                if(a!=res.get(res.size()-1)){
                    System.out.print(a+",");
                }else {
                    System.out.println(a);
                }
            }
        }
        for(;i<list.size();i++){
            if(res.size()==0||list.get(i)>res.get(res.size()-1)){//找出所有可能组合
                res.add(list.get(i));
                dfs(list, res,i, ken);
                res.remove(res.size()-1);
            }
        }
    }
}