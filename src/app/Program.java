package app;

import base.ComputeTime;
import utils.App;
import utils.MapList;
import utils.MapTree;
import utils.MapVector;

public class Program {
    public final static int SIZE = 100_000;

    public static void main(String[] args) {
        App.clearScreen();

        var mapList = new MapList();
        var mapVector = new MapVector(Program.SIZE);
        var mapTree = new MapTree();
        
        var computeTimeList = new ComputeTime<MapList>();
        var computeTimeVector = new ComputeTime<MapVector>();
        var computeTimeTree = new ComputeTime<MapTree>();

        computeTimeList.insertData(mapList);
        computeTimeList.removeVehicles(mapList);
        computeTimeList.sort(mapList);
        computeTimeList.findFord(mapList);
        mapList.saveToFile();

        computeTimeVector.insertData(mapVector);
        computeTimeVector.removeVehicles(mapVector);
        computeTimeVector.sort(mapVector);
        computeTimeVector.findFord(mapVector);
        mapVector.saveToFile();

        computeTimeTree.insertData(mapTree);
        mapTree.saveToFile();
    }
}
