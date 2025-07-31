package Thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/11 16:58
 * @description：
 * @modified By：
 * @version:
 */
public class Demo26 {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        },2000);
    }
}