package Thread;

import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/9 21:52
 * @description：
 * @modified By：
 * @version:
 */
public class Demo21 {
    private static volatile int count1=0;
    private static  volatile  int count2=0;
    public static void main(String[] args) throws InterruptedException {
        int length=10000000;

        int[] arr=new int[length];
        Random random=new Random();
        for (int i=0;i<length;i++)
        {
            arr[i]=random.nextInt(100)+1;
        }
        Thread t1=new Thread(()->{

            for (int i=0;i<length;i+=2){
                count1+=arr[i];
            }
            System.out.println("偶数下标和为 "+count1);
        });
        Thread t2=new Thread(()->{

            for (int i=1;i<length;i+=2){
                count2+=arr[i];
            }
            System.out.println("奇数下标和为 "+count2);
        });
        long startTime=System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        int sum=count1+count2;
        long endTime=System.nanoTime();
        long Time=(endTime-startTime)/1000000;
        System.out.println("总和为："+sum);
        System.out.println("总耗时为："+Time+"毫秒");


    }
}