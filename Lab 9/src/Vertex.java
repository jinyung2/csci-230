/*  Java Class: Vertex.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI230
    Date: May 7, 2018
    Description: The class that holds the Vertex class, stores the edge for the Adjacency List Graph Structure.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.LinkedList;

public class Vertex {


    private LinkedList<Edge> edges;
    private String name;

    public Vertex(String name){
        edges = new LinkedList<>();
        this.name = name;

    }

    public void add(Edge e){
        edges.add(e);
    }

    public LinkedList getEdges() {
        return edges;
    }

    public String toString(){
        String result = "";
        for (Edge e : edges){
            result = result + e.getName();
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
