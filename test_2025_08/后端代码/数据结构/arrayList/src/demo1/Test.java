package demo1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/23 15:03
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
        public static void main(String[] args) {
                ArrayList<Integer> arrayList=new ArrayList<>();
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                System.out.println(arrayList);
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