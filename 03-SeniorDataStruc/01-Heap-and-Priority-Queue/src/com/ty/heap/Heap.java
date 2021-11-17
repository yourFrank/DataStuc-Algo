package com.ty.heap;

import java.util.Comparator;

//定义通用的heap，可以根据自定义的comparator来实现最大堆或者最小堆以及相应的比较方法
public class Heap<E extends Comparable<E>> {

    private Array<E> data;
    private final Comparator<? super E> comparator; //可以自定义比较的接口，实现最大堆和最小堆的实现

    public Heap(int capacity) {
        data = new Array<>(capacity);
        comparator = null;
    }

    public Heap() {
        data = new Array<>();
        comparator = null;
    }

    public Heap(Comparator<? super E> comparator) {
        data = new Array<>();
        this.comparator = comparator;
    }


    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {   //插入的元素需要和父亲节点判断，如果父亲节点小于新 增加的节点要交换这两个值。然后继续和他的父亲节点判断
        if (comparator != null) {
            siftUpUsingComparator(k);
        } else {
            siftUpComparable(k);

        }

    }

    //使用类自己的Comparable进行比较
    private void siftUpComparable(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k); //k=父亲元素的索引，继续和上面的进行判断
        }
    }

    //使用自定义传入的Comparator接口比较
    private void siftUpUsingComparator(int k) {
        while (k > 0 && comparator.compare(data.get(k), data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k); //k=父亲元素的索引，继续和上面的进行判断
        }
    }

    //取出堆中的最大元素
    public E extractMax() {
        E ret = findMax(); //看一下堆首元素，里面已经做了非空校验
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    private void siftDown(int k) {
        if (comparator != null) {
            siftDownUsingComparator(k);
        } else {
            siftDownComparable(k);

        }

    }

    //使用自定义的comparator接口
    private void siftDownUsingComparator(int k) {
        while (leftChild(k) < data.getSize() - 1) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && comparator.compare(data.get(rightChild(k)),data.get(leftChild(k))) > 0) {
                j = j + 1; //此时找到j指向最大孩子节点的索引
            }
            if (comparator.compare(data.get(k),data.get(j)) >= 0) //如果比k比孩子节点都大了，循环就可以停止了
                break;

            data.swap(k, j);
            k = j;

        }
    }
    //使用类自己的Comparable进行比较
    private void siftDownComparable(int k) {
        while (leftChild(k) < data.getSize() - 1) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(rightChild(k)).compareTo(data.get(leftChild(k))) > 0) {
                j = j + 1; //此时找到j指向最大孩子节点的索引
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) //如果比k比孩子节点都大了，循环就可以停止了
                break;

            data.swap(k, j);
            k = j;

        }
    }

    //返回堆中最大元素，并且替换为新的元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
