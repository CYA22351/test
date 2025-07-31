package 练习;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 15:49
 * @description：
 * @modified By：
 * @version:
 */
public class Demo2 {
   private static int count=0;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                for (int i=1;i<=100;i++){
                    count+=i;
                }

                return count;
            }

        };
        FutureTask task=new FutureTask(callable);
        Thread t=new Thread(task);
        t.start();
        System.out.println(task.get());
    }
}