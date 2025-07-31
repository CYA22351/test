package Thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/11 12:01
 * @description：
 * @modified By：
 * @version:
 */
class MyThreadPool{
    private BlockingQueue<Runnable> queue=null;
    public MyThreadPool (int n){
        // n 表⽰线程池⾥有⼏个线程.
        // 创建了⼀个固定数量的线程池
        queue=new LinkedBlockingQueue<>(1000);
        for (int i=0;i<n;i++){
            Thread t=new Thread(()->{
                while (true){
                    try {
                        // 取出任务, 并执⾏~~
                       Runnable task= queue.take();
                       task.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.start();
        }
    }
    // 通过这个⽅法, 来把任务添加到线程池中
    public void submit (Runnable runnable) throws InterruptedException {
        queue.put(runnable);
    }
}
public class Demo25 {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool=new MyThreadPool(10);
        for (int i=0;i<100;i++){
            int finalI = i;
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName()+" id="+ finalI);
            });
        }
    }
}