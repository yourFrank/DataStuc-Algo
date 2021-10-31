package com.ty.LinkedList;

public class LinkedList<E> {

    private class Node{   //设定为内部类，只有在当前类使用这个node类
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head;  //头节点
    private int size;  // 链表大小

    public LinkedList(){
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        head = new Node(e, head); //也可以一句话
        size ++;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        if (index==0){  //如果要在表头加入
            addFirst(e);
        }else {
            Node prev=head;
            for (int i=0;i<index-1;i++){ //刚开始i=0在头节点，每次head走到下一个节点，经过index-1次循环来到index-1这个节点
                prev=prev.next;
            }
//            Node node=new Node(e);  //注意这里顺序
//            node.next=prev.next;
//            prev.next=node;
              prev.next=new Node(e,prev.next); //一句话
        }

    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }
}
