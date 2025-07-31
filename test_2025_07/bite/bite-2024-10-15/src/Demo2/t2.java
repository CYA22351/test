package Demo2;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 21:19
 * @description：
 * @modified By：
 * @version:
 */
public class t2 {
    public static void main(String[] args) throws InterruptedException {
        Object object1=new Object();
        Object object2=new Object();
        Object object3=new Object();
        Thread t1=new Thread(()->{
                for (int i=0;i<10;i++){
                    synchronized (object1){
                        try {
                            object1.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("A");
                    synchronized (object2){
                        object2.notify();
                    }
                }
        });
        Thread t2=new Thread(()->{
            for (int i=0;i<10;i++){
                synchronized (object2){
                    try {
                        object2.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("B");
                synchronized (object3){
                    object3.notify();
                }
            }
        });
        Thread t3=new Thread(()->{
            for (int i=0;i<10;i++){
                synchronized (object3){
                    try {
                        object3.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("C");
                synchronized (object1){
                    object1.notify();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000);
        synchronized (object1){
            object1.notify();
        }

    }
}