package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/12 22:48
 * @description：
 * @modified By：
 * @version:
 */
public interface Link {
    public void addFirst(int data);
    //尾插法
    public void addLast(int data);
    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data);
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key);
    //删除第一次出现关键字为key的节点
    public void remove(int key);
    public void removeAllKey(int key);
    //得到单链表的长度
    public int size();
    public void clear();
    public void display();
}