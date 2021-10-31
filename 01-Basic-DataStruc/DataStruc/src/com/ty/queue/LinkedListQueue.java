package com.ty.queue;

/**
 * 基于链表实现的队列，因为在表头插入删除节点比较方便，要删除队尾元素时却要找到tail的上一个，还是O(n)的，队首需要出队（删除），因此表头当做队首，同时使用tail指向队尾，方便入队
 * 此时我们只是需要对两端的元素进行操作，不需要操作中间的元素，因此我们不需要dummyHead来找上一个节点。 此时我们需要链表为空时注意head和tail指向同一个节点的情况
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
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

    private Node head, tail;  //此时需要有一个指向tail节点的指针
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void enqueue(E e){
        if(tail == null){ //如果tail等于null的话，head也等于null，因为只要有一个元素tail就肯定有指向的元素
            tail = new Node(e);
            head = tail; //此时让head也等于tail
        }
        else{    //有元素的话，直接入队尾
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node retNode = head;
        head = head.next; //出队后头节点后移，有可能变为null
        retNode.next = null;
        if(head == null)  //如果变为null了，说明原先只有一个元素，移除完之后链表为空，因此tail也指向null
            tail = null;
        size --;
        return retNode.e;
    }

    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
