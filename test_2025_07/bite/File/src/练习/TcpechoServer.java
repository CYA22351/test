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
 * @date ：Created in 2025/8/2 23:09
 * @description：
 * @modified By：
 * @version:
 */
public class TcpechoServer {
    private ServerSocket serverSocket=null;
    public TcpechoServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("启动服务器");
while (true){
    //对于tcp服务器，需要先处理客服端发来的连接
    //ServerSocketd.accept方法会接收到客户端的请求返回的是Socket
    //如果没有客户端连接，accept就会阻塞
    Socket clientSocket=serverSocket.accept();
    processConnection(clientSocket);
}
    }
    public void processConnection(Socket clientSocket)throws IOException{
        //getInetAddress()返回套接字连接的地址
        System.out.printf("[%s:%d] 客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        //getInputStream()返回套接字的输入流
        //getOutputStream()返回套接字的输出流
        try(InputStream inputStream=clientSocket.getInputStream();
            OutputStream outputStream=clientSocket.getOutputStream()) {
            Scanner scanner=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            //读取请求
            while (true){
                if (!scanner.hasNext()){
                    System.out.printf("[%s:%d] 客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                String request=scanner.next();
                String response=process(request);
                printWriter.println(response);
                printWriter.flush();
                System.out.printf("[%s:%d] req:%s,req:%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);
            }
        }
        clientSocket.close();
    }
    private String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpechoServer server=new TcpechoServer(8080);
        server.start();
    }
}