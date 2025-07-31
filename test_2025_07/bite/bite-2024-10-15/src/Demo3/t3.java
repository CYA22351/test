package Demo3;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 21:35
 * @description：
 * @modified By：
 * @version:
 */
public class t3 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1=new Object();
        Object locker2=new Object();
        Object locker3=new Object();

        Thread a=new Thread(()->{
            synchronized (locker1){

                    try {
                        Thread.currentThread().setName("a");
                        locker1.wait();
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }




        });
        Thread b=new Thread(()->{
            synchronized (locker2){

                    try {
                        Thread.currentThread().setName("b");
                        locker2.wait();
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

               synchronized (locker1){
                   locker1.notify();
               }

        });
        Thread c=new Thread(()->{
            synchronized (locker3){

                    try {
                        Thread.currentThread().setName("c");
                        locker3.wait();
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);

                }
        }
               synchronized (locker2){
                   locker2.notify();
               }

        });
        c.start();
        b.start();
        a.start();
        Thread.sleep(1000);
        synchronized (locker3){
            locker3.notify();
        }

    }
}