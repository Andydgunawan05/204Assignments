/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for Graph
 * Due: 12/15/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class GraphStudentTest {

    private Graph graph;
    private Town town1, town2, town3, town4;
    @SuppressWarnings("unused")
	private Road road1, road2, road3;

    @Before
    public void setUp() {
        graph = new Graph();

        town1 = new Town("Town1");
        town2 = new Town("Town2");
        town3 = new Town("Town3");
        town4 = new Town("Town4");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addVertex(town3);
        graph.addVertex(town4);

        road1 = graph.addEdge(town1, town2, 5, "Road1");
        road2 = graph.addEdge(town2, town3, 10, "Road2");
        road3 = graph.addEdge(town3, town4, 7, "Road3");
    }

    @Test
    public void testAddVertex() {
        Town newTown = new Town("Town5");
        assertTrue(graph.addVertex(newTown));
        assertTrue(graph.containsVertex(newTown));
        assertFalse(graph.addVertex(newTown));
    }

    @Test
    public void testAddEdge() {
        Road newRoad = graph.addEdge(town1, town3, 12, "Road4");
        assertNotNull(newRoad);
        assertTrue(graph.containsEdge(town1, town3));
        assertEquals("Road4", newRoad.getName());
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.removeVertex(town4));
        assertFalse(graph.containsVertex(town4));
        assertFalse(graph.removeVertex(town4));
    }

    @Test
    public void testRemoveEdge() {
        assertTrue(graph.removeEdge(town1, town2, 5, "Road1") != null);
        assertFalse(graph.containsEdge(town1, town2));
    }

    @Test
    public void testEdgesOf() {
        Set<Road> edges = graph.edgesOf(town2);
        assertEquals(2, edges.size());
    }

    @Test
    public void testGetEdge() {
        Road road = graph.getEdge(town1, town2);
        assertNotNull(road);
        assertEquals("Road1", road.getName());
    }

    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(town1));
        assertFalse(graph.containsVertex(new Town("NonexistentTown")));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(town1, town2));
        assertFalse(graph.containsEdge(town1, town4));
    }

    @Test
    public void testShortestPath() {
        graph.addEdge(town1, town3, 3, "Road4");
        graph.dijkstraShortestPath(town1);
        assertEquals(2, graph.shortestPath(town1, town4).size());
        assertEquals("Town1 via Road4 to Town3 3 mi", graph.shortestPath(town1, town4).get(0));
        assertEquals("Town3 via Road3 to Town4 7 mi", graph.shortestPath(town1, town4).get(1));
    }
}
