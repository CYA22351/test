package 练习;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 11:04
 * @description：
 * @modified By：
 * @version:
 */
public class TcpechoServer1 {
    private ServerSocket serverSocket=null;
    public TcpechoServer1 (int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true){
            Socket clientSocket=serverSocket.accept();
            processConnection(clientSocket);
        }
    }
    public void processConnection(Socket clientSocket)throws IOException{
        //getInetAddress()返回套接字的ip地址
        System.out.printf("[%s:%d],客户端上线!\n",clientSocket.getInetAddress(),clientSocket.getPort());
       //getInputStream(),getOutputStream(),Socket类提供了获取输入输出流对象的方法
        try(InputStream inputStream=clientSocket.getInputStream();
            OutputStream outputStream=clientSocket.getOutputStream()){
           //套一层缓冲
            Scanner scanner=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                if (!scanner.hasNext()){
                    System.out.printf("[%s:%d],客户端下线\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                String request=scanner.next();
                String response=process(request);
                printWriter.println(response);
                printWriter.flush();
                System.out.printf("[%s:%d] req:%s,resp:%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);
            }
        }
        clientSocket.close();
    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpechoServer1 server1=new TcpechoServer1(9090);
        server1.start();
    }
}