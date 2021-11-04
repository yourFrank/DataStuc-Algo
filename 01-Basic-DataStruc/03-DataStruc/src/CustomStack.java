import java.util.Arrays;

//LeetCode 1381 设计支持增量操作的栈
//实现增量时因为我们要操作栈底的k个元素，因此我们要将他们暴露出来，因此通过数组索引的方式
//方法一:
class CustomStack {
    private int[] stack;
    private int top;
    private int size;

    public CustomStack(int maxSize) {
        stack = new int[maxSize];
        top = -1;
        size=0;
    }

    public void push(int x) {
        if (size==stack.length){
            return;
        }else {
            stack[++top]=x;
            size++;
        }
    }

    public int pop() {
        if (size>0){
            int ret=stack[top];
            top--;
            size--;
            return ret;
        }
        return -1;

    }

    //复杂度为O(n)
    public void increment(int k, int val) {
//        if (k>size){
//            k=size;
//        }
        k=Math.min(k,size); //此时可以选取k和size最小值的方式避免上面if
        for (int i=0;i<k;i++){
            stack[i]+=val;
        }

    }

}

//    在方法一中，只剩下 inc 操作的时间复杂度不为 O(1)，因此可以尝试对该操作进行优化。
//    因为只有在 pop 操作时，我们才需要知道栈顶元素的具体值，在其余的情况下，我们只要存储每个元素的增量就行了。
//   我们用一个辅助数组 add 记录每次 inc 操作。具体地，如果 inc 操作是将栈底的 k 个元素（将 k 与栈中元素个数取较小值）增加 val，那么我们将 add[k - 1] 增加 val。
//    因此在遇到 pop 操作时，我们返回栈顶元素的初始值加上增量 add[top]。在这之后，我们将增量向栈底进行传递，累加至 add[top - 1] 处，这样 inc 操作的时间复杂度也减少至 O(1) 了

class CustomStack2 {
    int[] stack;
    int[] add;
    int top;

    public CustomStack2(int maxSize) {
        stack = new int[maxSize];
        add = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (top!=stack.length-1){ //判断栈是否满了
            stack[++top]=x;
        }
    }

    public int pop() {

        if (top!=-1){ //判断栈是否为空
            int ret=stack[top]+add[top];
            if (top!=0){ //如果不等于0的话
                //k出栈后此时设置k-1的增量。（因为下一个出栈的元素是k-1）。将（k之前）的增量设置成k的增量，注意一定要加上原先K-1这个位置的。k-1可能有自己的增量
                add[top-1]+=add[top];
            }
            add[top]=0; //增量使用完后出栈并且当前清零
            top--;
            return ret;
        }
        return -1;
    }

    public void increment(int k, int val) {
        k=Math.min(k-1,top);  //将k转换成对应的索引
        if (k==-1){ //如果没有元素return
            return;
        }
        add[k]+=val;  //我们只需要先保存k索引元素的增量，k之下的元素增量可以等k这个节点出栈后设置k-1的增量

    }
}