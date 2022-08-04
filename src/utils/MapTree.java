package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import base.Generic;
import base.Vehicle;

public class MapTree implements Generic {
    private ABB[] abb;

    public MapTree() {
        abb = new ABB[10];
    }

    private int hash(int key) {
        return (int) key / 10000 % abb.length;
    }

    @Override
    public boolean isEmpty() {
        for (ABB a : abb) {
            if (a != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        for (ABB a : abb) {
            if (a.search((Integer) key) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (ABB a : abb) {
            if (a.search((Vehicle) value) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vehicle get(Object key) {
        for (ABB a : abb) {
            if (a.search((Integer) key) != null) {
                return a.search((Integer) key).getValue();
            }
        }
        return null;
    }
    
    @Override
    public Vehicle put(Integer key, Vehicle value) {
        int hash = hash(key);
        var abb = this.abb[hash];

        if (abb == null) {
            abb = new ABB();
            abb.add(value);
            this.abb[hash] = abb;
            return value;
        } else {
            if (this.abb[hash].search(value) == null) {
                this.abb[hash].add(value);
            }
            return value;
        }
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("sample\\tree_ordered.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("chassi, marca\n" + toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVehicles() {
        for (int i = 0; i <= 4; i++) {
            abb[i] = null;
        }
        NodeTree<Vehicle> toRemove;
        
        if (abb[5] != null && (toRemove = abb[5].search(202050000)) != null) {
            abb[5].remove(toRemove.getValue());
        }
    }

    @Override
    public int findFord() {
        int count = 0;

        for (ABB a : abb) {
            if (a != null) {
                count += a.findFord();
            }
        }
        return count;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < abb.length; i++) {
            if (abb[i] != null) {
                sb.append(abb[i]);
            }
        } 
        return sb.toString();
    }

    @Override
    public Vehicle remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Vehicle> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        abb = null;
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

    @Override
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
