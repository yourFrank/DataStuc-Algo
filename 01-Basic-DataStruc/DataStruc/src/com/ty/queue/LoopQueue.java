package com.ty.queue;

/** 单向队列存在的问题: 当第一个元素出列后，所有的后面元素都要向前移动一个O（n）的时间复杂度
 * 使用循环队列，通过队首和队尾的指针来确定数组的范围（这样就不用移除队首元素后，后面的元素再向前移动一位了），
 * 如果数组尾部满了会循环在头部添加，当tail+1=front就是队列满了，此时预留出一个元素作为判断，因为不想和判断为空的时候是一个条件
 * 循环队列的出队时间复杂度O（1）
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail; //队首和队尾的指针 ,队尾默认指向一个空的元素
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1]; //指定容量,此时要+1，因为我们要有意识的浪费一个元素作为满的判断
    }

    public LoopQueue() {
        data = (E[]) new Object[10]; //不指定默认创建10的数组
    }
    public int getCapacity(){
        return data.length-1; //有意识的浪费了一个空间
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) { //满了扩容
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail=(tail + 1) % data.length;
        size++;
    }

    private void resize(int capacity) {
        E[] newData=(E[]) new Object[capacity+1];
        for (int i=0;i<size;i++){  //注意这里
            newData[i]=data[(i+front)%data.length];
        }
        front=0;
        tail=front+size;
        data=newData;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("dequeue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length; //改变队首的位置
        size--;
        if (size==getCapacity()/4){ //缩容
            resize(data.length/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("dequeue is empty");
        }
        return data[front];
    }
    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
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
