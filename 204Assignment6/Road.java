/*
 * Class: CMSC204  CRN 22098
 * Instructor: Khandan Monshi
 * Description: a road between two towns with a name and distance
 * Due: 12/15/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Andy Gunawan
 */

import java.util.Objects;

public class Road implements Comparable<Road> {
    private Town source;
    private Town destination;
    private int distance;
    private String name;

    // parameterized constructor
    public Road(Town source, Town destination, int distance, String name) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.name = name;
    }

    // default constructor 
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }

    // getters
    public Town getSource() {
        return source;
    }

    public Town getDestination() {
        return destination;
    }

    public int getWeight() {
        return distance;
    }

    public String getName() {
        return name;
    }

    // check if this road connects to another town
    public boolean contains(Town town) {
        return source.equals(town) || destination.equals(town);
    }

    // override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        // checks if both towns match in any order
        return (source.equals(road.source) && destination.equals(road.destination)) ||
               (source.equals(road.destination) && destination.equals(road.source));
    }

    // override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(source, destination, name);
    }

    // override compareTo
    @Override
    public int compareTo(Road other) {
        return Integer.compare(this.distance, other.distance);
    }
    
    // override toString
    @Override
    public String toString() {
        return String.format("%s via %s to %s (%d miles)", source, name, destination, distance);
    }
}
