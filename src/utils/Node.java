package utils;

public class Node<T> {
    private T element;
    private Node<T> previous;
    private Node<T> next;
    private int index;

    public Node(T element) {
        this.element = element;
        this.previous = null;
        this.next = null;
    }

    public Node(T element, int index) {
        this.element = element;
        this.previous = null;
        this.next = null;
        this.index = index;
    }

    public Node(T element, Node<T> previous, Node<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
