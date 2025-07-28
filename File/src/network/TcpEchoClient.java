package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/21 21:59
 * @description：
 * @modified By：
 * @version:
 */
public class TcpEchoClient {
    private Socket socket = null;
//    private String serverIP;
//    private int serverPort;
    public TcpEchoClient(String serverIP,int serverPort) throws IOException {
        socket=new Socket(serverIP,serverPort);
    }
    public void start(){
        Scanner scanner=new Scanner(System.in);
        try(InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream())
        {
            Scanner scanner1=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                //从控制器读取用户输入
                String request=scanner.next();
                //发送给服务器
                //只是将数据放到缓冲区，没有真正写入网卡，需要刷新缓冲区，
                printWriter.println(request);
                printWriter.flush();
                //获取服务器返回的响应
                String response=scanner1.next();
                //打印响应
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client=new TcpEchoClient("127.0.0.1",8080);
        client.start();
    }
}