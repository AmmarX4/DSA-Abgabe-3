package de.unistuttgart.dsass2024.ex03.p5;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTreeIterable<T> {

    private volatile IBinaryTreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public void insert(T t) {
        this.root = this.insert(this.root, t);
    }

    private IBinaryTreeNode<T> insert(IBinaryTreeNode<T> node, T t) {
        if (node == null) {
            IBinaryTreeNode<T> newNode = new BinaryTreeNode<>();
            newNode.setValue(t);
            return newNode;
        }
        if (t.compareTo(node.getValue()) < 0) {
            node.setLeftChild(this.insert(node.getLeftChild(), t));
        } else if (t.compareTo(node.getValue()) > 0) {
            node.setRightChild(this.insert(node.getRightChild(), t));
        }
        return node;
    }

    @Override
    public IBinaryTreeNode<T> getRootNode() {
        return this.root;
    }

    @Override
    public boolean isFull() {
        return false;
    }


    @Override
    public Iterator<T> iterator(TreeTraversalType traversalType) {

        return switch (traversalType) {
            case INORDER: new InOrderIterator();
            case PREORDER: new PreOrderIterator();
            case POSTORDER: new PostOrderIterator();
            case LEVELORDER: new LevelOrderIterator();

        };

    }

    private class InOrderIterator implements Iterator<T> {

        private final Stack<IBinaryTreeNode<T>> stack = new Stack<>();

        // Go to the deepest leftChild from the start
        public InOrderIterator() {
            IBinaryTreeNode<T> currentNode = root;
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
        }


        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("this tree has no more Elements");
            }

            IBinaryTreeNode<T> node = stack.pop();
            T nodeValue = node.getValue();
            IBinaryTreeNode<T> rightChild = node.getRightChild();

            while (rightChild != null) {
                stack.push(node.getRightChild());
                rightChild = rightChild.getLeftChild();

            }

            return nodeValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this method is not supported !");
        }

    }


    private class PreOrderIterator implements Iterator<T> {

        private Stack<IBinaryTreeNode<T>> stack = new Stack<>();

        public PreOrderIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {

            IBinaryTreeNode<T> node = stack.pop();
            T nodeValue = node.getValue();

            if (node.getRightChild() != null) {
                stack.push(node.getRightChild());
            }
            if (node.getLeftChild() != null) {
                stack.push(node.getLeftChild());
            }

            return nodeValue;
        }

        public void remove() {
            throw new UnsupportedOperationException("this method is not supported !");
        }
    }


    private class PostOrderIterator implements Iterator<T> {

        private Stack<IBinaryTreeNode<T>> stack = new Stack<>();

        public PostOrderIterator() {
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {}

        public void remove() {
            throw new UnsupportedOperationException("this method is not supported !");
        }
    }
}