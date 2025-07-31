package Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/12 18:25
 * @description：
 * @modified By：
 * @version:
 */
public class Demo28 {
   // private static int count=0;
    private static AtomicInteger count=new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            for (int i=0;i<50000;i++){
                //count++
                count.getAndIncrement();
            }
        });
        Thread t2=new Thread(()->{
            for (int i=0;i<50000;i++){
                count.getAndIncrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count="+count.get());
    }
}