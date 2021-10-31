package com.ty.LinkedList;

/**
 * 时间复杂度：添加头元素：O（1）,添加尾元素O(n),添加index位置的： 平均一下取中间的O（n/2）=》O（n） 和数组相反（数组删除头部为On，删除尾部为O1）
 *           删除头元素O(1), 删除尾部元素: O（n）,删除index元素O（n/2）=》O（n）  和数组相反
 *           修改操作：O（n） 查找操作：O（n）
 *           如果只对链表头进行操作添加、删除、修改，查，则都是O1的时间复杂度（这一切都归功于head指针的存在，来帮我们标记头在哪）。
 *             适合一端操作，此时我们可以用它来作为栈。同时将链表头部作为栈顶，只操作栈顶的元素
 *           相比较数组，因为数组对尾部操作都是01的时间复杂度，因此我们用数组来实现栈的时候都是将数组尾部当做栈顶
 *      然而当前这种链表只有头节点，不适合作为队列，因为每次都要找链表尾部链接，入队的操作都是O(n)
 * @param <E>
 */
public class LinkedList2<E> {

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

//    private Node head;  //头节点
    private int size;  // 链表大小
    private Node dummyHead; //虚拟头节点
    public LinkedList2(){
        dummyHead = new Node(null,null);
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



    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

//        加入虚拟头节点后可以省去判断是不是头节点的步骤
         Node prev=dummyHead;  //因为要找前一个节点，这个节点可能是第一个头节点，因此我们要从虚拟节点开始遍历
         for (int i=0;i<index;i++){ //因为多了一个虚拟节点，所以，此时循环index次找到index-1的节点
             prev=prev.next;
         }
         prev.next=new Node(e,prev.next);

    }
    // 在链表头添加新的元素e,此时可以直接调用add(0，e )方法即可
    public void addFirst(E e){
        add(0,e);
        size ++;
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;  //因为要获得当前节点，所以要从真正的节点开始遍历,注意区分上面的add操作找上一个节点是从dummy开始
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改链表的第index(0-based)个位置的元素为e
    // 在链表中不是一个常用的操作，练习用：）
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    //从链表中删除index位置的元素，返回删除的元素
    //不常用，练习用
    public E remove(int index){
        Node prev=dummyHead; //这里要找到它的上一个节点，因此prev=dummyhead
        for (int i=0;i<index;i++){
            prev=prev.next;
        }
        Node cur=prev.next;
        prev.next=cur.next;
        cur.next=null;
        size--;
        return cur.e;
    }
    //从链表删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }
    //从链表删除最后元素，返回删除的元素
    public E removeLast(){
        return remove(size);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for(Node cur = dummyHead.next ; cur != null ; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }

}
