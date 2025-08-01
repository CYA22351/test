package 练习;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 16:04
 * @description：
 * @modified By：
 * @version:
 */
public class Demo6 {

    private static AtomicInteger cout=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            for (int i=0;i<50000;i++){
                //count++
                cout.getAndIncrement();
            }
        });
        Thread t2=new Thread(()->{
            for (int i=0;i<50000;i++){
                cout.getAndIncrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(cout);
    }
}