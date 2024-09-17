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

//Custom exception thrown when two passwords don't match
public class UnmatchedException extends Exception {
	public UnmatchedException() {
		super("Passwords do not match");
	}
}