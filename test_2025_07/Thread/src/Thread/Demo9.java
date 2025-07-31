package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/6 9:43
 * @description：
 * @modified By：
 * @version:
 */
public class Demo9 {
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
        System.out.println(t.getState());
        t.start();
        t.join();
        System.out.println(t.getState());
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("1");
//            }
//        });
//        t.start();
//        System.out.println("2");
    }
}
