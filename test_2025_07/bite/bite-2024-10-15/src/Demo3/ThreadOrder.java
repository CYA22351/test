package Demo3;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 21:49
 * @description：
 * @modified By：
 * @version:
 */
public class ThreadOrder {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            synchronized (ThreadOrder.class) {
                while (!Thread.currentThread().getName().equals("Thread-3")) {
                    try {
                        ThreadOrder.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                ThreadOrder.class.notifyAll();
            }
        });

        Thread b = new Thread(() -> {
            synchronized (ThreadOrder.class) {
                while (!Thread.currentThread().getName().equals("Thread-2")) {
                    try {
                        ThreadOrder.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                ThreadOrder.class.notifyAll();
            }
        });

        Thread c = new Thread(() -> {
            synchronized (ThreadOrder.class) {
                while (!Thread.currentThread().getName().equals("Thread-1")) {
                    try {
                        ThreadOrder.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                ThreadOrder.class.notifyAll();
            }
        });

        c.start();
        b.start();
        a.start();
        System.out.println(c.getName());
    }
}