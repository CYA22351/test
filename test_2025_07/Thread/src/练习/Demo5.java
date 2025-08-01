package 练习;

import java.util.PriorityQueue;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 10:06
 * @description：
 * @modified By：
 * @version:
 */
class MtTimerTASK implements Comparable<MtTimerTASK>{
    //定义任务和要执行任务的时刻
private  Runnable task;
private long time;
//构造方法
public MtTimerTASK(Runnable task,long time){
    this.task=task;
    this.time=time;
}
//获得当前任务运行的时刻
public long getTime(){
    return time;
}
//运行任务
public void run(){
    task.run();
}
    //实现Comparable接口判断运行时间
    @Override
    public int compareTo(MtTimerTASK o) {
        return (int)(this.time-o.time);
    }
}
class MyTIMER{
    //优先队列
    public PriorityQueue<MtTimerTASK> queue=new PriorityQueue<>();
    //添加任务列表
    public void scheule(Runnable runnable,long delay){
        synchronized (this){
            MtTimerTASK task=new MtTimerTASK(runnable,System.currentTimeMillis()+delay);
            queue.offer(task);
            this.notify();
        }
    }
    public MyTIMER(){
        Thread t=new Thread(()->{
            while (true){
                synchronized (this){
                    while (queue.isEmpty()){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    MtTimerTASK task=queue.peek();

                    if (System.currentTimeMillis()<task.getTime()){
                        try {
                            this.wait(task.getTime()-System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        task.run();
                        queue.poll();
                    }
                }
            }
        });
        t.start();

    }
}
public class Demo5 {
    public static void main(String[] args) {
        MyTIMER timer=new MyTIMER();
        //添加任务
        timer.scheule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,1000");
            }
        },1000);
        timer.scheule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,2000");
            }
        },2000);
        timer.scheule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,3000");
            }
        },3000);
    }

}