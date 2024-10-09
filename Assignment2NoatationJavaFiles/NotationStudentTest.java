/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a JUnit test for Notation
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NotationStudentTest {

    @Test
    void testEvaluatePostfixExpression() {
        try {
            assertEquals(7.0, Notation.evaluatePostfixExpression("34+"), 0.001);
            assertEquals(14.0, Notation.evaluatePostfixExpression("72*"), 0.001);
            assertEquals(6.0, Notation.evaluatePostfixExpression("93-"), 0.001);
            assertEquals(2.0, Notation.evaluatePostfixExpression("84/"), 0.001);
        } catch (InvalidNotationFormatException e) {
            fail("Exception was thrown " + e);
        }
    }

    @Test
    void testEvaluatePostfixExpressionInvalid() {
        assertThrows(InvalidNotationFormatException.class, () -> Notation.evaluatePostfixExpression("34+*"));
    }

    @Test
    void testConvertPostfixToInfix() {
        try {
            assertEquals("(3+4)", Notation.convertPostfixToInfix("34+"));
            assertEquals("(7*2)", Notation.convertPostfixToInfix("72*"));
            assertEquals("(9-3)", Notation.convertPostfixToInfix("93-"));
            assertEquals("(8/4)", Notation.convertPostfixToInfix("84/"));
        } catch (InvalidNotationFormatException e) {
            fail("Exception was thrown " + e);
        }
    }

    @Test
    void testConvertPostfixToInfixInvalid() {
        assertThrows(InvalidNotationFormatException.class, () -> Notation.convertPostfixToInfix("93-+"));
    }

    @Test
    void testConvertInfixToPostfix() {
        try {
            assertEquals("34+", Notation.convertInfixToPostfix("3+4"));
            assertEquals("72*", Notation.convertInfixToPostfix("7*2"));
            assertEquals("93-", Notation.convertInfixToPostfix("9-3"));
            assertEquals("84/", Notation.convertInfixToPostfix("8/4"));
        } catch (InvalidNotationFormatException e) {
            fail("Exception was thrown " + e);
        }
    }

    @Test
    void testConvertInfixToPostfixInvalid() {
    	assertThrows(InvalidNotationFormatException.class, () -> Notation.convertInfixToPostfix("(3+4"));
    }
}
