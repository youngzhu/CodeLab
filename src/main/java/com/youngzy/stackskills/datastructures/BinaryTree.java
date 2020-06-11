package com.youngzy.stackskills.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class BinaryTree {

    public void breadthFirst(TreeNode root) throws CircularQueue.QueueOverflowException, CircularQueue.QueueUnderflowException {
        if (root == null) {
            return;
        }

        CircularQueue<TreeNode> queue = new CircularQueue<>(TreeNode.class);
        queue.enqueue(root);

        while (! queue.isEmpty()) {
            TreeNode node = queue.dequeue();

            // visit node
            // ...

            if (node.leftChild != null) {
                queue.enqueue(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.enqueue(node.rightChild);
            }
        }
    }

    /**
     * Pre-Order: root - left - right
     * @param root
     */
    public void depthFirstPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // visit node
        //...

        depthFirstPreOrder(root.leftChild);
        depthFirstPreOrder(root.rightChild);
    }

    /**
     * In-Order: left - root - right
     * @param root
     */
    public void depthFirstInOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        depthFirstPreOrder(root.leftChild);
        // visit node
        //...
        depthFirstPreOrder(root.rightChild);
    }

    /**
     * Post-Order: left - right - root
     * @param root
     */
    public void depthFirstPostOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        depthFirstPreOrder(root.leftChild);
        depthFirstPreOrder(root.rightChild);
        // visit node
        //...
    }

    class TreeNode<T> {
        T data;
        TreeNode<T> leftChild;
        TreeNode<T> rightChild;
    }
}


