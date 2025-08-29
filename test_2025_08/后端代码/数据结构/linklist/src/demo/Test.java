package demo;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/29 11:33
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        MySingleList mySingleList=new MySingleList();
        mySingleList.creatNode();
        mySingleList.show();
        System.out.println(mySingleList.size());
        System.out.println(mySingleList.contains(21));
        mySingleList.addIndex(2,33);
        mySingleList.show();

    }
}