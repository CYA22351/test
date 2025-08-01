package 练习;

import java.io.*;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 20:51
 * @description：
 * @modified By：
 * @version:
 */
public class Demo4 {
    public static void main(String[] args)throws IOException {
        try(OutputStream outputStream=new FileOutputStream("D:\\java\\code\\test_java\\test_2025_07\\bite\\File\\src\\练习\\hello.txt");
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"utf-8");
            PrintWriter printWriter=new PrintWriter(outputStream)){
            printWriter.println("我是第一行");
            printWriter.print("我是第二行");
            printWriter.printf("%d: 我的第三行\r\n",1+1);
            printWriter.flush();

        }
    }
}