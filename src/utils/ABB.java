package utils;

import base.Vehicle;

public class ABB {
    private NodeTree<Vehicle> root;
    private static int howManyFords;

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
        return search(this.root, element);
    }

    public NodeTree<Vehicle> search(Integer key) {
        return search(this.root, key);
    }

    private NodeTree<Vehicle> search(NodeTree<Vehicle> node, Integer key) {
        if (node == null) 
            return null;

        if (key == node.getValue().getChassi()) {
            return node;
        }
        if (key < node.getValue().getChassi()) {
            return search(node.getLeft(), key);
        }
        return search(node.getRight(), key);
    }

    private NodeTree<Vehicle> search(NodeTree<Vehicle> node, Vehicle element) {
        if (node == null) 
            return null;

        if (element.getChassi() == node.getValue().getChassi()) {
            return node;
        }
        if (element.compareTo(node.getValue()) < 0) {
            return search(node.getLeft(), element);
        }
        return search(node.getRight(), element);
    }

    public boolean remove(Vehicle value) {
        NodeTree<Vehicle> current = this.root;
        NodeTree<Vehicle> currentParent = null;

        while(current != null) {
            if (current.getValue().equals(value)) {
                break;                
            } else if (value.compareTo(current.getValue()) == -1) { 
                /**
                 * Valor procurado é menor que o atual.
                 */
                currentParent = current;
                current = current.getLeft();
            } else {
                currentParent = current;
                current = current.getRight();
            }
        }

        /**
         * Verifica se existe o elemento.
         */
        if (current != null) {
            if (current.getRight() != null){
                NodeTree<Vehicle> substitute = current.getRight();
                NodeTree<Vehicle> parentSubstitute = current;

                while(substitute.getLeft() != null){
                    parentSubstitute = substitute;
                    substitute = substitute.getLeft();
                }
                substitute.setLeft(current.getLeft());

                if (currentParent != null) {
                    if (current.getValue().compareTo(currentParent.getValue()) == -1) {
                        currentParent.setLeft(substitute);
                    }else {
                        currentParent.setRight(substitute);
                    }
                /**
                 * Se não tem pai atual, então é a raiz.
                 */
                } else {
                    this.root = substitute;
                    parentSubstitute.setLeft(null);
                    this.root.setRight(parentSubstitute);
                    this.root.setLeft(current.getLeft());
                }
                
                /**
                 * Removeu o elemento da árvore.
                 */
                if (substitute.getValue().compareTo(parentSubstitute.getValue()) == -1) {
                    parentSubstitute.setLeft(null);
                } else {
                    parentSubstitute.setRight(null);
                }
            } else if (current.getLeft() != null) {
                /**
                 * Tem filho apenas à esquerda.
                 */
                NodeTree<Vehicle> substitute = current.getLeft();
                NodeTree<Vehicle> parentSubstitute = current;

                while(substitute.getRight() != null) {
                    parentSubstitute = substitute;
                    substitute = substitute.getRight();
                }

                if (currentParent != null) {
                    if (current.getValue().compareTo(currentParent.getValue()) == -1) { //current < currentParent
                        currentParent.setLeft(substitute);
                    } else {
                        currentParent.setRight(substitute);
                    }
                } else {
                    this.root = substitute;
                }
                
                /**
                 * Removeu o elemento da árvore.
                 */
                if (substitute.getValue().compareTo(parentSubstitute.getValue()) == -1) {
                    parentSubstitute.setLeft(null);
                } else {
                    parentSubstitute.setRight(null);
                }
            } else {
                /**
                 * Não tem filho.
                 */
                if (currentParent != null) {
                    if (current.getValue().compareTo(currentParent.getValue()) == -1) { 
                        currentParent.setLeft(null);
                    } else {
                        currentParent.setRight(null);
                    }
                } else {
                    /**
                     * É a raíz.
                     */
                    this.root = null;
                }
            }
            return true;
        } else {
            return false;
        }        
    }

    public int getLowerValue(NodeTree<Vehicle> node) {
        int lower = root.getValue().getChassi();

        while (node.getLeft() != null) {
            lower = node.getLeft().getValue().getChassi();
            node = node.getLeft();
        }
        return lower;
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

    public int findFord() {
        howManyFords = 0;
        findFord(this.root);
        return howManyFords;
    }
    
    private void findFord(NodeTree<Vehicle> node) {
        if (node != null) {
            findFord(node.getLeft());
            if (node.getValue().isMarcaFord()) {
                howManyFords++;
            }
            findFord(node.getRight());
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
