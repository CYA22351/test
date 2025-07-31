package demo1;
class MyArray<E>{
    public Object[] array=new Object[10];
    public void serValue(int pos,E val){
        this.array[pos]=val;
    }
    public E getValue(int pos){
        return (E)this.array[pos];
    }
}
public class test {
    public static void main(String[] args) {
        MyArray<Integer> myArray=new MyArray<>();
        myArray.serValue(0,20);
        myArray.serValue(1,10);
        Integer a=myArray.getValue(1);
        System.out.println(a);
    }
}
