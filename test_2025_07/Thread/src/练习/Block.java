package 练习;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/31 22:24
 * @description：
 * @modified By：
 * @version:
 */
class MyBlockingQUeue{
    private String[] data=null;
    private int head;
    private int tail;
    private int size;
    public MyBlockingQUeue(int capacity){
        data=new String[capacity];
    }
    public void put(String elem) throws InterruptedException {
        synchronized (this){
            while (size>= data.length){
                this.wait();
            }
            data[tail]=elem;
            tail++;
            if (tail>=data.length){
                tail=0;
            }
            size++;
            this.notify();
        }

    }
    public String take() throws InterruptedException {
        synchronized (this){
            while (size==0){
                this.wait();
            }
            String ret=data[head];
            head++;
            if (head>=data.length){
                head=0;
            }
            size--;
            this.notify();
            return ret;
        }
    }
}


public class Block {
    public static void main(String[] args) {
        MyBlockingQUeue qUeue=new MyBlockingQUeue(1000);
        Thread procuder=new Thread(()->{

            while (true){  int n=0;
                try {
                    qUeue.put(n+" ");
                    System.out.println("生产元素："+n);
                    n++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer=new Thread(()->{
            while (true){
                try {
                    String n=qUeue.take();
                    System.out.println("消费元素："+n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        procuder.start();
        consumer.start();
    }
}