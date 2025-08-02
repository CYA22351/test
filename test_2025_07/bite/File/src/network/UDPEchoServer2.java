package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/20 9:40
 * @description：
 * @modified By：
 * @version:
 */
public class UDPEchoServer2 {
    //网卡端口
    private DatagramSocket socket=null;
    public UDPEchoServer2(int port) throws SocketException {
        socket=new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true){
            //1.读取请求并解析
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request=new String(requestPacket.getData(),0,requestPacket.getLength());
            String response=process(request);
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);
            System.out.println();

            System.out.printf("[%s:%d] req: %s,resp: %s\n",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
        }
    }
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer2 server2=new UDPEchoServer2(9090);
        server2.start();
    }
}