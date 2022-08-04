package utils;

public class Vector<T> {
    private static final int INITIAL_SIZE = 12;
    private static final int SIZE_TO_INCREMENT = 18;

    private Object[] objects;
    private int control;

    public Vector() {
        objects = new Object[INITIAL_SIZE];
    }

    public Vector(int length) {
        objects = new Object[length];
    }

    /**
     * Adds an element to this vector.
     * 
     * @param element an instance of Object
     * @return true|false
     */
    public boolean add(T element) {
        if (element != null) {
            incrementSize();
            objects[control++] = element;
            return true;
        }
        return false;
    }

    /**
     * This function adds an element to the array at the specified index
     * 
     * @param element The element to be added to the list.
     * @param index the index of the element to be added
     * @return the method returns a boolean value.
     */
    public boolean add(T element, int index) {
        if (element != null) {
            incrementSize();
            objects[index] = element;
            return true;
        }
        return false;
    }

    /**
     * Adds a list of elements to this vector.
     * 
     * @param elements an instance of Object
     * @return true|false
     */
    @SuppressWarnings("unchecked")
    public boolean add(T... elements) {
        for (T element : elements) {
            add(element);
        }
        return true;
    }

    /**
     * Get the element in the given index passed.
     * 
     * @param index the position in the vector
     * @return the element in the position
     * @throws IllegalArgumentException if the index is out of bounds
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < objects.length) {
            return (T) objects[index];
        }
        throw new IllegalArgumentException("\nIndex out of range.\n");
    }

    /**
     * Checks if this vector reached the end. If yes, then this vector will be
     * copied to a new one with {@code X} slots.
     */
    public void incrementSize() {
        if (isFull()) {
            Object[] newVector = new Object[size() + SIZE_TO_INCREMENT];

            for (int i = 0; i < objects.length; i++) {
                newVector[i] = objects[i];
            }
            objects = newVector;
        }
    }

    /**
     * Checks if this vector is empty.
     */
    public boolean isEmpty() {
        return control == 0;
    }

    /**
     * Shrink the instance of this vector to the number of elements used. This
     * could be used to free some memory.
     */
    public void shrinkToSize() {
        Object[] newVector = new Object[control];

        for (int i = 0; i < control; i++) {
            newVector[i] = objects[i];
        }
        objects = newVector;
    }

    /**
     * Verifies if this vector contains the given element.
     * 
     * @param element an element to search for
     * @return true|false
     */
    public boolean contains(T object) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == object) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this vector is full.
     * @return true|false
     */
    public boolean isFull() {
        if (control == objects.length) {
            return true;
        }
        return false;
    }

    /**
     * Verifies if this vector contains the given element and
     * removes from the vector.
     * 
     * @param element an element to remove
     * @return true|false
     */
    public boolean remove(T element) {
        int index = -1;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == element) {
                objects[i] = null;
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < objects.length - 1; i++) {
                objects[i] = objects[i + 1];            
            }
            control--;
            return true;  
        }
        return false;
    }

    /**
     * This function adds an element from the array at the specified index.
     * If the index is occupied, the element is replaced.
     * 
     * @param index the index of the element to be removed
     * @return the method returns a boolean value.
     */
    public boolean setNull(int index) {
        if (index >= 0 && index < size()) {
            objects[index] = null;
            return true;
        }
        return false;
    }

    /**
     * Removes the element in the given index.
     * 
     * @param index the position in the vector
     * @return true|false
     */
    public boolean removeByIndex(int index) {
        if (index >= 0 && index < objects.length) {
            objects[index] = null;

            for (int i = index; i < objects.length - 1; i++) {
                objects[i] = objects[i + 1];
            }
            control--;
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elements in this vector.
     * @return the number of elements
     */
    public int size() {
        return objects.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                builder.append(String.format("[%d]->", i));
                builder.append(objects[i]).append(" ");
            }
        }
        return builder.toString();
    }
}
