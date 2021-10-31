package com.ty.queue;

import java.util.Stack;

/**
 *
 */
class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int front;

    public MyQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }


    public void push(int x) {
//第一种操作：将stack1中的移到2，再移到1  时间复杂度O（n）,队首元素在stack1的栈顶
//        if (stack1.isEmpty()){
//            stack1.push(x);
//        } else  {
//            while (!stack1.isEmpty()) {
//                stack2.push(stack1.pop());
//            }
//            stack1.push(x);
//            while (!stack2.isEmpty()) {
//                stack1.push(stack2.pop());
//            }
//        }
     //第二种: //队首元素直接入栈stack1
        if (stack1.isEmpty()){
            front=x;
        }
        stack1.push(x);

    }

    public int pop() {
        //如果stack2为空了，则将1中的元素pop移入2，此时stack1中的栈底（队首）元素在stack2的栈顶
        //该操作摊还时间复杂度为O（1），因为每执行n次入栈操作才会执行一复杂度高的出栈入栈操作，弹栈一次stack1,入栈一次stack2的操作是0 (2n)+1（stack2中弹栈的1次） (2n+1/n)为O(1)的时间复杂度
        if (stack2.isEmpty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
       return stack2.pop();
    }

    public int peek() {
        if (!stack2.empty()){
           return stack2.peek();
        }
        return front;
    }

    public boolean empty() {
        return stack1.isEmpty()&& stack2.empty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop());
         // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false


    }
}
