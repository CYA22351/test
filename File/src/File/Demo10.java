package File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 17:26
 * @description：
 * @modified By：
 * @version:
 */
public class Demo10 {
    public static void main(String[] args) {
        try(InputStream inputStream=new FileInputStream("")){

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
