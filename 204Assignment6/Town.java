/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: a town in the graph with a name
 * Due: 12/15/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */


import java.util.Objects;

public class Town implements Comparable<Town> {
    private String name;

    // initialize a town using its name
    public Town(String name) {
        this.name = name;
    }

    // copy constructor
    public Town(Town templateTown) {
        this.name = templateTown.name;
    }

    // getter for the town's name
    public String getName() {
        return name;
    }

    // override equals to compare towns by name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return name.equals(town.name);
    }

    // override hashCode to generate a hash code based on the name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // override compareTo to compare towns by name 
    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    // override toString to return the town's name
    @Override
    public String toString() {
        return name;
    }
}
