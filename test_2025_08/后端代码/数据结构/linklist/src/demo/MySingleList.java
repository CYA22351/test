package demo;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/29 10:55
 * @description：
 * @modified By：
 * @version:
 */
public class MySingleList {
    static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode head;
    public void creatNode(){
        ListNode node1=new ListNode(11);

        ListNode node2=new ListNode(21);
        ListNode node3=new ListNode(13);
        ListNode node4=new ListNode(32);
        ListNode node5=new ListNode(43);
        ListNode node6=new ListNode(44);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
       this.head=node1;

    }
    public void addFirst(int data){
        ListNode node=new ListNode(data);
        node.next=head;
        head=node;
    }
    //尾插法
    public void addLast(int data){
        ListNode node=new ListNode(data);
        if (head==null){
            head=node;
            return;
        }
        ListNode cur=head;

        while (cur.next!=null){
            cur=cur.next;
        }
        cur.next=node;
    }
    //任意位置插⼊,第⼀个数据节点为0号下标
    public void addIndex(int index,int data){
        if (index<0||index>size()){
            throw new IndexIlleaglException("插入位置不合法！！！");
        }
        if (index==0){
            addFirst(data);
            return;
        }
        if (index==size()){
            addLast(data);
            return;
        }
        ListNode node=new ListNode(data);
       int count=0;
        ListNode cur=head;
        while (count!=index-1){
            cur=cur.next;
            count++;
        }
        node.next=cur.next;
        cur.next=node;



    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key){
        ListNode cur=head;
        while (cur!=null){
            if (cur.val==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }
    //删除第⼀次出现关键字为key的节点
    public void remove(int key){
    }
    //删除所有值为key的节点
    public void removeAllKey(int key){
    }
    //得到单链表的⻓度
    public int size(){
        int count=0;
        ListNode cur=head;
        while (cur!=null){
            cur=cur.next;
            count++;
        }
        return count;
    }
    public void clear() {
    }
    public void display() {}
    public void show(){
        ListNode cur=head;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.next;
        }
        System.out.println();
    }
}