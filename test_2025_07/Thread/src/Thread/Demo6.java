package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/5 16:24
 * @description：
 * @modified By：
 * @version:
 */
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    break;
                }
            }
            System.out.println("t 结束");
        });
        t.start();
        Thread.sleep(3000);
        System.out.println("main 结束");
        t.interrupt();
    }
}