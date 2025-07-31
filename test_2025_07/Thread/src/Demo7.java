/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/5 17:50
 * @description：
 * @modified By：
 * @version:
 */
public class Demo7 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            for (int i=0;i<3;i++){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("t 结束 ");
        });
        t.start();
        t.join(3000);
        System.out.println("main 结束");
    }
}