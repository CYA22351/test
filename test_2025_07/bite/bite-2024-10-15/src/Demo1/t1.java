package Demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 21:12
 * @description：
 * @modified By：
 * @version:
 */
public class t1 {
    private static volatile int count=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            for (int i=0;i<10000;i++){
                count++;
            }
        });
        Thread t2=new Thread(()->{
            for (int i=0;i<10000;i++){
                count++;
            }
        });
        t1.start();
        t1.join();
        t2.start();

        t2.join();
        System.out.println(count);

    }
}