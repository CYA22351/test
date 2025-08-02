package 练习;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/2 19:49
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechServer {
    //构造网卡是UDPscoket，用于接发数据报
    private DatagramSocket socket=null;
    //提供端口号
    public UdpechServer(int port) throws SocketException {
        socket=new DatagramSocket(port);
    }
    public void start()throws IOException {
        System.out.println("服务器启动");
        //接受的数据保存在字符数组中，new byte[4006],4096长度
        DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
        //输出型参数，参数作为返回值，服务器会将接受的数据保存在该对象中
        socket.receive(requestPacket);
        //将读取的二进制数据保存在字符串中，并只保留有用的部分
        String request=new String(requestPacket.getData(),0,requestPacket.getLength());
       //根据请求返回响应
        String response=process(request);
        //将响应字符串转成二进制数据报
        //udp本身没有保存对方信息的功能，getSocketAddress()返回ip和端口
        DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
        //将响应数据报发送给客户端
        socket.send(responsePacket);
        System.out.printf("[%s:%d] req: %s,resp: %s\n",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);


    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpechServer server=new UdpechServer(9090);
        server.start();
    }

}