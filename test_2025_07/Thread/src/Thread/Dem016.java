package Thread;

class Singleton {
    private static Singleton instance=new Singleton();//静态成员的初始化，是在类加载的阶段触发的
    public static Singleton getInstance(){
        return instance;
    }
    private Singleton(){

    }
}
public class Dem016{
    public static void main(String[] args) {
        Singleton s1=Singleton.getInstance();
        Singleton s2=Singleton.getInstance();
        System.out.println(s1==s2);
    }
}