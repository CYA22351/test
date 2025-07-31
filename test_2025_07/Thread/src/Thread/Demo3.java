package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/4 21:55
 * @description：
 * @modified By：
 * @version:
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while (true){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t1");
        t.start();
        while (true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}