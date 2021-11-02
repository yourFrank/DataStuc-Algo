import java.util.Deque;
import java.util.LinkedList;

//LeetCode 155 最小栈
//选取栈中的最小值，此时我们需要保存这个最小值。并且当栈中最小值出栈时我们要找出新的最小值。
//此时我们可以选择一个辅助栈，当每个元素入栈的时候我们都将此刻的最小值保存到辅助栈中，此时辅助栈就保存了所有时刻的最小值，当元素出栈时，辅助栈也出栈一个
class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}