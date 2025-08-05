package 练习;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 11:58
 * @description：
 * @modified By：
 * @version:
 */
public class TcpechoServer2 {
    private ServerSocket serverSocket=null;
    public TcpechoServer2(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void statr() throws IOException {
        System.out.println("启动服务器");
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true){
            Socket clientSocket=serverSocket.accept();
            executorService.submit(()->{
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    private void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try (InputStream inputStream=clientSocket.getInputStream();
             OutputStream outputStream=clientSocket.getOutputStream()){
            Scanner scanner=new Scanner(inputStream);
            PrintWriter printWriter=new PrintWriter(outputStream);
            while (true){
                if (!scanner.hasNext()){
                    System.out.printf("[%s:%d]客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                String request=scanner.next();
                String response=process(request);
                printWriter.println(response);
                printWriter.flush();
                System.out.printf("[%s:%d] req:%d,resp:%d\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            clientSocket.close();
        }
    }
    private String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpechoServer2 server2=new TcpechoServer2(9090);
        server2.statr();
    }
}