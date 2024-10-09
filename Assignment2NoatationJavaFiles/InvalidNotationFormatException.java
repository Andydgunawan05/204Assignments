/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a custom exception for when the Notion had an invalid format
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

public class InvalidNotationFormatException extends RuntimeException {
    public InvalidNotationFormatException() {
        super("Invalid notation format");
    }
    
    public InvalidNotationFormatException(String message) {
        super(message);
    }
}
