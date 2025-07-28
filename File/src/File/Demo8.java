package File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 15:43
 * @description：
 * @modified By：
 * @version:
 */
public class Demo8 {
    public static void main(String[] args) {
        try(OutputStream outputStream=new FileOutputStream("./output.txt",true)){
//
//            outputStream.write(97);
//            outputStream.write(98);
//            outputStream.write(99);
            byte[] bytes={99,98,97};
            outputStream.write(bytes);

        } catch (IOException e) {
            //此时需要处理两个异常
            throw new RuntimeException(e);
        }

    }
}