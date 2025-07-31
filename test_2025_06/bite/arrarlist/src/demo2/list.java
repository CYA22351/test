package demo2;

public interface list {
    public void add(int data);
    public boolean ifFull();
    public void add(int pos,int data);
    public void display();
    public boolean contains(int tofind);
    public int indexof(int toFind);
    public int get(int pos);
    public void set(int pos,int value);
    public void remove(int toRemovbe);
    public void clear();



}
