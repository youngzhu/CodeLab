package com.youngzy.stackskills.datastructures.queue;

import java.util.Stack;

public class QueueBuiltWithTwoStacks<T> {
    private Stack<T> forwardStack;
    private Stack<T> reverseStack;

    public QueueBuiltWithTwoStacks() {
        forwardStack = new Stack<>();
        reverseStack = new Stack<>();
    }

//    public boolean isFull() {
//        return forwardStack
//    }

    public boolean isEmpty() {
        return forwardStack.isEmpty() && reverseStack.isEmpty();
    }

    public void enqueue(T data) {
        if (forwardStack.isEmpty()) {
            while (! reverseStack.isEmpty()) {
                forwardStack.push(reverseStack.pop());
            }
        }

        forwardStack.push(data);
    }

    public T dequeue() {
        if (! isEmpty()) {

            if (reverseStack.isEmpty()) {
                while (! forwardStack.isEmpty()) {
                    reverseStack.push(forwardStack.pop());
                }
            }

            reverseStack.pop();
        }

        return null;
    }
}
