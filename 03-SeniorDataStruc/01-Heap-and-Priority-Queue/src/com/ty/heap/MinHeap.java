package com.ty.heap;

//最小堆， 堆首的元素小于左右两个孩子节点
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MinHeap() {
        data = new Array<>();
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

    private void siftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {//如果父亲元素大于刚插入的节点则要进行交换

            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //取出堆中的最小元素
    public E extractMin() {
        E ret = findMin(); //看一下堆首元素，里面已经做了非空校验
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    public E findMin() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize() - 1) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j = j + 1;
            }
            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }
            data.swap(j, k);
            k = j;
        }


    }

    //返回堆中最小元素，并且替换为新的元素e
    public E replace(E e) {
        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }


}
