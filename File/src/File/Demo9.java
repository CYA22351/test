package File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 16:08
 * @description：
 * @modified By：
 * @version:
 */
public class Demo9 {
    public static void main(String[] args) {
        try (Reader reader=new FileReader("./test.txt")){
            while (true){
//                int n=reader.read();
//                if (n==-1){
//                    break;
//                }
//                System.out.println((char) n);
                char[] buf=new char[1024];
                int n=reader.read(buf);
                if (n==-1){
                    break;}
                for (int i=0;i<n;i++){
                    System.out.println(buf[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}