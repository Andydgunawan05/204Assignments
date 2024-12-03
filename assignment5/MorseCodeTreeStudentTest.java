/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for MorseCodeTree
 * Due: 12/02/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MorseCodeTreeStudentTest {

    private MorseCodeTree morseCodeTree;

    @BeforeEach
    public void setUp() {
        morseCodeTree = new MorseCodeTree(); // initializes tree
    }

    @Test
    public void testFetch() {
        assertEquals("e", morseCodeTree.fetch("."), "Fetch .   return e");
        assertEquals("t", morseCodeTree.fetch("-"), "Fetch -   return t");
        assertEquals("m", morseCodeTree.fetch("--"), "Fetch --  return m");
        assertEquals("h", morseCodeTree.fetch("...."), "Fetch .... return h");
    }

    @Test
    public void testToArrayList() {
        ArrayList<String> result = morseCodeTree.toArrayList();
        String expected = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
        assertEquals(expected, String.join(" ", result), "in order traversal matches string");
    }

    @Test
    public void testInsertAndFetch() {
        morseCodeTree.insert("..--", "custom");
        assertEquals("custom", morseCodeTree.fetch("..--"), "fetch ..-- returns custom");
    }

    @Test
    public void testUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class, () -> morseCodeTree.delete("data"), "delete operation throws UnsupportedOperationException");
        assertThrows(UnsupportedOperationException.class, () -> morseCodeTree.update(), "update operation throws UnsupportedOperationException");
    }
}
