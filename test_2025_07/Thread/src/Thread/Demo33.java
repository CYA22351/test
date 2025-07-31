package Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 17:51
 * @description：
 * @modified By：
 * @version:
 */
public class Demo33 {
    public static void main(String[] args) throws InterruptedException {
        //构造方法中的参数表示任务的个数
        CountDownLatch latch=new CountDownLatch(10);
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        for (int i=0;i<10;i++){

            int id=i;
            executorService.submit(()->{
                System.out.println("子任务开始执行："+id );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子任务结束执行："+id);
                latch.countDown();
            });

        }
        latch.await();
        System.out.println("所有任务都执行完毕");
        executorService.shutdown();
    }
}