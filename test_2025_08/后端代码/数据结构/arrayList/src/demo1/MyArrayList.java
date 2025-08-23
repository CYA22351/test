package demo1;

import javax.swing.plaf.PanelUI;
import java.util.Arrays;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/23 15:04
 * @description：
 * @modified By：
 * @version:
 */
public class MyArrayList implements ILit{
    public int[] array;
    public int usedSize;

    public MyArrayList() {
       array=new int[10];
    }
//判断顺序表是否为空
    public boolean isFull(){
        if (usedSize==array.length){
            return true;
        }
        return false;
    }

    //2倍扩容
    public void grow(){
        array= Arrays.copyOf(array,2*array.length);
    }
    //自定义扩容
    public void grow(int n){
        array= Arrays.copyOf(array,n*array.length);
    }

    //添加元素
    @Override
    public void add(int data) {
        if (isFull()){
            grow();
        }
        array[usedSize]=data;
        usedSize++;
    }

    //判断插入元素时pos是否合法
public void checkInsertPos(int pos){
        if (pos<0||pos>usedSize){
            throw new PosIllegalException("pos位置不合法"+pos);
        }
}

    @Override
    public void add(int pos, int data) {
        if (isFull()){
            grow();
        }
        checkInsertPos(pos);
        for (int i=usedSize-1;i>=pos;i--){
            array[i+1]=array[i];
        }
        array[pos]=data;
        usedSize++;

    }

    //判断顺序表中是否有该元素
    @Override
    public boolean contains(int toFind) {
        for (int i=0;i<usedSize;i++){
            if (array[i]==toFind){
                return true;
            }
        }
        return false;
    }
    //判断顺序表书是否为空
    public boolean isEmputy(){
        return usedSize==0;
    }

    //
    @Override
    public int indexOf(int toFind) {
        for (int i=0;i<usedSize;i++){
            if (array[i]==toFind){
                return i;
            }
        }
        return -1;
    }

    //判断获取元素位置是否合法
    public void checkGetSetPos(int pos){
        if (pos<0||pos>=usedSize){
            throw new PosIllegalException("获取元素pos位置不合法"+pos);
        }
    }

    //获取元素
    @Override
    public int get(int pos) {

        if (isEmputy()){
            throw new ListEmptyException("获取元素时，顺序表为空。。。");
        }
        checkGetSetPos(pos);
        return array[pos];
    }

    //更新顺序表元素
    @Override
    public void set(int pos, int value) {
        if (isEmputy()){
            throw new ListEmptyException("顺序表为空");

        }
        checkGetSetPos(pos);
        array[pos]=value;
    }

    @Override
    public void remove(int toRemove) {
        int index=indexOf(toRemove);
        if(index==-1){
            System.out.println("没有你要删除的数据...");
            return;
        }
        for(int i=index;i<usedSize-1;i++){
            array[i]=array[i+1];
        }
        usedSize--;


    }

    //获取顺序表实际长度
    @Override
    public int size() {
        return usedSize;
    }

    //清空顺序表
    @Override
    public void clear() {
        usedSize=0;

    }

    //打印顺序表
    @Override
    public void display() {
        for (int i=0;i<usedSize;i++){
            System.out.printf(array[i]+" ");
        }
        System.out.println();
    }
}