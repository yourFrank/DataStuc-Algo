package com.ty.array;

/*
 *实现自己的动态数组，可以添加和删除任意元素
 * 这里提醒一下均摊时间复杂度:当我们增加一个元素的时候可能会发生扩容，操作，扩容操作的次数是n+1（n次赋值，加一次的添加元素操作）
 * 当我们执行n次添加操作会有一次的扩容操作，因此每n次添加操作会有2n+1次操作，平均下来每次添加操作会有2次，因此均摊下来也是O(1)的
 */
public class Array<E> {
    private E[] data;
    private int size;

    // 无参数的构造函数，默认数组的容量capacity=10
    public  Array() {
        this(10);
    }

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];//这里要注意，不能直接new泛型的数组，首先要new Object[]再转成泛型数组
        size = 0;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //在最后一个位置添加元素
    public void addLast(E ele) {
        if (size == data.length) { //如果等于数组长度扩容
            resize(data.length * 2);
        }
        data[size++] = ele;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if (size == data.length)
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    //删除索引为index的元素
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        E ret = data[index]; //保存要删除的元素
        for (int i = index; i+1 < size; i++) { //注意这里是小于size，因为数组一个size个元素，而不是小于length
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null; // loitering objects != memory leak
        if (size == data.length / 4 && data.length / 2 != 0) { //如果元素只有四分之一则缩容（此时要考虑1/0等于0时，就不缩容了，0没有意义）
            resize(data.length / 2);
        }
        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }


    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }
    //获取最后一个元素
    public E getLast(){
        return data[size-1];
    }

    //获取第一个元素
    public E getFirst(){
        return data[0];
    }

    public E get(int index){ //获取下标为index的元素
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        return data[index];
    }
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity]; //这里要注意，不能直接new泛型的数组，首先要new Object[]再转成泛型数组
        for (int i = 0; i < size; i++) { //注意这里循环是i<size
            newData[i] = data[i];
        }
        data = newData;

    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("com.ty.array.Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}
