package com.ty.priorityqueue;

import com.ty.heap.Heap;

//基于堆实现优先队列
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private Heap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new Heap<>();
    }

    @Override
    public int getSize(){
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty(){
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront(){
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e){
        maxHeap.add(e);
    }

    @Override
    public E dequeue(){
        return maxHeap.extractMax();
    }
}
