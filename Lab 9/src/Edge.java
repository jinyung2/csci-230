/*  Java Class: Edge.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI230
    Date: May 7, 2018
    Description: A class that holds the Edge data type.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.LinkedList;

public class Edge {

    private LinkedList<Vertex> incident;
    private String name;

    public Edge(String e) {
        incident = new LinkedList<>();
        this.name = e;
    }

    public void add(Vertex v){
        incident.add(v);
    }

    public String getName() {
        return name;
    }
}
