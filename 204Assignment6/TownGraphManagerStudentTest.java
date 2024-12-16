/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for TownGraphManager
 * Due: 12/15/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;

public class TownGraphManagerStudentTest {
    private TownGraphManager manager;

    @Before
    public void setUp() {
        manager = new TownGraphManager();
        
        // initial data
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addTown("Town3");
        manager.addTown("Town4");

        manager.addRoad("Town1", "Town2", 5, "Road1");
        manager.addRoad("Town2", "Town3", 10, "Road2");
        manager.addRoad("Town3", "Town4", 7, "Road3");
    }

    @Test
    public void testAddRoad() {
        assertTrue(manager.addRoad("Town1", "Town3", 12, "Road4"));
        assertEquals("Road4", manager.getRoad("Town1", "Town3"));
    }

    @Test
    public void testGetRoad() {
        assertEquals("Road1", manager.getRoad("Town1", "Town2"));
        assertEquals("Road2", manager.getRoad("Town2", "Town3"));
        assertNull(manager.getRoad("Town1", "Town4")); 
    }

    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("Town5"));
        assertTrue(manager.containsTown("Town5"));
    }

    @Test
    public void testContainsTown() {
        assertTrue(manager.containsTown("Town1"));
        assertFalse(manager.containsTown("Town6"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertTrue(manager.containsRoadConnection("Town1", "Town2"));
        assertFalse(manager.containsRoadConnection("Town1", "Town4"));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = manager.allRoads();
        assertEquals(3, roads.size());
        assertTrue(roads.contains("Road1"));
        assertTrue(roads.contains("Road2"));
        assertTrue(roads.contains("Road3"));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(manager.deleteRoadConnection("Town1", "Town2", "Road1"));
        assertFalse(manager.containsRoadConnection("Town1", "Town2"));
        assertFalse(manager.deleteRoadConnection("Town1", "Town2", "Road1")); 
    }

    @Test
    public void testDeleteTown() {
        assertTrue(manager.deleteTown("Town4"));
        assertFalse(manager.containsTown("Town4"));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> towns = manager.allTowns();
        assertEquals(4, towns.size());
        assertTrue(towns.contains("Town1"));
        assertTrue(towns.contains("Town2"));
        assertTrue(towns.contains("Town3"));
        assertTrue(towns.contains("Town4"));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = manager.getPath("Town1", "Town4");
        assertNotNull(path);
        assertEquals(3, path.size());
        assertEquals("Town1 via Road1 to Town2 5 mi", path.get(0));
        assertEquals("Town2 via Road2 to Town3 10 mi", path.get(1));
        assertEquals("Town3 via Road3 to Town4 7 mi", path.get(2));
    }

    
    @Test
    public void testPopulateTownGraphWithoutFile() {
        try {
            String content = "Road1,5;Rockville;Gaithersburg\nRoad2,10;Gaithersburg;Clarksburg";
            File tempFile = File.createTempFile("tempGraph", ".txt");
            tempFile.deleteOnExit(); 
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                writer.print(content);
            }

            manager.populateTownGraph(tempFile);
            assertTrue(manager.containsTown("Rockville"));
            assertTrue(manager.containsTown("Gaithersburg"));
            assertTrue(manager.containsRoadConnection("Rockville", "Gaithersburg"));
            assertTrue(manager.containsRoadConnection("Gaithersburg", "Clarksburg"));

        } catch (Exception e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    
}
