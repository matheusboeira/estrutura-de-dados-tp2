package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import base.Generic;
import base.Vehicle;
import base.enums.Color;

public class MapList implements Generic {
    private DoublyLinkedList[] list;
    private int howManyFords;

    public MapList() {
        list = new DoublyLinkedList[10];
    }

    private int hash(int key) {
        return (int) key / 10000 % list.length;
    }

    @Override
    public Vehicle put(Integer key, Vehicle value) {
        int hash = hash(key);
        DoublyLinkedList list = this.list[hash];

        if (list == null) {
            list = new DoublyLinkedList();
            list.addStart(value);
            this.list[hash] = list;
        } else {
            for (Node<Vehicle> i = list.getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().getChassi() == key) {
                    i.setElement(value);
                    return i.getElement();
                }
            }
            list.addStart(value);
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;

        for (var list : this.list) {
            if (list != null) {
                size += list.size();
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int _key = (int) key;
        int _hash = hash((int) key);

        if (list[_hash] != null) {
            for (Node<Vehicle> i = list[_hash].getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().getChassi() == _key) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Vehicle _vehicle = (Vehicle) value;
        int _hash = (int) hash(_vehicle.getChassi());

        if (list[_hash] != null) {
            for (Node<Vehicle> i = list[_hash].getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Vehicle get(Object key) {
        int _key = (int) key;
        int _hash = hash((int) key);

        if (list[_hash] != null) {
            for (Node<Vehicle> i = list[_hash].getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().getChassi() == _key) {
                    return i.getElement();
                }
            }
        }
        return null;
    }

    @Override
    public Vehicle remove(Object key) {
        int _key = (int) key;
        int _hash = hash((int) key);

        if (list[_hash] != null) {
            for (Node<Vehicle> i = list[_hash].getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().getChassi() == _key) {
                    list[_hash].remove(i.getElement());
                    return i.getElement();
                }
            }
        }
        return null;
    }

    public void removeVehicles() {
        for (int i = 0; i <= 4; i++) {
            list[i] = null;
        }

        if (list[5] != null) {
            for (Node<Vehicle> i = list[5].getFirstNode(); i != null; i = i.getNext()) {
                if (i.getElement().getChassi() == 202050000) {
                    list[5].remove(i.getElement());
                    break;
                }
            }
        }
    }

    /**
     * Returns another list with the same elements as this list but in ascending order.
     */
    public void sort() {
        for (var list : this.list) {
            if (list != null) {
                for (Node<Vehicle> i = list.getFirstNode(); i != null; i = i.getNext()) {
                    for (Node<Vehicle> j = list.getFirstNode(); j != null; j = j.getNext()) {
                        if (i.getElement().getChassi() < j.getElement().getChassi()) {
                            Vehicle aux = i.getElement();
                            i.setElement(j.getElement());
                            j.setElement(aux);
                        }
                    }
                }
            }
        }
    }

    public int findFord() {
        for (var list : this.list) {
            if (list != null) {
                for (Node<Vehicle> i = list.getFirstNode(); i != null; i = i.getNext()) {
                    if (i.getElement().isMarcaFord()) {
                        howManyFords++;
                    }
                }
            }
        }
        return howManyFords;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Vehicle> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Vehicle> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Entry<Integer, Vehicle>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String printSorted() {
        var builder = new StringBuilder();
        Vehicle vehicle = null;

        for (var list : this.list) {
            if (list != null) {
                for (Node<Vehicle> i = list.getFirstNode(); i != null; i = i.getNext()) {
                    vehicle = i.getElement();
                    builder.append(vehicle.getChassi()).append(", ");
                    builder.append(vehicle.getMarca()).append("\n");
                }
            }
        }
        return builder.toString();
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("sample\\list_ordered.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("chassi, marca\n" + printSorted());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();

        App.printf(Color.BLUE, "\n[IMPRESSÃO DO MAPEAMENTO]\n\n");
        
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                builder.append(String.format("[%d]", i)).append(": ").append(list[i]).append("\n");
            } else {
                builder.append(String.format("[%d]", i)).append(": ").append("[]").append("\n");
            }
        }
        return builder.toString();
    }
}
