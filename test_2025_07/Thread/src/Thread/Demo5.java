package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/5 12:08
 * @description：
 * @modified By：
 * @version:
 */
public class Demo5 {
  public static boolean isFinished=false;
    public static void main(String[] args) throws InterruptedException {
    // boolean isFinished=false;
        Thread t=new Thread(()->{
            while(!isFinished){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        Thread.sleep(3000);
        isFinished=true;
    }
}