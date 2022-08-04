package utils;

import base.Vehicle;

public class ABB {
    private NodeTree<Vehicle> root;

    public ABB() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Vehicle ...elements) {
        for (Vehicle element : elements) {
            add(element);
        }
    }

    public NodeTree<Vehicle> getRoot() {
        return root;
    }

    public NodeTree<Vehicle> search(Vehicle element) {
        NodeTree<Vehicle> aux = this.root;
    
        while (aux != null) {
            if (aux.getValue() == element) 
                return aux;
            
            if (element.compareTo(aux.getValue()) < 0) 
                aux = aux.getLeft();

            if (element.compareTo(aux.getValue()) > 0) 
                aux = aux.getRight();
        }
        return null;
    }

    public void add(Vehicle element) {
        if (isEmpty()) {
            root = new NodeTree<Vehicle>(element);
            return;
        }

        NodeTree<Vehicle> aux = root;

        while (aux != null) {
            if (element.compareTo(aux.getValue()) < 0) {
                if (aux.getLeft() == null) {
                    NodeTree<Vehicle> newNode = new NodeTree<>(element);
                    aux.setLeft(newNode);
                    aux.setParent(aux);
                    return;
                }
                aux = aux.getLeft();
            } else {
                if (aux.getRight() == null) {
                    NodeTree<Vehicle> newNode = new NodeTree<>(element);
                    aux.setRight(newNode);
                    aux.setParent(aux);
                    return;
                }
                aux = aux.getRight();
            }
        }
    }

    public String inOrder() {
        var builder = new StringBuilder();
        inOrder(this.root, builder);
        return builder.toString();
    }
    
    private void inOrder(NodeTree<Vehicle> node, StringBuilder builder) {
        if (node != null) {
            inOrder(node.getLeft(), builder);
            builder.append(node.getValue().getChassi()).append(", ");
            builder.append(node.getValue().getMarca()).append("\n");
            inOrder(node.getRight(), builder);
        }
    }

    public String toString() {
        return inOrder();
    }
}
