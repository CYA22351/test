package Thread;

import java.util.concurrent.Semaphore;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 17:21
 * @description：
 * @modified By：
 * @version:
 */
public class Demo32 {
    private static int count=0;
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(1);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    count++;
                    semaphore.release();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    count++;
                    semaphore.release();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Fina count = "+count);

    }
}