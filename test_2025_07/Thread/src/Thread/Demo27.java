package Thread;

import java.util.PriorityQueue;
import java.util.Timer;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/11 17:19
 * @description：
 * @modified By：
 * @version:
 */
//abstract class MyTimerTask implements Runnable{
//
//    @Override
//    public abstract void run();
//}
    class MtTimerTask implements Comparable<MtTimerTask>{
        private Runnable task;
        //记录任务要执行的时刻
        private long time;

    public MtTimerTask(Runnable task, long time) {
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
    public int compareTo(MtTimerTask o) {
        return (int)(this.time-o.time);
    }
}
class MyTimer{
private PriorityQueue<MtTimerTask> queue=new PriorityQueue<>();

public void schedule(Runnable runnable,long delay){
synchronized (this){

            MtTimerTask mtTimerTask=new MtTimerTask(runnable,System.currentTimeMillis()+delay);
            queue.offer(mtTimerTask);
            this.notify();
    }}

public MyTimer(){
    Thread t=new Thread(()->{
        try {    while (true){
            synchronized (this){
                while (queue.isEmpty()){

                        this.wait();
                }
                        MtTimerTask task = queue.peek();
                    if (System.currentTimeMillis()<task.getTime()){
                        this.wait(task.getTime()-System.currentTimeMillis());
                    }
                    else {
                        task.run();
                        queue.poll();
                    }
            }}
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    t.start();

}
}
public class    Demo27 {
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

    }
}