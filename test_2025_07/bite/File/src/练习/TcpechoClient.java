package 练习;

import network.TcpEchoClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/2 23:39
 * @description：
 * @modified By：
 * @version:
 */
public class TcpechoClient {
    private Socket socket=null;
    public TcpechoClient(String serverIP,int serverPort) throws IOException {
        socket=new Socket(serverIP,serverPort);

    }
    public void start()throws IOException{
        Scanner scanner=new Scanner(System.in);
        try(InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream()){
            Scanner scanner1=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                String request=scanner.next();
                printWriter.println(request);
                printWriter.flush();
                String response=scanner1.next();
                System.out.println(response);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client=new TcpEchoClient("127.0.0.1",8080);
        client.start();
    }
}