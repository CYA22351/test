package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/4 11:52
 * @description：
 * @modified By：
 * @version:
 */
//表示成一个任务
class MyRunnable implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class Dome2 {
    public static void main(String[] args) throws InterruptedException {
         Runnable runnable=new MyRunnable();
         //线程要明确自己的任务
         Thread t=new Thread(runnable);
         t.start();
         while (true){
             System.out.println("hello main");
             Thread.sleep(1000);
         }
    }
}