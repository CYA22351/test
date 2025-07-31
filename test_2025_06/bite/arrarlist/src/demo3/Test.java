package demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/10 18:49
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);

        list.add(list1);
        List<Integer> list2=new ArrayList<>();
        list2.add(11);
        list2.add(21);
        list2.add(31);
        list.add(list2);
        System.out.println(list);


    }
    public static void main1(String[] args) {
        String str1="welcome to bit";
        String str2="come";
        ArrayList<Character> ret=new ArrayList<>();
        for(int i=0;i<str1.length();i++){
            char ch=str1.charAt(i);
            if (!str2.contains(ch+"")){
                ret.add(ch);
            }
        }
       for (int i=0;i<ret.size();i++){
           System.out.print(ret.get(i));
       }
    }
}