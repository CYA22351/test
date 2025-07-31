package 练习;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 9:39
 * @description：
 * @modified By：
 * @version:
 */
class MyBlockQueue{
    private String[] data=null;
    private int head=0;
    private int tail=0;
    private int size=0;
    public MyBlockQueue (int capacity){
        data=new String[capacity];
    }
    public void put(String elem) throws InterruptedException {
        synchronized (this){
        while (size>=data.length){
            this.wait();
        }
        data[tail]=elem;
        tail++;
        if (tail>=data.length){
            tail=0;
        }
        size++;
        this.notify();
    }}
    public String take() throws InterruptedException {
        synchronized (this){
            while(size==0){
                this.wait();
            }
            String da=data[head];
            head++;
            if (head>=data.length){
                head=0;
            }
            size--;
            this.notify();
            return da;
        }
    }
}
public class Demo4 {
    public static void main(String[] args) {
MyBlockQueue queue=new MyBlockQueue(1000);
Thread produce=new Thread(new Runnable() {
    @Override
    public void run() {
        int n=0;
        while (true){
            try {
                queue.put(n+" ");
                System.out.println("生产元素："+ n);
                n++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
});
Thread consumer=new Thread(new Runnable() {
    @Override
    public void run() {
        while (true){
            try {
                String n=queue.take();
                System.out.println("消费元素： "+n);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
});
produce.start();
consumer.start();
    }}