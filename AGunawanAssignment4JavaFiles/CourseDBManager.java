import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {

    private CourseDBStructure cds = new CourseDBStructure(20); // default hash table size

    // creates a CourseDBElement and adds it to the hash table
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement cde = new CourseDBElement(id, crn, credits, roomNum, instructor);
        cds.add(cde);
    }

    // gets a CourseDBElement by its CRN from the hash table

    @Override
    public CourseDBElement get(int crn) {
        try {
            return cds.get(crn);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // reads course information from a file and adds it to the database
    @Override
    public void readFile(File input) throws FileNotFoundException {
        Scanner in = new Scanner(input);

        // reads file line by line
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] parts = line.split(" ", 5); 
            if (parts.length != 5) {
                continue; 
            }
            try {
                String id = parts[0];
                int crn = Integer.parseInt(parts[1]);
                int credits = Integer.parseInt(parts[2]);
                String roomNum = parts[3];
                String instructor = parts[4];
                // adds course to the database
                add(id, crn, credits, roomNum, instructor);
            } catch (NumberFormatException e) {
                // if CRN or credits are not valid then skip line
                continue;
            }
        }

        in.close();
    }


    // returns a list of all courses in the database as string
    @Override
    public ArrayList<String> showAll() {
        return cds.showAll();
    }
}
