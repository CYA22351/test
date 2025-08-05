package 练习;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 9:21
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechoServer1 {
    private DatagramSocket socket=null;
    public UdpechoServer1 (int port) throws SocketException {
        socket=new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true){
            DatagramPacket requsetPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requsetPacket);
            String request=new String(requsetPacket.getData(),0,requsetPacket.getLength());
            String response=process(request);
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,requsetPacket.getSocketAddress());
            socket.send(responsePacket);
            System.out.printf("[%s:%d] req:%s,resp:%s\n",requsetPacket.getAddress(),requsetPacket.getPort(),request,response);
        }
    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpechoServer1 server1=new UdpechoServer1(8080);
        server1.start();

    }
}