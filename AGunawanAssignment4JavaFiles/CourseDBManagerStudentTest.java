import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManagerStudentTest {
    private CourseDBManagerInterface dataMgr;

    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();
    }

    @After
    public void tearDown() throws Exception {
        dataMgr = null;
    }

    @Test
    public void testAdd() {
        try {
            dataMgr.add("CMSC201", 12345, 3, "CS101", "John Smith");
            dataMgr.add("CMSC202", 67890, 4, "CS102", "Jane Johnson");
        } catch (Exception e) {
            fail("exception: " + e);
        }
    }

    @Test
    public void testGet() {
        dataMgr.add("CMSC201", 12345, 3, "CS101", "John Smith");

        assertNotNull("valid CRN", dataMgr.get(12345));
        assertEquals("CS101", dataMgr.get(12345).getRoomNum());

        assertNull("invalid CRN", dataMgr.get(99999)); 
    }

    @Test
    public void testShowAll() {
        dataMgr.add("CMSC201", 12345, 3, "CS101", "John Smith");
        dataMgr.add("CMSC202", 67890, 4, "CS102", "Jane Johnson");

        ArrayList<String> list = dataMgr.showAll();
        assertEquals("\nCourse:CMSC202 CRN:67890 Credits:4 Instructor:Jane Johnson Room:CS102", list.get(0));
        assertEquals("\nCourse:CMSC201 CRN:12345 Credits:3 Instructor:John Smith Room:CS101", list.get(1));
    }

    @Test
    public void testReadFile() {
        try {
            File inputFile = new File("TestInput.txt");
            PrintWriter writer = new PrintWriter(inputFile);
            writer.println("CMSC201 12345 3 CS101 John Smith");
            writer.println("CMSC202 67890 4 CS102 Jane Johnson");
            writer.close();

            dataMgr.readFile(inputFile);

            assertEquals("CMSC201", dataMgr.get(12345).getID());
            assertEquals("CMSC202", dataMgr.get(67890).getID());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException");
        } catch (IOException e) {
            fail("IOException: " + e.getMessage());
        } catch (Exception e) {
            fail("exception: " + e.getMessage());
        }
    }
}
