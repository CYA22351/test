package 练习;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 19:54
 * @description：
 * @modified By：
 * @version:
 */
public class TcpechoServer3 {
    private ServerSocket socket=null;
    public TcpechoServer3(int port) throws IOException {
        socket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动！");

        while (true){
            Socket clientSocket=socket.accept();
            processConnection(clientSocket);
        }
    }
    public void processConnection(Socket clientSocket){
        System.out.printf("[%s:%d]客户端上线\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try (InputStream inputStream=clientSocket.getInputStream();
             OutputStream outputStream=clientSocket.getOutputStream()){
            Scanner scanner=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                if (!scanner.hasNext()){
                    System.out.printf("[%s:%d]客户端下线\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                String request=scanner.next();
                String response=process(request);
                printWriter.println(response);
                printWriter.flush();
                System.out.printf("[%s:%d] req:%s,resp:%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpechoServer3 server3=new TcpechoServer3(9090);
        server3.start();
    }
}