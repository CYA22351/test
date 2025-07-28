package network;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/21 17:01
 * @description：
 * @modified By：
 * @version:
 */
public class UdpDictServer extends UDPEchoServer{
    private HashMap<String,String> dict=new HashMap<>();
    public UdpDictServer(int port) throws SocketException {
        super(port);
        dict.put("小猫","cat");
        dict.put("小狗","dog");
        dict.put("小兔子","rabbit");
        dict.put("小鸭子","duck");
    }
    @Override
    public String process(String request){
        return  dict.getOrDefault(request,"未找到该词条");
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server=new UdpDictServer(9090);
        server.start();
    }
}