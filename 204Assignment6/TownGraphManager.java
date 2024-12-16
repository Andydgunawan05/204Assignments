/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: Manages a graph of towns and roads
 * Due: 12/15/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph = new Graph(); // the graph that holds towns and roads

    // adds a road between two towns
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        graph.addVertex(t1);
        graph.addVertex(t2);
        graph.addEdge(t1, t2, weight, roadName);
        return true;
    }

    // gets the name of the road connecting two towns
    @Override
    public String getRoad(String town1, String town2) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        Road road = graph.getEdge(t1, t2);
        return (road != null) ? road.getName() : null;
    }

    // adds a town 
    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    // finds a town using its name
    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    // checks if a town exists 
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    // checks if there is a road between two towns
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        return graph.containsEdge(t1, t2);
    }

    // returns a list of all roads sorted by their names
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roadNames = new ArrayList<>();
        for (Road road : graph.edgeSet()) {
            roadNames.add(road.getName());
        }
        Collections.sort(roadNames);
        return roadNames;
    }

    // deletes a road between two towns
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        Road road = graph.getEdge(t1, t2);
        if (road != null && road.getName().equals(roadName)) {
            graph.removeEdge(t1, t2, road.getWeight(), roadName);
            return true;
        }
        return false;
    }

    // deletes a town 
    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    // returns a list of all town names 
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> townNames = new ArrayList<>();
        for (Town town : graph.vertexSet()) {
            townNames.add(town.getName());
        }
        Collections.sort(townNames);
        return townNames;
    }

    // finds the shortest path between two towns
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        ArrayList<String> path = graph.shortestPath(t1, t2);
        return (path != null) ? path : new ArrayList<>();
    }

    // reads towns and roads from a file to build the graph
    public void populateTownGraph(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String[] roadInfo = parts[0].split(",");
            String roadName = roadInfo[0];
            int distance = Integer.parseInt(roadInfo[1]);
            String town1 = parts[1];
            String town2 = parts[2];
            addRoad(town1, town2, distance, roadName);
        }
        scanner.close();
    }
}
