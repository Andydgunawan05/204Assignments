/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a generic queue
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> implements QueueInterface<T> {
    private List<T> queue;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 0;

    // constructor to initialize queue with a set capacity
    public MyQueue(int capacity) {
        this.queue = new ArrayList<>();
        this.capacity = capacity;
    }
    
    // default constructor to set capacity to 0
    public MyQueue() {
        this.queue = new ArrayList<>();
        this.capacity = DEFAULT_CAPACITY;
    }

    // checks if queue is empty
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // checks if queue is full
    @Override
    public boolean isFull() {
        return queue.size() == capacity;
    }

    // removes the first element in queue and returns it
    @Override
    public T dequeue() throws QueueUnderflowException {
    	// checks if queue is empty
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        return queue.remove(0);
    }

    // returns the number of elements in the queue
    @Override
    public int size() {
        return queue.size();
    }

    // adds a element to the end of the queue
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
    	// checks if stack is full
        if (isFull()) {
            throw new QueueOverflowException();
        }
        queue.add(e);
        return true;
    }

    // returns the elements of the queue with a delimiter
    @Override
    public String toString(String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            result.append(queue.get(i).toString());
            if (i < queue.size() - 1) { 
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    // returns the element of the queue
    @Override
    public String toString() {
        return toString("");
    }

    // fills the queue with elements from a array list 
    @Override
    public void fill(ArrayList<T> list) {
        // clears the current queue and adds the elements from the list
        queue.clear();
        for (T item : list) {
            try {
                enqueue(item);
            } catch (QueueOverflowException e) {
                break;  // stops when the queue becomes full
            }
        }
    }
}
