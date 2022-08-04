package base;

import java.time.Duration;
import java.time.Instant;

import app.Program;
import base.enums.Color;
import utils.App;
import utils.MapList;
import utils.MapTree;
import utils.MapVector;

public class ComputeTime<T extends Generic> {
    public void insertData(T type) {
        App.printf(Color.PURPLE, String.format("\n\n[CALCULANDO TEMPOS - %s]\n\n", type.getClass().getSimpleName()));
        Instant start = Instant.now();

        for (int i = 0; i < Program.SIZE; i++) {
            var vehicle = new Vehicle();
            type.put(vehicle.getChassi(), vehicle);
        }
        Instant end = Instant.now();

        App.printf(Color.GREEN_BG, "%70s", String.format("Inserção de %d elementos: ", Program.SIZE));
        App.printf(Color.PURPLE_BG, "%8s %2s s%9s", " ", Duration.between(start, end).toSeconds(), " ");
        App.printf(Color.BLUE_BG, "%8s %6s ms%14s", " ", Duration.between(start, end).toMillis(), " ");
        App.printf(Color.WHITE_BG, "%14s %11s ns%18s", " ", Duration.between(start, end).toNanos(), " ");
        System.out.println();
    }

    public void sort(T type) {
        Instant start = Instant.now();
        type.sort();
        Instant end = Instant.now();

        App.printf(Color.GREEN_BG, "%70s", String.format("Ordenação de %d elementos: ", Program.SIZE));
        App.printf(Color.PURPLE_BG, "%8s %2s s%9s", " ", Duration.between(start, end).toSeconds(), " ");
        App.printf(Color.BLUE_BG, "%8s %6s ms%14s", " ", Duration.between(start, end).toMillis(), " ");
        App.printf(Color.WHITE_BG, "%14s %11s ns%18s", " ", Duration.between(start, end).toNanos(), " ");
        System.out.println();
    }

    public void removeVehicles(T type) {
        Instant start = Instant.now();
        type.removeVehicles();
        Instant end = Instant.now();

        App.printf(Color.GREEN_BG, "%70s", String.format("Remoção de carros com chassi inferior ou igual a 202050000: ", Program.SIZE));
        App.printf(Color.PURPLE_BG, "%8s %2s s%9s", " ", Duration.between(start, end).toSeconds(), " ");
        App.printf(Color.BLUE_BG, "%8s %6s ms%14s", " ", Duration.between(start, end).toMillis(), " ");
        App.printf(Color.WHITE_BG, "%14s %11s ns%18s", " ", Duration.between(start, end).toNanos(), " ");
        System.out.println();
    }

    public void findFord(T type) {
        Instant start = Instant.now();
        int howManyFords = type.findFord();
        Instant end = Instant.now();

        App.printf(Color.GREEN_BG, "%70s", String.format("Contagem de veículos da marca Ford (%d): ", howManyFords));
        App.printf(Color.PURPLE_BG, "%8s %2s s%9s", " ", Duration.between(start, end).toSeconds(), " ");
        App.printf(Color.BLUE_BG, "%8s %6s ms%14s", " ", Duration.between(start, end).toMillis(), " ");
        App.printf(Color.WHITE_BG, "%14s %11s ns%18s", " ", Duration.between(start, end).toNanos(), " ");
        System.out.println();
    } 

    public static void getTimePrint(MapVector mapVector, MapList mapList, MapTree mapTree) {
        Instant start_1 = Instant.now();
        mapVector.printSorted();
        Instant end_1 = Instant.now();

        Instant start_2 = Instant.now();
        mapList.printSorted();
        Instant end_2 = Instant.now();

        Instant start_3 = Instant.now();
        System.out.println(mapTree);
        Instant end_3 = Instant.now();

        App.printf(Color.GREEN_BG, "%70s", String.format("Tempo de impressão ordenado (Vetor): %s ns", Duration.between(start_1, end_1).toNanos()));
        System.out.println();
        App.printf(Color.WHITE_BG, "%70s", String.format("Tempo de impressão ordenado (LDE): %s ns",  Duration.between(start_2, end_2).toNanos()));
        System.out.println();
        App.printf(Color.BLUE_BG, "%70s", String.format("Tempo de impressão ordenado (Árvore): %s ns",  Duration.between(start_3, end_3).toNanos()));
        System.out.println();
    }
}
