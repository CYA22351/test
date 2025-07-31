package demo2;

import java.util.Arrays;
import java.util.List;

public class ArrayList implements list {
    public int[] array;
    public int usesize;
    public static final int DEFAULT_CAPACITY = 10;
    public ArrayList(){
        this.array=new int[DEFAULT_CAPACITY];
    }

    @Override
    public void add(int data) {

        if (ifFull()){
            grow();
        }
        array[usesize]=data;
        this.usesize++;
    }

    public void grow(){
        this.array= Arrays.copyOf(this.array,2*this.array.length);//数组扩容
    }
    @Override
    public boolean ifFull() throws PosIllegal{
        return this.usesize==array.length;
    }
    public void checkpos(int pos){
        if (pos<0||pos>usesize){
            throw new PosIllegal("pos位置不合法");
        }
    }
    @Override
    public void add(int pos, int data) {
        try{
            checkpos(pos);
            if (ifFull()){
                grow();
            }
            for (int i=this.usesize-1;i>=pos;i--){
                this.array[i+1]=this.array[i];

            }
            this.array[pos]=data;
            usesize++;
        }catch (PosIllegal e)
        {
            System.out.println("插入元素pos位置不合法");
        }

    }

    @Override
    public void display() {
    for (int i=0;i<this.usesize;i++)
    {
        System.out.printf(this.array[i]+" ");

    }
    }

    @Override
    public boolean contains(int tofind) {
        for(int i=0;i<usesize;i++){
            if (array[i]==tofind){
                return true;
            }
        }
        return false ;
    }

    @Override
    public int indexof(int toFind) {

        for(int i=0;i<usesize;i++){
            if (array[i]==toFind){
                return i;
            }
        }return 0;
    }
public boolean ifEmpty(){
        return usesize==0;
}
public void checkEmpty(){
        if (ifEmpty()){
            throw new EmpetException("顺序表为空");
        }
}
    public void checkpos2(int pos){
        if (pos<0||pos>=usesize){
            throw new PosIllegal("pos位置不合法");
        }
    }
    @Override
    public int get(int pos) {
        try{
            checkEmpty();
            checkpos2(pos);
            return array[pos];
        }catch (PosIllegal e){
            e.printStackTrace();
        }catch (EmpetException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void set(int pos, int value) {
        try{
            checkEmpty();
            checkpos2(pos);
             array[pos]=value;
        }catch (PosIllegal e){
            e.printStackTrace();
        }catch (EmpetException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int toRemovbe) {
        try{
            checkEmpty();
            int pos=indexof(toRemovbe);
            if (pos==-1){
                    return;
            }
            for (int i=pos;i<usesize-1;i++){
                array[i]=array[i+1];
            }
            usesize--;
        }catch (EmpetException e){
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        usesize=0;
    }
}
