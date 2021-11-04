package com.ty.queue;

//双端队列，在循环队列的基础上还可以从队首和队尾添加和删除元素

public class Deque <E> implements Queue<E> {

    private E[] data;
    private int front, tail; //队首和队尾的指针 ,队尾默认指向一个空的元素
    private int size;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity + 1]; //指定容量,此时要+1，因为我们要有意识的浪费一个元素作为满的判断
    }

    public Deque() {
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

    //在队首添加元素
    public void addFront(E e){
        if (size==getCapacity()){
            resize(getCapacity()*2);
        }
        //我们要确定这个新元素索引的位置
        front=front==0?data.length-1:front-1;
        data[front]=e;
        front++;
    }
    public E removeLast(){
        if(isEmpty()) throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        tail=tail == 0 ? data.length - 1 : tail - 1; //对tail作判断
        size --;
        data[tail]=null;
        if(getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);
        return data[tail];
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
