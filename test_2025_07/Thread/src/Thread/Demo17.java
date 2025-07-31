package Thread;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/8 15:39
 * @description：
 * @modified By：
 * @version:
 */
class SingletonLazy{
    private static volatile SingletonLazy instance=null;
    private static Object object=new Object();
    public static SingletonLazy getInstance(){
        if (instance==null)
        {
            synchronized (object){
                if (instance==null){
                    instance=new SingletonLazy();
                }
            }
        }
        return instance;
    }
    private SingletonLazy(){

    }
}
public class Demo17 {
    public static void main(String[] args) {
        SingletonLazy singletonLazy1=SingletonLazy.getInstance();
        SingletonLazy singletonLazy2=SingletonLazy.getInstance();
        System.out.println(singletonLazy1==singletonLazy2);

    }
}