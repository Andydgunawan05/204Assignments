import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {

    protected int sizeOfHash;
    protected int elementSize;
    protected LinkedList<CourseDBElement>[] hashTable;
    private ArrayList<CourseDBElement> insertionOrder; // tracks insertion order

    // initializes hash table with the next prime 
    public CourseDBStructure(int size) {
        sizeOfHash = findNextPrime((int) (size / 1.5));
        hashTable = new LinkedList[sizeOfHash];
        insertionOrder = new ArrayList<>();
    }

    // initializes a hash table with a fixed size for testing
    public CourseDBStructure(String testing, int size) {
        sizeOfHash = size;
        hashTable = new LinkedList[sizeOfHash];
        insertionOrder = new ArrayList<>();
    }

    // adds or updates a CourseDBElement in the hash table
    @Override
    public void add(CourseDBElement element) {
        int index = Math.abs(element.hashCode()) % sizeOfHash;

        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        for (CourseDBElement existingElement : hashTable[index]) {
            if (existingElement.getCRN() == element.getCRN()) {
                existingElement.setCourseID(element.getID());
                existingElement.setCredits(element.getCredits());
                existingElement.setRoomNum(element.getRoomNum());
                existingElement.setInstructor(element.getInstructor());

                int existingIndex = insertionOrder.indexOf(existingElement);
                insertionOrder.set(existingIndex, existingElement);
                return;
            }
        }

        hashTable[index].add(element);
        insertionOrder.add(element);
        elementSize++;
    }

    // gets a CourseDBElement by CRN
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int index = Math.abs(Integer.toString(crn).hashCode()) % sizeOfHash;

        if (hashTable[index] != null) {
            for (CourseDBElement element : hashTable[index]) {
                if (element.getCRN() == crn) {
                    return element;
                }
            }
        }

        throw new IOException("Course with CRN " + crn + " not found.");
    }

    // returns the size of the hash table
    @Override
    public int getTableSize() {
        return sizeOfHash;
    }

    // returns all courses as strings in reverse order
  
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> courses = new ArrayList<>();

        for (int i = insertionOrder.size() - 1; i >= 0; i--) {
            courses.add(insertionOrder.get(i).toString());
        }

        return courses;
    }

    // finds the next prime number
    private int findNextPrime(int num) {
        while (true) {
            if (isPrime(num) && num % 4 == 3) {
                return num;
            }
            num++;
        }
    }

    // checks if a number is prime
   
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
