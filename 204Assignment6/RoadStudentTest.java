/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for Road
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

public class RoadStudentTest {

    private Road road1, road2, road3;
    private Town town1, town2, town3;

    @Before
    public void setUp() {
        town1 = new Town("Town1");
        town2 = new Town("Town2");
        town3 = new Town("Town3");

        road1 = new Road(town1, town2, 5, "Road1");
        road2 = new Road(town1, town3, 7, "Road2");
        road3 = new Road(town1, town2, 5, "Road1");
    }

    @Test
    public void testConstructor() {
        assertNotNull(road1);
        assertEquals("Road1", road1.getName());
        assertEquals(5, road1.getWeight());
        assertTrue(road1.contains(town1));
        assertTrue(road1.contains(town2));
    }

    @Test
    public void testGetters() {
        assertEquals("Road1", road1.getName());
        assertEquals(5, road1.getWeight());
    }

    @Test
    public void testContains() {
        assertTrue(road1.contains(town1));
        assertTrue(road1.contains(town2));
        assertFalse(road1.contains(town3));
    }

    @Test
    public void testEquals() {
        assertTrue(road1.equals(road3));
        assertFalse(road1.equals(road2));
    }

    @Test
    public void testToString() {
        assertTrue(road1.toString().contains("Road1"));
        assertTrue(road1.toString().contains("Town1"));
        assertTrue(road1.toString().contains("Town2"));
        assertTrue(road1.toString().contains("5"));
    }

    @Test
    public void testHashCode() {
        assertEquals(road1.hashCode(), road3.hashCode());
        assertNotEquals(road1.hashCode(), road2.hashCode());
    }

    @Test
    public void testCompareTo() {
        Road shorterRoad = new Road(town1, town2, 3, "Road3");
        assertTrue(road1.compareTo(shorterRoad) > 0);
        assertTrue(shorterRoad.compareTo(road1) < 0);
        assertEquals(0, road1.compareTo(road3));
    }
}
