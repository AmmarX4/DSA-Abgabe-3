package de.unistuttgart.dsass2024.ex03.p5;

public class BinaryTreeNode<T extends Comparable<T>> implements IBinaryTreeNode<T> {
private IBinaryTreeNode<T> left;
private IBinaryTreeNode<T> right;
private T val;

    public BinaryTreeNode() {}


    @Override
    public void setValue(T val) {
        this.val = val;
    }

    @Override
    public T getValue() {
        return val;
    }

    @Override
    public void setLeftChild(IBinaryTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public IBinaryTreeNode<T> getLeftChild() {
        return left;
    }

    @Override
    public void setRightChild(IBinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public IBinaryTreeNode<T> getRightChild() {
        return right;
    }
}