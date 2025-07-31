package Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/9 11:00
 * @description：
 * @modified By：
 * @version:
 */
public class Demo18 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue=new LinkedBlockingQueue<>(100);
       for (int i=0;i<100;i++){
           queue.put("aaa");
       }
        System.out.println("队列已满");
        queue.put("aaa");
        System.out.println("在添加");
    }
}