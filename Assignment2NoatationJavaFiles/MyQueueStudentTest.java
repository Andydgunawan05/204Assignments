/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a JUnit test for MyQueue
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyQueueStudentTest {

    private MyQueue<Integer> intQueue;
    private MyQueue<String> stringQueue;

    @BeforeEach
    void setUp() {
        intQueue = new MyQueue<>(3);
        stringQueue = new MyQueue<>(2);
    }

    @Test
    void testIsEmpty() {
        assertTrue(intQueue.isEmpty(), "pass");
        intQueue.enqueue(1);
        assertFalse(intQueue.isEmpty(), "fail");
    }

    @Test
    void testIsFull() {
        assertFalse(intQueue.isFull(), "fail");
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        assertTrue(intQueue.isFull(), "pass");
    }

    @Test
    void testEnqueue() {
        try {
            assertTrue(intQueue.enqueue(1), "pass");
            assertTrue(intQueue.enqueue(2), "pass");
            assertEquals(2, intQueue.size(), "pass");
            assertTrue(intQueue.enqueue(3), "pass");
            assertEquals(3, intQueue.size(), "pass");
            assertThrows(QueueOverflowException.class, () -> intQueue.enqueue(4), "exception thrown");
        } catch (QueueOverflowException e) {
            fail("fail");
        }
    }

    @Test
    void testDequeue() {
        try {
            intQueue.enqueue(1);
            intQueue.enqueue(2);
            assertEquals(1, intQueue.dequeue(), "pass");
            assertEquals(2, intQueue.dequeue(), "pass");
            assertTrue(intQueue.isEmpty(), "pass");
            assertThrows(QueueUnderflowException.class, () -> intQueue.dequeue(), "exception thrown");
        } catch (QueueUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testSize() {
        assertEquals(0, intQueue.size(), "pass");
        intQueue.enqueue(1);
        assertEquals(1, intQueue.size(), "pass");
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        assertEquals(3, intQueue.size(), "pass");
        try {
            intQueue.dequeue();
            assertEquals(2, intQueue.size(), "pass");
        } catch (QueueUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testToString() {
        try {
            stringQueue.enqueue("A");
            stringQueue.enqueue("B");
            assertEquals("AB", stringQueue.toString(), "pass");
            stringQueue.dequeue();
            assertEquals("B", stringQueue.toString(), "pass");
        } catch (QueueOverflowException | QueueUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testToStringWithDelimiter() {
        try {
            stringQueue.enqueue("A");
            stringQueue.enqueue("B");
            assertEquals("A B", stringQueue.toString(" "), "pass");
            assertEquals("A,B", stringQueue.toString(","), "pass");
            stringQueue.dequeue();
            assertEquals("B", stringQueue.toString(" "), "pass");
        } catch (QueueOverflowException | QueueUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testFill() {
        ArrayList<String> fillList = new ArrayList<>();
        fillList.add("X");
        fillList.add("Y");

        stringQueue.fill(fillList);
        assertEquals(2, stringQueue.size(), "pass");
        assertEquals("X Y", stringQueue.toString(" "), "pass");
    }
}
