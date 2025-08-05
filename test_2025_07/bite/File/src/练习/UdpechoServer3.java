package 练习;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 18:18
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechoServer3 {
    private DatagramSocket socket=null;
    public UdpechoServer3(int port) throws SocketException {
        socket=new DatagramSocket(port);
    }
    public void start()throws IOException {
        System.out.println("服务器启动!");
        while (true){
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request=new String(requestPacket.getData(),0,requestPacket.getLength());
            String response=process(request);
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);
            System.out.printf("[%s:%d] req:%s,resp:%s\n",requestPacket.getAddress(),requestPacket.getPort(),request,response);
        }
    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpechoServer3 server3=new UdpechoServer3(8080);
        server3.start();
    }
}