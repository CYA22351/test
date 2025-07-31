package 练习;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 8:45
 * @description：
 * @modified By：
 * @version:
 */
class MyTimerTask implements Comparable<MyTimerTask>{
    private Runnable task;
    private long time;

    public MyTimerTask(Runnable task, long time) {
        this.task = task;
        this.time = time;
    }
    public long getTime(){
        return time;
    }
    public void run(){
        task.run();
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int)(this.time-o.time);
    }


}
class MyTimer{
    private PriorityQueue<MyTimerTask> queue=new PriorityQueue<>();
    public void schedule(Runnable runnable ,long delay){
       synchronized (this){
           MyTimerTask task=new MyTimerTask(runnable,System.currentTimeMillis()+delay);
           queue.offer(task);
           this.notify();
       }
    }
    public MyTimer(){
        Thread t=new Thread(()->{
            try {
                while (true){
                synchronized (this){
                    while (queue.isEmpty()){

                            this.wait();
                        }
                    MyTimerTask task=queue.peek();
                    if (System.currentTimeMillis()<task.getTime()){
                        this.wait(task.getTime()-System.currentTimeMillis());
                    }
                    else {
                        task.run();
                        queue.poll();
                    }
                    }
                }
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }

}
public class Demo1 {
    public static void main(String[] args) {
        MyTimer timer=new MyTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        },3000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 2000");
            }
        },2000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 1000");
            }
        },1000);
        Executors.newScheduledThreadPool(4);

    }
}
