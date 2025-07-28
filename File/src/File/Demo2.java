package File;

import java.io.File;
import java.io.IOException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 17:40
 * @description：
 * @modified By：
 * @version:
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file=new File("./test.txt");
        file.createNewFile();
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
    }
}