package File;

import java.io.File;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 20:03
 * @description：
 * @modified By：
 * @version:
 */
public class Demo6 {
    public static void main(String[] args) {
        //重命名能起到启动的作用
        File file=new File("./test2");
        File newfile=new File("./src/text2");
        boolean b = file.renameTo(newfile);
        System.out.println(b);
    }
}