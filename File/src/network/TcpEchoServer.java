package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/21 17:54
 * @description：
 * @modified By：
 * @version:
 */
public class TcpEchoServer {
    private ServerSocket serverSocket=null;
    public TcpEchoServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("启动服务器");
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true){
            //对于tcp服务器，需要先处理客服端发来的连接
            //通过读写clientSocket来处理客户端的请求
            //如果没有客户端发起连接，此时accept就会阻塞
            Socket cilentSocket = serverSocket.accept();
//            Thread t=new Thread(()->{
//                try {
//                    processConnection(cilentSocket);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            });
//            t.start();
            executorService.submit(()->{
                try {
                    processConnection(cilentSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        //处理一个客户端的连接。
        //可能要涉及到多个客户端的请求和响应

    }
    private void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try(InputStream inputStream=clientSocket.getInputStream();
            OutputStream outputStream=clientSocket.getOutputStream()){
            //针对InputStream套一层
            Scanner scanner=new Scanner(inputStream);
            //针对 OutoutStream套一层
            PrintWriter printWriter=new PrintWriter(outputStream);
            //1. 读取请求并解析
            while (true){
                //判断收到数据是否包含空白符，换行，回车，制表符，翻页符
                if (!scanner.hasNext()){
                    System.out.printf("[%s:%d]客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                String request=scanner.next();
                //2. 根据请求，计算响应
                String response=process(request);
                //3. 把响应返回给客户端
                printWriter.println(response);
                printWriter.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s,resp:%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),response,response);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }finally {
            clientSocket.close();
        }
    }
    private String process(String request){
        return request;
    }
    public static void main(String[] args) throws IOException {
        TcpEchoServer server=new TcpEchoServer(8080);
        server.start();
    }
}