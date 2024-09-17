/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This program is a game where the user tries to guess a random color  
 * Due: 09/17/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	// Checks if the Password meets all the requirements
	public static boolean isValidPassword(String password) throws 
	LengthException,NoUpperAlphaException, NoLowerAlphaException, NoDigitException,
	NoSpecialCharacterException,InvalidSequenceException {
		if (password.length() < 6) {
			throw new LengthException("The Password must be at least 6 characters long");	
		}
	
		if (!containsUpperCase(password)) {
			throw new NoUpperAlphaException("The Password must contain at least one Upper Case Letter");
		}
		
		if(!containsLowerCase(password)) {
			throw new NoLowerAlphaException("The Password must contain at least one Lower Case Letter");
		}
		
		if(!containsDigit(password)) {
			throw new NoDigitException("The Password must contain at least one number");
		}
		
		if(!containsSpecialCharacter(password)) {
			throw new NoSpecialCharacterException("The Password must contain at least one Special Character");
		}
		
        if (containsInvalidSequence(password)) {
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
        }
        
        return true;
	}
	
	// Checks if a password is weak if it has 6-9 characters
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if (password.length() >= 6 && password.length() <= 9) {
			throw new WeakPasswordException("This Password is OK but weak, it contains less than 10 characters");
		}
		return false;
	}
	
	// Checks a list of passwords and returns invalid passwords
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
	    ArrayList<String> invalidPasswords = new ArrayList<>();
	    for (String password : passwords) {
	        try {
	            isValidPassword(password);
	        } catch (LengthException e) {
	            invalidPasswords.add(password + " The password must be at least 6 characters long");
	        } catch (NoUpperAlphaException e) {
	            invalidPasswords.add(password + " The password must contain at least one uppercase alphabetic character");
	        } catch (NoLowerAlphaException e) {
	            invalidPasswords.add(password + " The password must contain at least one lowercase alphabetic character");
	        } catch (NoDigitException e) {
	            invalidPasswords.add(password + " The password must contain at least one digit");
	        } catch (NoSpecialCharacterException e) {
	            invalidPasswords.add(password + " The password must contain at least one special character");
	        } catch (InvalidSequenceException e) {
	            invalidPasswords.add(password + " The password cannot contain more than two of the same character in sequence");
	        }
	    }
	    return invalidPasswords;
	}
	
	// Checks if the password contains a upper case letter
	private static boolean containsUpperCase(String password) {
		for(char c : password.toCharArray()) {
			if(Character.isUpperCase(c)) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if the password contains a lower case letter
	private static boolean containsLowerCase(String password) {
		for(char c : password.toCharArray()) {
			if(Character.isLowerCase(c)) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if the password contains a number
	private static boolean containsDigit(String password) {
		for(char c : password.toCharArray()) {
			if(Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if the password contains a Special Character
	private static boolean containsSpecialCharacter(String password) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
	}
	
	// Checks if the password has 2 of the same characters in a row
	private static boolean containsInvalidSequence(String password) {
	    for (int i = 0; i < password.length() - 1; i++) {  
	        if (password.charAt(i) == password.charAt(i + 1)) {
	            return true; 
	        }
	    }
	    return false;  
	}
    
    // Checks if the password contains a upper case letter
    public static boolean hasUpperAlpha(String password) {
        return containsUpperCase(password);
    }

    // Checks if the password is a valid length
    public static void isValidLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }
    }
    
    // Compares 2 passwords 
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!password.equals(passwordConfirm)) {
            throw new UnmatchedException();
        }
    }
	
    // Compares 2 passwords
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
	    return password.equals(passwordConfirm);
	}


}

