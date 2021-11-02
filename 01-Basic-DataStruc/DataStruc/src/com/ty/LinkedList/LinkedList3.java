package com.ty.LinkedList;


import javafx.util.Pair;

//链表递归实现
public class LinkedList3<E> {

    private Node head;
    private int size;  // 链表大小

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        head = add(head, index, e);
        size++;
    }

    //链表的递归添加
    public Node add(Node cur, int index, E e) {
        if (index == 0) {
            Node node = new Node(e, cur);
            return node;
        }

        cur.next = add(cur.next, index - 1, e);
        return cur;
    }

    // 在链表头添加新的元素e,此时可以直接调用add(0，e )方法即可
    public void addFirst(E e) {
        add(0, e);
        size++;
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    //获取第index位置的元素
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        return get(index, head);
    }

    public E get(int index, Node head) {
        if (index == 0) {
            return head.e;
        }
        return get(index - 1, head.next);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
        set(head, index, e);
    }

    private void set(Node head, int index, E e) {
        if (index == 0) {
            head.e = e;
            return;
        }
        set(head, index - 1, e);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        return contains(head, e);
    }

    // 在以node为头结点的链表中，查找是否存在元素e，递归算法
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (node.e.equals(e))
            return true;
        return contains(node.next, e);
    }
//
//    public E remove(int index) {
//        if (index < 0 || index >= size)
//            throw new IllegalArgumentException("Set failed. Illegal index.");
//        return remove(head, index - 1);
//    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Remove failed. Index is illegal.");
        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }
    // 从以node为头结点的链表中，删除第index位置的元素，递归算法
    // 返回值包含两个元素，删除后的链表头结点和删除的值，返回头节点是因为当删除的是头节点的时候，头节点要变成下一个节点
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) return new Pair<>(node.next, node.e);
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    private class Node {   //设定为内部类，只有在当前类使用这个node类
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


}
