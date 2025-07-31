package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/11 11:30
 * @description：
 * @modified By：
 * @version:
 */
public class Demo24 {
    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(4);
       for (int i=0;i<1000;i++){
           int finalI = i;
           threadPool.submit(()->{
               System.out.println("hello "+ finalI +Thread.currentThread().getName());
           });
       }

    }
}