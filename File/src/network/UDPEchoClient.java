package network;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/19 10:53
 * @description：
 * @modified By：
 * @version:
 */
public class UDPEchoClient {
    private DatagramSocket socket=null;
    //UDP本身不保存对端的信息
    // 服务器的IP和端口号
    private String serverIP;
    private int serverPort;
    public UDPEchoClient(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
            socket=new DatagramSocket();//一定使用无参的构造方法
    }
    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);

        while (true){
            System.out.println("请输入发送的内容:");
            String request=scanner.next();
            //2. 把请求发送给服务器，需要在构造方法中指定数据，数据来源，数据目的地
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serverIP),serverPort);
            //3. 发送数据
            socket.send(requestPacket);
            //4. 接受服务器的响应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            // 5. 从服务器读取的数据进行解析，打印出来
            String resopnse=new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(resopnse);
        }


    }

    public static void main(String[] args) throws IOException {
        UDPEchoClient client=new UDPEchoClient("127.0.0.1",9090);
        client.start();
    }
}