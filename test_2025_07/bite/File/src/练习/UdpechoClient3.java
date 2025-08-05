package 练习;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/3 18:35
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechoClient3 {
    private DatagramSocket socket=null;
    //要定义
    private String serverIP;
    private int serverPort;
    public UdpechoClient3(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
        socket=new DatagramSocket();
    }
    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要发送的内容：");
            //scanner对象输入发送内容
            String request=scanner.next();
            //发送时要把数据和IP和端口号打包成数据报
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serverIP),serverPort);
          //发送给服务器
            socket.send(requestPacket);
            //创建一个字节数组的数据报
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
          //输出型参数
            socket.receive(responsePacket);
            //再将数据报转换成字符串输出
            String response=new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpechoClient3 client3=new UdpechoClient3("127.0.0.1",8080);
        client3.start();
    }
}