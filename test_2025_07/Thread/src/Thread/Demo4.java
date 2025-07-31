package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/5 10:10
 * @description：
 * @modified By：
 * @version:
 */
public class Demo4 {
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
        });
        t.setDaemon(true);//设置为后台线程
        t.start();
        for (int i=0;i<3;i++)
        {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
        System.out.println("main结束");
    }
}