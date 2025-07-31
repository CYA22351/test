package File;

import java.io.*;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 17:35
 * @description：
 * @modified By：
 * @version:
 */
public class Demo11 {
    public static void main(String[] args) {
        try (Writer writer=new FileWriter("./output.txt",true)){
            BufferedWriter bufferedReader=new BufferedWriter(writer);
            bufferedReader.write("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}