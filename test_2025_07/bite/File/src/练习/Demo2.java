package 练习;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/1 19:13
 * @description：
 * @modified By：
 * @version:
 */
public class Demo2 {
    public static void main(String[] args) throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你要复制的文件名：");
        String fileName1=scanner.next();
        File file1=new File(fileName1);
        if (!file1.exists()){
            System.out.println("此文件不存在！");
            return;
        }
        if (!file1.isFile()){
            System.out.println("文件不是普通文件！");
            return;
        }
        System.out.println("请输入要复制到地址： ");
        String fileName2=scanner.next();
        File file2=new File(fileName2);
        if (file2.exists()){
            if(file2.isDirectory()){
                System.out.println("该地址是个目录!");
                return;
            }
            if (file2.isFile()){
                System.out.println("是否要进行覆盖？y/n");
                String key=scanner.next();
                if (!key.contains("y")){
                    System.out.println("复制失败");
                    return;
                }
            }}
            try(InputStream inputStream=new FileInputStream(file1);
            OutputStream outputStream=new FileOutputStream(file2)) {
                byte[] bytes=new byte[1024];
                int n=0;
                while (true){
                    n=inputStream.read(bytes);
                    if (n==-1){
                        break;
                    }
                    outputStream.write(bytes,0,n);
                }

                outputStream.flush();
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        try(InputStream inputStream=new FileInputStream(file1)){
//            try(OutputStream outputStream=new FileOutputStream(file2)){
//                        byte[] buf=new byte[1024];
//                        int len=0;
//                        while (true){
//                            len=inputStream.read(buf);
//                            if (len==-1){
//                                break;
//                            }
//                            outputStream.write(buf,0,len);
//                        }
//                        outputStream.flush();
//
//            }
//
//        }
        System.out.println("复制成功");


    }
}