/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a custom exception for when the Stack is full is empty and a operation is performed on the queue
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

public class StackUnderflowException extends RuntimeException{

    public StackUnderflowException() {
        super("Stack is empty can not perform this operation");
    }

    public StackUnderflowException(String message) {
        super(message);
    }
}
