import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
    // graph is stored as an adjacency list, each town is linked to a list of roads that connect it to other towns
    private Map<Town, Set<Road>> adjacencyMap = new HashMap<>();

    // adds a town to the graph
    @Override
    public boolean addVertex(Town town) {
        if (town == null || adjacencyMap.containsKey(town)) return false;
        adjacencyMap.put(town, new HashSet<>());
        return true;
    }

    // adds a road between two towns
    @Override
    public Road addEdge(Town source, Town destination, int weight, String description) {
        if (source == null || destination == null || !adjacencyMap.containsKey(source) || !adjacencyMap.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination not in graph");
        }
        Road road = new Road(source, destination, weight, description);
        adjacencyMap.get(source).add(road);
        adjacencyMap.get(destination).add(road); // since the graph is undirected
        return road;
    }

    // removes a town and all its connected roads
    @Override
    public boolean removeVertex(Town town) {
        if (!adjacencyMap.containsKey(town)) return false;
        adjacencyMap.values().forEach(roads -> roads.removeIf(road -> road.contains(town)));
        adjacencyMap.remove(town);
        return true;
    }

    // removes a road between two towns
    @Override
    public Road removeEdge(Town source, Town destination, int weight, String description) {
        Road roadToRemove = new Road(source, destination, weight, description);
        if (adjacencyMap.containsKey(source)) {
            adjacencyMap.get(source).remove(roadToRemove);
        }
        if (adjacencyMap.containsKey(destination)) {
            adjacencyMap.get(destination).remove(roadToRemove);
        }
        return roadToRemove;
    }

    // returns a set of all towns
    @Override
    public Set<Town> vertexSet() {
        return adjacencyMap.keySet();
    }

    // returns a set of all roads
    @Override
    public Set<Road> edgeSet() {
        Set<Road> roads = new HashSet<>();
        for (Set<Road> roadSet : adjacencyMap.values()) {
            roads.addAll(roadSet);
        }
        return roads;
    }

    // finds all roads connected to a town
    @Override
    public Set<Road> edgesOf(Town town) {
        if (!adjacencyMap.containsKey(town)) {
            throw new IllegalArgumentException("Town not found in the graph");
        }
        return adjacencyMap.get(town);
    }

    // finds the shortest path between two towns
    @Override
    public ArrayList<String> shortestPath(Town source, Town destination) {
        // dijkstra's algorithm
        dijkstraShortestPath(source);

        // builds the path from source to destination
        ArrayList<String> path = new ArrayList<>();
        Town current = destination;
        while (current != null && !current.equals(source)) {
            Town previous = previousTowns.get(current);
            if (previous == null) break;
            Road road = getEdge(previous, current);
            path.add(0, previous.getName() + " via " + road.getName() + " to " + current.getName() + " " + road.getWeight() + " mi");
            current = previous;
        }

        return path.isEmpty() ? null : path;
    }

    // dijkstra's algorithm for shortest path
    private Map<Town, Integer> distances;
    private Map<Town, Town> previousTowns;

    @Override
    public void dijkstraShortestPath(Town source) {
        distances = new HashMap<>();
        previousTowns = new HashMap<>();
        PriorityQueue<Town> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Town town : adjacencyMap.keySet()) {
            distances.put(town, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Town current = queue.poll();
            for (Road road : edgesOf(current)) {
                Town neighbor = road.getSource().equals(current) ? road.getDestination() : road.getSource();
                int newDist = distances.get(current) + road.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previousTowns.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    // retrieves the edge between two towns
    @Override
    public Road getEdge(Town source, Town destination) {
        for (Road road : adjacencyMap.getOrDefault(source, new HashSet<>())) {
            if (road.contains(destination)) return road;
        }
        return null;
    }

    // checks if the graph contains a specific town
    @Override
    public boolean containsVertex(Town town) {
        return adjacencyMap.containsKey(town);
    }

    // checks if the graph contains a road connecting two towns
    @Override
    public boolean containsEdge(Town source, Town destination) {
        return getEdge(source, destination) != null;
    }
}
