/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: JUnit testing for BasicDoubleLinkedList
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

public class BasicDoubleLinkedListStudentTest {

    private BasicDoubleLinkedList<Integer> basicList;

    @Before
    public void setUp() {
        basicList = new BasicDoubleLinkedList<>();
    }

    @Test
    public void testAddToEndAndFront() {
        basicList.addToEnd(10);
        basicList.addToEnd(20);
        basicList.addToFront(5);
        basicList.addToFront(1);

        ListIterator<Integer> iterator = basicList.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
    }

    @Test
    public void testRemoveFirstAndLast() {
        basicList.addToEnd(10);
        basicList.addToEnd(20);
        basicList.addToEnd(30);
        basicList.addToEnd(40);

        assertEquals(Integer.valueOf(10), basicList.retrieveFirstElement());
        assertEquals(Integer.valueOf(40), basicList.retrieveLastElement());

        ListIterator<Integer> iterator = basicList.iterator();
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
    }

    @Test
    public void testRemoveElementFromMiddle() {
        basicList.addToEnd(1);
        basicList.addToEnd(2);
        basicList.addToEnd(3);
        basicList.addToEnd(4);
        basicList.addToEnd(5);

        Comparator<Integer> comparator = Integer::compare;
        basicList.remove(3, comparator);

        ListIterator<Integer> iterator = basicList.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
    }

    @Test
    public void testRemoveNonExistentElement() {
        basicList.addToEnd(5);
        basicList.addToEnd(10);
        basicList.addToEnd(15);

        Comparator<Integer> comparator = Integer::compare;
        assertNull(basicList.remove(100, comparator));

        ListIterator<Integer> iterator = basicList.iterator();
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
    }

    @Test
    public void testAddAndRemoveMixedOperations() {
        basicList.addToEnd(50);
        basicList.addToFront(20);
        basicList.addToEnd(70);
        basicList.addToFront(10);
        basicList.retrieveFirstElement();
        basicList.addToEnd(60);
        basicList.retrieveLastElement();

        ListIterator<Integer> iterator = basicList.iterator();
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(50), iterator.next());
        assertEquals(Integer.valueOf(70), iterator.next());
    }

    @Test
    public void testRetrieveFirstAndLast() {
        basicList.addToEnd(100);
        basicList.addToEnd(200);
        basicList.addToEnd(300);

        assertEquals(Integer.valueOf(100), basicList.getFirst());
        assertEquals(Integer.valueOf(300), basicList.getLast());

        basicList.retrieveFirstElement();
        assertEquals(Integer.valueOf(200), basicList.getFirst());

        basicList.retrieveLastElement();
        assertEquals(Integer.valueOf(200), basicList.getLast());
    }

    @Test
    public void testEmptyList() {
        assertNull(basicList.getFirst());
        assertNull(basicList.getLast());
        assertEquals(0, basicList.getSize());
    }

    @Test
    public void testSizeTracking() {
        basicList.addToEnd(1);
        basicList.addToEnd(2);
        basicList.addToFront(3);
        basicList.addToFront(4);

        assertEquals(4, basicList.getSize());

        basicList.retrieveFirstElement();
        basicList.retrieveLastElement();

        assertEquals(2, basicList.getSize());
    }
}
