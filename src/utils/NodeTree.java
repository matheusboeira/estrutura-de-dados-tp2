package utils;

public class NodeTree<Type> {
    private Type value;
    private NodeTree<Type> left;
    private NodeTree<Type> right;
    private NodeTree<Type> parent;

    public NodeTree(Type value) {
        this.value = value;
    }

    public Type getValue() {
        return value;
    }

    public void setTree(Type value) {
        this.value = value;
    }

    public NodeTree<Type> getLeft() {
        return left;
    }

    public void setLeft(NodeTree<Type> left) {
        this.left = left;
    }

    public NodeTree<Type> getRight() {
        return right;
    }

    public void setRight(NodeTree<Type> right) {
        this.right = right;
    }

    public NodeTree<Type> getParent() {
        return parent;
    }

    public void setParent(NodeTree<Type> parent) {
        this.parent = parent;
    }
}
