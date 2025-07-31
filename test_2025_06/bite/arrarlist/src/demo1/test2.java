package demo1;
class ALG<E extends Comparable<E>>{
public E findMax(E[] array){
    E max=array[0];
    for (int i=1;i<array.length;i++){
        if (max.compareTo(array[i])<0){
            max=array[i];
        }
    }
    return max;
}
}
class ALG2{
    public static  <E extends Comparable<E>> E findMax(E[] array){
        E max=array[0];
        for (int i=1;i<array.length;i++){
            if (max.compareTo(array[i])<0){
                max=array[i];
            }
        }
        return max;
    }
}
public class test2 {
    public static void main(String[] args) {
        Integer[] array={1,2,3,4,5,6,7,8,9};
        int a=ALG2.findMax(array);
        System.out.println(a);
    }
    public static void main1(String[] args) {
    Integer[] array={1,2,3,4,5,6,7,8,9};
    ALG<Integer> alg=new ALG<>();
    int a=alg.findMax(array);
        System.out.println(a);
    }
}
