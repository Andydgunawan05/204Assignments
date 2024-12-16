/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for Town
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

public class TownStudentTest {

    private Town town1, town2, town3;

    @Before
    public void setUp() {
        town1 = new Town("Town1");
        town2 = new Town("Town2");
        town3 = new Town("Town1");
    }

    @Test
    public void testConstructor() {
        assertNotNull(town1);
        assertEquals("Town1", town1.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Town1", town1.getName());
        assertEquals("Town2", town2.getName());
    }

    @Test
    public void testEquals() {
        assertTrue(town1.equals(town3));
        assertFalse(town1.equals(town2));
        assertFalse(town1.equals(null));
    }

    @Test
    public void testHashCode() {
        assertEquals(town1.hashCode(), town3.hashCode());
        assertNotEquals(town1.hashCode(), town2.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) < 0);
        assertTrue(town2.compareTo(town1) > 0);
        assertEquals(0, town1.compareTo(town3));
    }

    @Test
    public void testToString() {
        assertEquals("Town1", town1.toString());
        assertEquals("Town2", town2.toString());
    }
}
