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
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vehicle get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Vehicle put(Integer key, Vehicle value) {
        int hash = hash(key);
        ABB abb = this.abb[hash];

        if (abb == null) {
            abb = new ABB();
            abb.add(value);
            this.abb[hash] = abb;
        } else {
            this.abb[hash].add(value);
        }
        return value;
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
    public Vehicle remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Vehicle> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<Integer> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Vehicle> values() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Entry<Integer, Vehicle>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeVehicles() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int findFord() {
        // TODO Auto-generated method stub
        return 0;
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
}
