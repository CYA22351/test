package 练习;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 9:46
 * @description：
 * @modified By：
 * @version:
 */
//使用阻塞队列实现线程池
class myThreadPool{
    private BlockingQueue<Runnable> queue=null;
    public myThreadPool(int n){
        // n表示线程数

        queue=new LinkedBlockingQueue<>(n);
        for (int i=0;i<n;i++){
            Thread t=new Thread(()->{
                while (true){
                    try {
                        //取出任务并执行
                        Runnable runnable=queue.take();
                        runnable.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.start();
        }

    }
    //提交任务
    public void submit(Runnable runnable) throws InterruptedException {
        queue.put(runnable);
    }
}
public class MyThreadPool1 {
    public static void main(String[] args) throws InterruptedException {
        myThreadPool pool=new myThreadPool(10);
        for (int i=0;i<100;i++){
            int finalI = i;
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName()+" id="+ finalI);
            });
        }
    }
}