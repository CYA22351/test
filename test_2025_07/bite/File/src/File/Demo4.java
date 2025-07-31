package File;

import java.io.File;
import java.util.Arrays;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 18:34
 * @description：
 * @modified By：
 * @version:
 */
public class Demo4 {
    public static void main(String[] args) {
        //针对文件是无法进行list的
        File file=new File("./");
        String[] list=file.list();
        System.out.println(Arrays.toString(list) );
        File[] files=file.listFiles();
        System.out.println(Arrays.toString(files));
    }
}