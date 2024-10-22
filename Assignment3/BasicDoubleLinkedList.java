/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: This class implements a double-linked list that 
 *              allows  for adding, removing, and iterating elements
 * Due: 10/22/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

    // inner Node class
    protected class Node {
        T data;
        Node next;
        Node prev;

        public Node(T dataNode) {
            this.data = dataNode;
            this.next = null;
            this.prev = null;
        }
    }

 // inner DoubleLinkedListIterator class
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node current = head;  // starts at the head
        private boolean atEnd = false; // for when the iterator reaches the end

        @Override
        public boolean hasNext() {
            return current != null && !atEnd;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            if (current.next != null) {
                current = current.next;
            } else {
                //  marks it as the end
                atEnd = true;
                current = null; 
            }
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return (atEnd && tail != null) || (current != null && current.prev != null);
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (atEnd) {
                // if it's at the end it will reset back to tail
                current = tail;
                atEnd = false;
            } else if (current == null || current.prev == null) {
                // if current is null or no previous element exists it will throw  a NoSuchElementException
                throw new NoSuchElementException();
            } else {
                current = current.prev;
            }
            return current.data;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    protected Node head;
    protected Node tail;
    protected int size;


    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // adds an element to the end of the list
    public void addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // adds a element to the front of the list
    public void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // returns the first element without removing it
    public T getFirst() {
        if (head == null) return null;
        return head.data;
    }

    // returns the last element without removing it
    public T getLast() {
        if (tail == null) return null;
        return tail.data;
    }

    // returns the size of the list
    public int getSize() {
        return size;
    }
    
    // returns a new iterator that traverses through the list
    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }
    
    // removes the first instance of a specific element from the list using a comparator
    public Node remove(T targetData, Comparator<T> comparator) {
        Node current = head;
        while (current != null) {
            if (comparator.compare(current.data, targetData) == 0) {
                // removes the node
                if (current == head) {
                    retrieveFirstElement();
                } else if (current == tail) {
                    retrieveLastElement();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return current;
            }
            current = current.next;
        }
       // if no elements are found 
       return null;  
    }
    
    // removes and returns the first element
    public T retrieveFirstElement() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
        	// if list becomes empty
            tail = null;  
        }
        size--;
        return data;
    }

    // removes and returns the last element
    public T retrieveLastElement() {
        if (tail == null) return null;
        T data = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
        	// if list becomes empty
            head = null;  
        }
        size--;
        return data;
    }

    // returns a arrayList of the elements in the list
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }
}
