package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/3 11:15
 * @description：
 * @modified By：
 * @version:
 */
class Mythread2 implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("hello t3");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Mythread1 extends  Thread{
    @Override
    public void run() {
      while (true){
          System.out.println("hello t1");
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      }
    }
}
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Mythread1();
       Thread t2=new Thread(){
           public void run(){
             while(true){
                 System.out.println("hello t2");
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }
           }
       };

       Thread t3=new Thread(new Mythread2());
       Thread t4=new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("hello t4");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });
       Thread t5=new Thread(()->{
           while (true){
               System.out.println("hello t5");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });
        //真正创建一个新线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        while (true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}