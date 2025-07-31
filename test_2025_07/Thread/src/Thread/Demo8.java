package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/6 9:22
 * @description：
 * @modified By：
 * @version:
 */
public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
          while (true){

          }
        });
        System.out.println(t.getState());
        t.start();
        Thread.sleep(1000);
        System.out.println(t.getState());
    }
}