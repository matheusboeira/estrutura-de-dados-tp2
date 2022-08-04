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

public class MapVector implements Generic {
    private final double MAGIC = 0.63274838;

    private Vector<Vehicle> vector;
    private int howManyFords;
    private int control;

    public MapVector(int size) {
        vector = new Vector<>(size);
        control = 0;
    }

    private int hash(int key) {
        return (int) (((key * MAGIC) % 1) * vector.size());
    }

    @Override
    public Vehicle get(Object key) {
        int pump = 0;
        int _chassi = (int) key;

        while (pump < vector.size()) {
            int _key = (hash((int) key) + pump) % vector.size();
            
            if (vector.get(_key).getChassi() == _chassi) {
                return vector.get(_key);
            }
            pump++;
        }
        return null;
    }

    @Override
    public Vehicle put(Integer key, Vehicle value) {
        int pump = 0;
        int hash;

        while (pump < vector.size()) {
            hash = (hash(key) + pump) % vector.size();

            if (vector.get(hash) == null || vector.get(hash).getChassi() == key) {
                vector.add(value, hash);
                return vector.get(hash);
            }
            pump++;
        }
        return null;
    }

    @Override
    public int size() {
        return vector.size();
    }

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return vector.get(hash((int) key)) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Vehicle _vehicle = (Vehicle) value;
        return vector.get((int) hash(_vehicle.getChassi())) != null;
    }

    @Override
    public Vehicle remove(Object key) {
        var _vehicle = vector.get(hash((int) key));
        vector.remove(_vehicle);
        return _vehicle;
    }

    public void removeVehicles() {
        int elements = 0;

        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                if (vector.get(i).getChassi() <= 202050000) {
                    vector.setNull(i);
                } else {
                    elements++;
                }
            }
        }  
        MapVector newMapVector = new MapVector(elements);

        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                newMapVector.put(vector.get(i).getChassi(), vector.get(i));
            }
        }
        vector = newMapVector.vector;
        control = 1;     
    }

    public int findFord() {
       for (int i = 0; i < vector.size(); i++) {
           if (vector.get(i) != null) {
               if (vector.get(i).isMarcaFord()) {
                   howManyFords++;
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

    public void sort() {
        int elements = 0;

        if (control == 1) {
            /**
             * Quando os elementos já foram removidos.
             */
            _sort();
            return;
        }

        /**
         * Quando os elementos ainda não foram removidos.
         */
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                elements++; 
            }
        }
        MapVector newMapVector = new MapVector(elements);
        
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                newMapVector.put(vector.get(i).getChassi(), vector.get(i));
            }
        }
        vector = newMapVector.vector;
        _sort();
    }

    private void _sort() {        
        for (int i = 0; i < vector.size() - 1; i++) {
            for (int j = (i + 1); j < vector.size(); j++) {
                if (vector.get(i).getChassi() > vector.get(j).getChassi()) {
                    var aux = vector.get(i);
                    vector.add(vector.get(j), i);
                    vector.add(aux, j);
                }
            }
        }
    }

    public String printSorted() {
        var builder = new StringBuilder();
        Vehicle vehicle = null;

        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                vehicle = vector.get(i);
                builder.append(vehicle.getChassi()).append(", ");
                builder.append(vehicle.getMarca()).append("\n");
            }
        }
        return builder.toString();
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("sample\\vector_ordered.txt");
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
        
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) != null) {
                builder.append(String.format("[%d]", i)).append(": ").append(vector.get(i)).append("\n");
            } else {
                builder.append(String.format("[%d]", i)).append(": ").append("[]").append("\n");
            }
        }
        return builder.toString();
    }
}
