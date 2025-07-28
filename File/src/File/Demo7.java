package File;

import java.io.*;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 9:37
 * @description：
 * @modified By：
 * @version:
 */
public class Demo7 {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream=null;
//       try{
//           inputStream=new FileInputStream("./test.txt");
//       }finally {
//           //一定要close
//           inputStream.close();
//
//       }
//
//        //创建对象成功就相当于打开文件
        //try括号里里面要求这个类实现Closable接口
        try(InputStream inputStream=new FileInputStream("./test.txt")){
            while (true)
      {
          //一次读取一个字节

//                int data=inputStream.read();
//                if (data==-1){
//                    break;
//                }
//                System.out.printf("0x%x\n",data);
//            }
        byte[] data=new byte[1024];
        int n=inputStream.read(data);
        if (n==-1){
            break;
        }
        for (int i=0;i<n;i++){
            System.out.printf("0x%x\n",data[i]);
        }
      }}//出了try自动调用close
    }
}