package 练习;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/2 20:29
 * @description：
 * @modified By：
 * @version:
 */
public class UdpechoClient {
    private DatagramSocket socket=null;
    //udp本身不保存对端的信息，所以要将服务器的ip和端口保存下来传给服务器
    private String serverIP;
    private int serverPort;
    public UdpechoClient(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
        socket=new DatagramSocket();//使用无参构造
    }
    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要发送的内容");
            // 输入用户请求
            String request=scanner.next();
            //将用户请求字符串转换成字符数组
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serverIP),serverPort);
        //发送数据
            socket.send(requestPacket);
            //构造接受数据报，字节数组
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);//输出型参数，将服务器返回的数据保存在responsePacket中
            //将返回的数据报转成字符串，字符数组不一定用完，从0到实际长度
            String response=new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpechoClient client=new UdpechoClient("127.0.0.1",9090);
        client.start();
    }
}