package File;

import java.io.File;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/14 19:55
 * @description：
 * @modified By：
 * @version:
 */
public class Demo5 {
    public static void main(String[] args) {

        File file=new File("./test/111/222/333");
        //mkdir无法创建多级目录，只能创建一级目录
        //创建多级目录使用mkdirs
        boolean mkdir = file.mkdirs();
        System.out.println("resule= "+mkdir);
    }
}