package File;

import java.io.File;
import java.io.IOException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 17:27
 * @description：
 * @modified By：
 * @version:
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        File file=new File("./test.txt");
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}