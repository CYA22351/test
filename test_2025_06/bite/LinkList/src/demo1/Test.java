package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/12 22:52
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
    public static void main(String[] args) {
    SingleLinkedList singleLinkedList=new SingleLinkedList();
//    singleLinkedList.createList();
//    singleLinkedList.display();
//        System.out.println(singleLinkedList.size());
        singleLinkedList.addLast(1);
        singleLinkedList.addLast(2);
        singleLinkedList.addLast(3);
        singleLinkedList.addLast(4);
        singleLinkedList.addLast(5);
        singleLinkedList.addLast(99);
        singleLinkedList.display();
        singleLinkedList.addIndex(-1,1);
    }
}