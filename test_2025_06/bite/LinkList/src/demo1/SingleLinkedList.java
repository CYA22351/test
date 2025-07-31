package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/12 22:47
 * @description：
 * @modified By：
 * @version:
 */
public class SingleLinkedList implements Link{
    public ListNode head;
    static  class  ListNode{
          public int val;
          public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public void createList(){
        ListNode node1=new ListNode(12);
        ListNode node2=new ListNode(23);
        ListNode node3=new ListNode(34);
        ListNode node4=new ListNode(45);
        ListNode node5=new ListNode(56);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        this.head=node1;
    }

    @Override
    public void addFirst(int data) {
ListNode node=new ListNode(data);
node.next=head;
head=node;
    }

    @Override
    public void addLast(int data) {
ListNode node=new ListNode(data);
ListNode cur=head;

if (head==null){
    head=node;
    return;
}
while (cur.next!=null){
 cur=cur.next;

}
cur.next=node;
    }
public void checkindex(int index){
        if (index<0||index>size()){
            throw new indexIllegal("index位置不合法");
        }
}
    @Override
    public void addIndex(int index, int data) {

         checkindex(index);
         int len=size();
         if (index==0){
             addFirst(data);
             return;
         }
         if (index==len){
             addLast(data);
             return;
         }

         ListNode node=new ListNode(data);

         ListNode cur=head;
         while (index-1!=0){
             cur=cur.next;
             index--;

         }
         node.next=cur.next;
         cur.next=node;


    }

    @Override
    public boolean contains(int key) {
        ListNode cur=head;
        while (cur!=null){
            if (cur.val==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    @Override
    public void remove(int key) {
        if (head==null){
            return;
        }
        if (head.val==key){
            head=head.next;
            return;
        }
    ListNode cur=findNodeofkey(key);
        if (cur==null){
            return;
        }
        cur.next=cur.next.next;
    }
    public ListNode findNodeofkey(int key){
        ListNode cur=head;
        while (cur.next!=null){
            if (cur.next.val==key){
                return cur;
            }
        }
        return null;
    }

    @Override
    public void removeAllKey(int key) {
if (head==null){
    return;
}
ListNode pre=head;
ListNode cur=head.next;
while (cur!=null){
    if (cur.val==key){
        pre.next=cur.next;
        cur=cur.next;
    }else {
        pre=cur;
        cur=cur.next;
    }
}
if (head.val==key){
    head=head.next;
}
    }

    @Override
    public int size() {
        int len=0;
        ListNode cur=head;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        return len;
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public void display() {
        ListNode cur=head;
while (cur!=null){
    System.out.print(cur.val+" ");
    cur=cur.next;
}
        System.out.println();
    }
}