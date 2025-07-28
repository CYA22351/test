package File;

import java.io.File;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 18:15
 * @description：
 * @modified By：
 * @version:
 */
public class Demo3 {
    public static void main(String[] args) {
        File file=new File("./test.txt");
        boolean delete = file.delete();
        System.out.println(delete);
    }
}