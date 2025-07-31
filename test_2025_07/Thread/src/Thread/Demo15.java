package Thread;

import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 16:01
 * @description：
 * @modified By：
 * @version:
 */
public class Demo15 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1=new Thread(()->{
            synchronized (object) {
                try {
                    System.out.println("t1等待中");

                    object.wait();
                    System.out.println("t1等待结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread t2=new Thread(()->{
            synchronized (object) {
                try {
                    System.out.println("t2等待中");

                    object.wait();
                    System.out.println("t2等待结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread t3=new Thread(()->{
            Scanner scanner=new Scanner(System.in);
            System.out.println("输入任意值，唤醒线程");
            scanner.next();
            synchronized (object){
                object.notify();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}