package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 12:14
 * @description：
 * @modified By：
 * @version:
 */
public class Demo30 {
   private static int tota=0;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
               int sum=0;
                for (int i=1;i<=100;i++){
                    sum+=i;
                }
                tota=sum;
            }
        };
        Thread t=new Thread(runnable);
        t.start();
        t.join();
        System.out.println("tatoal="+ tota);
    }
}