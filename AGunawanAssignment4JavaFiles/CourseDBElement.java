
public class CourseDBElement implements Comparable<CourseDBElement> {
    private String ID;
    private int crn;
    private int credits;
    private String roomNum;
    private String instructor;

    // default constructor
    public CourseDBElement() {
        this.ID = "";
        this.crn = 0;
        this.credits = 0;
        this.roomNum = "";
        this.instructor = "";
    }

    // parameterized constructor
    public CourseDBElement(String courseID, int crn, int credits, String roomNum, String instructor) {
        this.ID = courseID;
        this.crn = crn;
        this.credits = credits;
        this.roomNum = roomNum;
        this.instructor = instructor;
    }

    // getters and setters
    
    public String getID() {
        return ID;
    }
    
    public void setCourseID(String courseID) {
        this.ID = courseID;
    }

    public int getCRN() {
        return crn;
    }

    public void setCRN(int crn) {
        this.crn = crn;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    // override compareTo method for sorting and comparing
    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn);
    }

    // override hash code based on CRN
    @Override
    public int hashCode() {
        return String.valueOf(crn).hashCode();
    }

    // override equals method to compare two CourseDBElements
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseDBElement other = (CourseDBElement) obj;
        return this.crn == other.crn;
    }

    // override toString method for displaying course information
    @Override
    public String toString() {
        return String.format(
            "\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s",
            ID, crn, credits, instructor, roomNum
        );
    }


}

