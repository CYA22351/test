package 练习;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/31 22:05
 * @description：
 * @modified By：
 * @version:
 */
public class BlockingQueue1 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue=new LinkedBlockingQueue<>(1000);
        Thread producer=new Thread(()->{
            int n=0;
            while (true){
                try {
                    queue.put(n);
                    System.out.println("生产元素："+n);
                    n++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer=new Thread(()->{
            while (true){
                try {
                    Integer n=queue.take();
                    System.out.println("消费铁元素："+n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
    }
}