package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/19 10:53
 * @description：
 * @modified By：
 * @version:
 */
public class UDPEchoServer {
    //网卡文件
    private DatagramSocket socket=null;
    public UDPEchoServer(int port) throws SocketException {
        //提供端口号，让服务器来使用
        socket=new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true){
            //1.读取请求并解析


            //DatagramPacket表示一个UDP的数据报，传入字节数组,保存UDP的载荷部分
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);//输出型参数，参数作为返回值
            //把读取到的二进制数据转成字符串，只构造有效的部分
            String request=new String(requestPacket.getData(),0,requestPacket.getLength());
            //2.根据请求，计算响应，最关键的步骤
            String response=process(request);

            //3.把响应返回给客户端
            //使用字符的长度
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            //Udp协议自身没有保存对方信息的功能
            //getSocketAddress()返回ip和端口

            socket.send(responsePacket);
     System.out.printf("[%s:%d] req: %s,resp: %s\n",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer server=new UDPEchoServer(9090);
        server.start();
    }

}