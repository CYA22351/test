package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 11:17
 * @description：
 * @modified By：
 * @version:
 */
public class Demo13 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1=new Object();
        Object locker2=new Object();
        Thread t1=new Thread(()->{
            synchronized (locker1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } synchronized (locker2){
                System.out.println("t1两把锁都拿到了");
            }

        });
        Thread t2=new Thread(()->{
            synchronized (locker2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } synchronized (locker1){
                System.out.println("t2两把锁都拿到了");
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}