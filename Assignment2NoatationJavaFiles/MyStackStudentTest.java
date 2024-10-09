import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackStudentTest {

    private MyStack<Integer> intStack;
    private MyStack<String> stringStack;

    @BeforeEach
    void setUp() {
        intStack = new MyStack<>(3);
        stringStack = new MyStack<>(2);
    }

    @Test
    void testIsEmpty() {
        assertTrue(intStack.isEmpty(), "pass");
        intStack.push(1);
        assertFalse(intStack.isEmpty(), "fail");
    }

    @Test
    void testIsFull() {
        assertFalse(intStack.isFull(), "fail");
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        assertTrue(intStack.isFull(), "pass");
    }

    @Test
    void testPush() {
        try {
            assertTrue(intStack.push(1), "pass");
            assertTrue(intStack.push(2), "pass");
            assertEquals(2, intStack.size(), "pass");
            assertTrue(intStack.push(3), "pass");
            assertEquals(3, intStack.size(), "pass");
            assertThrows(StackOverflowException.class, () -> intStack.push(4), "exception thrown");
        } catch (StackOverflowException e) {
            fail("fail");
        }
    }

    @Test
    void testPop() {
        try {
            intStack.push(1);
            intStack.push(2);
            assertEquals(2, intStack.pop(), "pass");
            assertEquals(1, intStack.pop(), "pass");
            assertTrue(intStack.isEmpty(), "pass");
            assertThrows(StackUnderflowException.class, () -> intStack.pop(), "exception thrown");
        } catch (StackUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testTop() {
        try {
            intStack.push(1);
            intStack.push(2);
            assertEquals(2, intStack.top(), "pass");
            intStack.pop();
            assertEquals(1, intStack.top(), "pass");
        } catch (StackUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testSize() {
        assertEquals(0, intStack.size(), "pass");
        intStack.push(1);
        assertEquals(1, intStack.size(), "pass");
        intStack.push(2);
        intStack.push(3);
        assertEquals(3, intStack.size(), "pass");
        try {
            intStack.pop();
            assertEquals(2, intStack.size(), "pass");
        } catch (StackUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testToString() {
        try {
            stringStack.push("A");
            stringStack.push("B");
            assertEquals("AB", stringStack.toString(), "pass");
            stringStack.pop();
            assertEquals("A", stringStack.toString(), "pass");
        } catch (StackOverflowException | StackUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testToStringWithDelimiter() {
        try {
            stringStack.push("A");
            stringStack.push("B");
            assertEquals("A B", stringStack.toString(" "), "pass");
            assertEquals("A,B", stringStack.toString(","), "pass");
            stringStack.pop();
            assertEquals("A", stringStack.toString(" "), "pass");
        } catch (StackOverflowException | StackUnderflowException e) {
            fail("fail");
        }
    }

    @Test
    void testFill() {
        ArrayList<String> fillList = new ArrayList<>();
        fillList.add("X");
        fillList.add("Y");

        stringStack.fill(fillList);
        assertEquals(2, stringStack.size(), "pass");
        assertEquals("X Y", stringStack.toString(" "), "pass");
    }
}
