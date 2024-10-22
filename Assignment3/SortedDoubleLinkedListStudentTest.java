/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for SortedDoubleLinkedList
 * Due: 10/22/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedListStudentTest {

    private SortedDoubleLinkedList<Integer> sortedList;
    private Comparator<Integer> comparator;

    @Before
    public void setUp() {
        comparator = (a, b) -> a - b;
        sortedList = new SortedDoubleLinkedList<>(comparator);
    }

    @Test
    public void testAddMixedElements() {
        sortedList.add(10);
        sortedList.add(1);
        sortedList.add(5);
        sortedList.add(3);
        sortedList.add(8);
        sortedList.add(7);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertEquals(Integer.valueOf(8), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToEndUnsupported() {
        sortedList.add(15);
        sortedList.addToEnd(20);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToFrontUnsupported() {
        sortedList.add(25);
        sortedList.addToFront(30);
    }

    @Test
    public void testRemoveElementFromMiddle() {
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        sortedList.add(4);
        sortedList.add(5);
        sortedList.add(6);

        sortedList.remove(3, comparator);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(6), iterator.next());
    }

    @Test
    public void testRemoveElementFromEnd() {
        sortedList.add(100);
        sortedList.add(200);
        sortedList.add(300);
        sortedList.add(400);

        sortedList.remove(400, comparator);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(100), iterator.next());
        assertEquals(Integer.valueOf(200), iterator.next());
        assertEquals(Integer.valueOf(300), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveElementFromFront() {
        sortedList.add(10);
        sortedList.add(20);
        sortedList.add(30);
        sortedList.add(40);

        sortedList.remove(10, comparator);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveMultipleElements() {
        sortedList.add(10);
        sortedList.add(20);
        sortedList.add(30);
        sortedList.add(40);
        sortedList.add(50);
        sortedList.add(60);

        sortedList.remove(10, comparator);
        sortedList.remove(50, comparator);
        sortedList.remove(30, comparator);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertEquals(Integer.valueOf(60), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveNonExistentElement() {
        sortedList.add(5);
        sortedList.add(10);
        sortedList.add(15);

        assertNull(sortedList.remove(100, comparator));

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
    }

    @Test
    public void testRemoveFromEmptyList() {
        assertNull(sortedList.remove(10, comparator));
    }

    @Test
    public void testAddAndRemoveMixedOperations() {
        sortedList.add(50);
        sortedList.add(20);
        sortedList.add(70);
        sortedList.add(40);
        sortedList.remove(70, comparator);
        sortedList.add(60);
        sortedList.add(10);

        ListIterator<Integer> iterator = sortedList.iterator();
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertEquals(Integer.valueOf(50), iterator.next());
        assertEquals(Integer.valueOf(60), iterator.next());
    }
}
