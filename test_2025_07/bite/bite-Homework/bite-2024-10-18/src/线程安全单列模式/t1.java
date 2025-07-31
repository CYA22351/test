package 线程安全单列模式;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/9 18:41
 * @description：
 * @modified By：
 * @version:
 */
class SiglonLazy{
    private static volatile SiglonLazy instance=null;
    private static Object object=new Object();
    public static SiglonLazy getInstance(){
        if (instance==null){
            synchronized (object){
                if (instance==null){
                    instance=new SiglonLazy();
                }

            }
        }
        return instance;
    }
}
public class t1 {
    public static void main(String[] args) {
SiglonLazy siglonLazy1=SiglonLazy.getInstance();
        SiglonLazy siglonLazy2=SiglonLazy.getInstance();
        System.out.println(siglonLazy1==siglonLazy2);
    }
}