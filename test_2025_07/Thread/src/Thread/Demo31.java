package Thread;

import java.util.concurrent.Semaphore;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/13 17:10
 * @description：
 * @modified By：
 * @version:
 */
public class Demo31 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(3);
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
        semaphore.acquire();
        System.out.println("进行一次P操作");
    }
}