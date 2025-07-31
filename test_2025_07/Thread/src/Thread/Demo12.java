package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 9:40
 * @description：
 * @modified By：
 * @version:
 */
class count{
    private int count=0;
    public void add(){
        synchronized (this){
            count++;
        }
    }
    public int getCount(){
        return count;
    }
}
public class Demo12 {
   // private  static int count=0;

    public static void main(String[] args) throws InterruptedException {
        count count1=new count();
        Object locker=new Object();
        Thread t1=new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                synchronized (count1) {
                        count1.add();
                }
            }
        }
    );
        t1.start();
        t1.join();
        System.out.println("count="+count1.getCount());
    }
}