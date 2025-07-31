package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/6 10:05
 * @description：
 * @modified By：
 * @version:
 */
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        Thread mainthread=Thread.currentThread();
        Thread t1=new Thread(()->{
            try {
                System.out.println("开始等待 mian");
                mainthread.join();
                System.out.println("结束等待 main");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        Thread.sleep(3000);
        System.out.println("mian 结束");
    }
}