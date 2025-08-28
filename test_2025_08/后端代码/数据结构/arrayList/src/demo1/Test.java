package demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/23 15:03
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
        public static List<List<Integer>> generate(int numRows) {
                List<List<Integer>> ret=new ArrayList<>();
                List<Integer> list=new ArrayList<>();
                //先将第一行加入集合
                list.add(1);
                ret.add(list);

                for(int i=1;i<numRows;i++){
                        List<Integer> curRow=new ArrayList<>();
                        //第一个1
                        curRow.add(1);
                        //中间数

                        //获取上一行的集合
                        List<Integer> preRow=ret.get(i-1);
                        for(int j=1;j<i;j++)
                        {
                                int val=preRow.get(j)+preRow.get(j-1);
                                curRow.add(val);

                        }
                        //最后一个1
                        curRow.add(1);

                        //将每一个完整行加入集合
                        ret.add(curRow);

                }

                return ret;
        }

        public static void main(String[] args) {
                List<List<Integer>> generate = generate(3);
                for (List<Integer> x:generate){
                        System.out.println(x);
                }
        }
        public static void main2(String[] args) {
                ArrayList<Integer> arrayList=new ArrayList<>();
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(4);
                arrayList.add(5);
                System.out.println(arrayList);
                //for
                for (int i=0;i<arrayList.size();i++){
                        System.out.print(arrayList.get(i)+" ");
                }
                System.out.println();
                //增强for
                for (Integer x:arrayList){
                        System.out.print(x+" ");
                }
                System.out.println();
                //迭代器
                Iterator<Integer> iterator = arrayList.iterator();
                while (iterator.hasNext()){
                        System.out.print(iterator.next()+" ");
                }
                System.out.println();
                //subList是另建索引。指向1下标，修改integers的零下标就是修改arrayList的1下标
//                List<Integer> integers = arrayList.subList(1, 4);
//                System.out.println(integers);
//                System.out.println("===============");
//                integers.set(0,22);
//                System.out.println(integers);
//                System.out.println(arrayList);
//                arrayList.add(0,99);
//                System.out.println(arrayList);
//                arrayList.remove(new Integer(99));
//                System.out.println(arrayList);
        }
    public static void main1(String[] args) {
MyArrayList list=new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.display();
        list.remove(1);
        list.display();
        list.remove(99);
        list.display();

    }
}