/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: This class implements a sorted double linked list that 
 *              maintains elements in sorted order based on a comparator
 * Due: 10/22/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    private Comparator<T> comparator;

    // constructor to set the comparator for sorting the list
    public SortedDoubleLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    // adds a element in the correct position
    public void add(T data) {
        Node newNode = new Node(data);

        // if the list is empty add the node as the first and last element
        if (head == null) {
            head = tail = newNode;
        } else {
            Node current = head;

            // find position by comparing elements
            while (current != null && comparator.compare(data, current.data) > 0) {
                current = current.next;
            }

            // inserts the new node at the front, end, or middle
            if (current == head) {
                // adds to the front
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) {
                // adds to the end
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                // insert between two nodes
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    // adding to the end is not allowed in a sorted list
    @Override
    public void addToEnd(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // adding to the front is not allowed in a sorted list
    @Override
    public void addToFront(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // uses the parent class's iterator
    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    // removes an element using the comparator
    @Override
    public Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}
