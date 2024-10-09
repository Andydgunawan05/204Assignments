/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a generic stack
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
    private ArrayList<T> stack;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 0;

    // constructor to initialize stack with a set capacity
    public MyStack(int capacity) {
        this.stack = new ArrayList<>();
        this.capacity = capacity;
    }
    
    // default default constructor to set capacity to 0
    public MyStack() {
        this.stack = new ArrayList<>();
        this.capacity = DEFAULT_CAPACITY;
    }

    // checks if stack is empty
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // checks if stack is full
    @Override
    public boolean isFull() {
        return stack.size() == capacity;
    }

    // removes the first element in the stack and returns it
    @Override
    public T pop() throws StackUnderflowException {
    	// checks if stack is empty
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty can not pop");
        }
        return stack.remove(stack.size() - 1);
    }

    // returns the first element in the stack without removing it
    @Override
    public T top() throws StackUnderflowException {
    	// checks if stack is empty
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty can not retrieve top");
        }
        return stack.get(stack.size() - 1);
    }

    // returns the number of elements in the stack
    @Override
    public int size() {
        return stack.size();
    }

    // adds a element to the top of the stack
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException("Stack is full can not push");
        }
        stack.add(e);
        return true;
    }

    //returns the elements of the stacks
    @Override
    public String toString() {
        return toString("");
    }
    
    //returns the elements of the stack with a delimiter
    @Override
    public String toString(String delimiter) {
        if (stack.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            result.append(stack.get(i).toString());
            if (i < stack.size() - 1) {
                result.append(delimiter); 
            }
        }
        
        return result.toString();
    }


    // fills the stack with elements from a array list
    @Override
    public void fill(ArrayList<T> list) {
    	// clears the current stack and adds elements from the array list
        stack.clear();
        for (T item : list) {
            try {
                push(item);
            } catch (StackOverflowException ex) {
                break; // stops when the stack becomes full
            }
        }
    }
}
