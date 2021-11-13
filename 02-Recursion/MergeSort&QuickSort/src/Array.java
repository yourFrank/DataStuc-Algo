import java.util.Comparator;

class Array<E> {

    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array() {
        this(10);
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

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        data[size] = null; // loitering objects != memory leak

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
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

    public void swap(int i, int j) {

        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}


//完全二叉树：按照顺序放置节点的树 。因为是按照顺序存放的，我们可以使用数组顺序存储，每个节点的父节点和孩子节点所对应的索引是能确定的
//堆结构满足完全二叉树的性质，最大堆：每个父亲节点的值都大于他们孩子节点的值。（但不代表上一层的值都大于下一层的值）;相似的最小堆就是小于
//如果数组中索引从1开始存 : 孩子相应的父亲节点索引就是 i/2 ，左孩子节点2*i，右孩子节点就是2*i+1； 这样0这个索引的位置就需要空出来(浪费了一个位置)
//如果数组从索引0开始存: 孩子相应的父亲节点就是(i-1)/2 ,左孩子节点就是2*i+1 ，右孩子就是2*i+2
class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;
    private final Comparator<? super E> comparator; //可以自定义比较的接口，实现最大堆和最小堆的实现

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
        comparator = null;
    }

    public MaxHeap() {
        data = new Array<>();
        comparator = null;
    }

    public MaxHeap(Comparator<? super E> comparator) {
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

//最小堆， 堆首的元素小于左右两个孩子节点
class MinHeap<E extends Comparable<E>> {

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





interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}

 class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
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
