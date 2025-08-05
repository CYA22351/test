package 练习;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 9:56
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechoClient1 {
    private DatagramSocket socket=null;
    private String serverIP;
    private int serverPort;
    public UdpechoClient1(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
        socket=new DatagramSocket();
    }
    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要发送的内容:");
            String request=scanner.next();
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response=new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpechoClient1 client1=new UdpechoClient1("127.0.0.1",8080);
        client1.start();
    }

}