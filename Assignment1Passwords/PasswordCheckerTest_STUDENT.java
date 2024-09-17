
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	  @Test
	    public void testIsValidPasswordTooShort() {
	        try {
	            PasswordCheckerUtility.isValidPassword("abc1A");
	            fail("LengthException Fail");
	        } catch (LengthException e) {
	            assertTrue("LengthException Pass", true);
	        } catch (Exception e) {
	            fail("LengthException Fail (Different) " + e);
	        }
	    }
	    
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	   @Test
	    public void testIsValidPasswordNoUpperAlpha() {
	        try {
	            PasswordCheckerUtility.isValidPassword("asdfghjk!");
	            fail("NoUpperAlphaException Fail");
	        } catch (NoUpperAlphaException e) {
	            assertTrue("NoUpperAlphaException Pass", true);
	        } catch (Exception e) {
	            fail("NoUpperAlphaException Fail (Different) " + e);
	        }
	    }
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	   @Test
	    public void testIsValidPasswordNoLowerAlpha() {
	        try {
	            PasswordCheckerUtility.isValidPassword("ASDFGHJKL!");
	            fail("NoLowerAlphaException Fail");
	        } catch (NoLowerAlphaException e) {
	            assertTrue("NoLowerAlphaException Pass", true);
	        } catch (Exception e) {
	            fail("NoLowerAlphaException Fail (Different) " + e);
	        }
	    }
	   
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	   @Test
	    public void testIsWeakPassword() {
	        try {
	            PasswordCheckerUtility.isWeakPassword("abcD1!");
	            fail("WeakPasswordException Fail");
	        } catch (WeakPasswordException e) {
	            assertTrue("WeakPasswordException Pass", true);
	        } catch (Exception e) {
	            fail("WeakPasswordException Fail (Different) " + e);
	        }
	    }

	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	    @Test
	    public void testIsValidPasswordInvalidSequence() {
	        try {
	            PasswordCheckerUtility.isValidPassword("aaaFghjk1!");
	            fail("InvalidSequenceException Fail");
	        } catch (InvalidSequenceException e) {
	            assertTrue("InvalidSequenceException Pass", true);
	        } catch (Exception e) {
	            fail("InvalidSequenceException Fail (Different) " + e);
	        }
	    }
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	    @Test
	    public void testIsValidPasswordNoDigit() {
	        try {
	            PasswordCheckerUtility.isValidPassword("Asdfghjk!@");
	            fail("NoDigitException Fail");
	        } catch (NoDigitException e) {
	            assertTrue("NoDigitException Pass", true);
	        } catch (Exception e) {
	            fail("NoDigitException Fail (Different) " + e);
	        }
	    }
	    
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	    @Test
	    public void testIsValidPasswordSuccessful() {
	        try {
	            assertTrue(PasswordCheckerUtility.isValidPassword("Asdfghjk1!"));
	        } catch (Exception e) {
	            fail("isValidPassword Fail " + e);
	        }
	    }
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	    @Test
	    public void testInvalidPasswords() {
	        ArrayList<String> passwords = new ArrayList<>(Arrays.asList("abc","abcdef", "ABCDEF","ABCdef","abcDEF123","AAAbb123!"));
	        ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(passwords);
	        assertTrue(results.get(0).contains("The password must be at least 6 characters long"));
	        assertTrue(results.get(1).contains("The password must contain at least one uppercase alphabetic character"));
	        assertTrue(results.get(2).contains("The password must contain at least one lowercase alphabetic character"));
	        assertTrue(results.get(3).contains("The password must contain at least one digit"));
	        assertTrue(results.get(4).contains("The password must contain at least one special character"));
	        assertTrue(results.get(5).contains("The password cannot contain more than two of the same character in sequence"));
	    }

}
