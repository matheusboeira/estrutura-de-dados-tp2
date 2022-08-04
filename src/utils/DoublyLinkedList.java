package utils;

import java.util.Iterator;

import base.Vehicle;
import utils.interfaces.List;

public class DoublyLinkedList implements List<Vehicle>, Iterable<Vehicle> {
    private int size;
    private Node<Vehicle> head;
    private Node<Vehicle> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds an element in the end of the list.
     * 
     * @param element the element to be added
     */
    public void add(Vehicle element) {
        addEnd(element);
    }

    /**
     * Adds an element in the start (head) of the list.
     * 
     * @param element the element to be added
     */
    @Override
    public void addStart(Vehicle element) {
        Node<Vehicle> node = new Node<>(element);

        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.setNext(this.head);
            this.head.setPrevious(node);
            this.head = node;
        }
        size++;
    }

    /**
     * Adds an element in the end (tail) of the list.
     * 
     * @param element the element to be added
     */
    @Override
    public void addEnd(Vehicle element) {
        Node<Vehicle> node = new Node<>(element);

        if (this.tail == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.setPrevious(this.tail);
            this.tail.setNext(node);
            this.tail = node;
        }
        size++;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true|false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remove and element from the list.
     * 
     * @param element the element to remove
     */
    @Override
    public boolean remove(Vehicle element) {
        Node<Vehicle> search = head;

        while (search != null && search.getElement() != element) {
            search = search.getNext();
        }
        if (search == null) return false;

        if (search.getPrevious() == null) {
            head = search.getNext();
        } else if (search.getNext() == null) {
            search.getPrevious().setNext(null);
            tail = search.getPrevious();
        } else {
            search.getPrevious().setNext(search.getNext());
            search.getNext().setPrevious(search.getPrevious());
        }
        size--;
        return true;
    }

    /**
     * Get the size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Get the first element of the list.
     * @return the first element (head)
     */
    public Vehicle getFirstElement() {
        return head.getElement();
    }

    /**
     * Get the last element of the list.
     * @return the last element (tail)
     */
    public Vehicle getLastElement() {
        return tail.getElement();
    }

    /**
     * Prints elements in descending order.
     */
    public String endToStart() {
        var builder = new StringBuilder();
        Node<Vehicle> node = this.tail;

        while (node != null) {
            builder.append(node.getElement());
            node = node.getPrevious();
        }
        return builder.toString();
    }

    /**
     * Get the last node of the list.
     * @return
     */
    public Node<Vehicle> getLastNode() {
        return tail;
    }

    /**
     * Get the first node of the list.
     */
    public Node<Vehicle> getFirstNode() {
        return head;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        Node<Vehicle> node = this.head;
        Vehicle vehicle = null;

        while (node != null) {
            if (node.getNext() != null) {
                vehicle = node.getElement();
                builder.append("(Chassi: ").append(vehicle.getChassi());
                builder.append(", Marca: ").append(vehicle.getMarca()).append("), ");
            } else {
                vehicle = node.getElement();
                builder.append("(Chassi: ").append(vehicle.getChassi());
                builder.append(", Marca: ").append(vehicle.getMarca()).append(")");
            }
            node = node.getNext();
        }
        return builder.append("]").toString();
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return new Iterator<Vehicle>() {
            Node<Vehicle> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Vehicle next() {
                if (hasNext()) {
                    Vehicle data = current.getElement();
                    current = current.getNext();
                    return data;
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not implemented.");
            }
        };
    }
}
