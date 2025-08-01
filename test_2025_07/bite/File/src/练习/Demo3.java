package 练习;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.WeakHashMap;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 20:30
 * @description：
 * @modified By：
 * @version:
 */
public class Demo3 {
    public static void main(String[] args)throws IOException {
        try(InputStream inputStream=new FileInputStream("D:\\java\\code\\test_java\\test_2025_07\\bite\\File\\src\\练习\\hello.txt");
            //scanner用来读取字符
            Scanner scanner=new Scanner(inputStream,"utf-8")){
            while (scanner.hasNext()){
                String s=scanner.next();
                System.out.println(s);
            }
        }
    }
}