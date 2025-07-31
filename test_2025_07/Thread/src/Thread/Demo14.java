package Thread;

import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/7 14:08
 * @description：
 * @modified By：
 * @version:
 */
public class Demo14 {
    private static volatile int flag=0;
    public static void main(String[] args) {
        Thread t1=new Thread(() ->{
            while (flag==0){

            }
            System.out.println("t1 线程结束");
        });
        Thread t2=new Thread(() ->{
            Scanner scanner=new Scanner(System.in);
            System.out.println("输入flag的值： ");
            flag=scanner.nextInt();
        });
        t1.start();
        t2.start();
    }
}