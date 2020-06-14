package com.youngzy.stackskills.datastructures.binarytree;

import com.youngzy.stackskills.datastructures.binarytree.BinaryTree;

public class BinarySearchTree {
    public TreeNode<Integer> insert(TreeNode<Integer> root, TreeNode<Integer> newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.data <= root.data) {
            root.leftChild = insert(root.leftChild, newNode);
        } else {
            root.rightChild = insert(root.rightChild, newNode);
        }

        return root;
    }

    public TreeNode<Integer> lookup(TreeNode<Integer> root, int data) {
        if (root == null) {
            return null;
        }

        if (root.data == data) {
            return root;
        }

        if (data <= root.data) {
            return lookup(root.leftChild, data);
        } else {
            return lookup(root.rightChild, data);
        }
    }

    public int minimumValue(TreeNode<Integer> root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        if (root.leftChild == null) {
            return root.data;
        }

        return minimumValue(root.leftChild);
    }

    public int maxDepth(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        if (root.leftChild == null && root.rightChild == null) {
            return 0;
        }

        int leftMaxDepth = 1 + maxDepth(root.leftChild);
        int rightMaxDepth = 1 + maxDepth(root.rightChild);

        return Math.max(leftMaxDepth, rightMaxDepth);
    }

    /**
     * 左右互换
     *
     * @param root
     */
    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }

        mirror(root.leftChild);
        mirror(root.rightChild);

        // swap
        TreeNode tmp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = tmp;
    }

    /**
     * 给定节点个数，共有多少总不同的树
     */
    public int countTree(int numOfNodes) {
        if (numOfNodes <= 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 1; i <= numOfNodes; i++) {
            int countLeftTrees = countTree(numOfNodes - 1);
            int countRightTrees = countTree(numOfNodes - i);

            sum += countLeftTrees * countRightTrees;
        }

        return sum;
    }

    public void printRange(TreeNode<Integer> root, int low, int high) {
        if (root == null) {
            return;
        }
        if (low <= root.data) {
            printRange(root.leftChild, low, high);
        }
        if (low <= root.data && root.data <= high) {
            System.out.println(root.data);
        }
        if (high > root.data) {
            printRange(root.rightChild, low, high);
        }

    }

    public boolean isBinarySearchTree(TreeNode<Integer> root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data <= min || root.data > max) {
            return false;
        }

        return isBinarySearchTree(root.leftChild, min, root.data)
                && isBinarySearchTree(root.rightChild, root.data, max);
    }
}
