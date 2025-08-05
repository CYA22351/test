package 练习;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 20:05
 * @description：
 * @modified By：
 * @version:
 */
public class TchechoClient3 {
    private Socket socket=null;
    public TchechoClient3(String serverIP,int serverPort) throws IOException {
        socket=new Socket(serverIP,serverPort);
    }
    public void start(){
        Scanner scanner=new Scanner(System.in);
        try (InputStream inputStream=socket.getInputStream();
             OutputStream outputStream=socket.getOutputStream()){
            Scanner scanner1=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                System.out.println("输入你要发送的内容：");
                String request=scanner.next();
                printWriter.println(request);
                printWriter.flush();
                String response=scanner1.next();
                System.out.println(response);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TchechoClient3 client3=new TchechoClient3("127.0.0.1",9090);
        client3.start();
    }
}