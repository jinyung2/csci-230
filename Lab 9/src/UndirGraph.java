/*  Java Class: UndirGraph.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI230
    Date: May 7, 2018
    Description: A class that creates a simple representation of a graph for adjacency list structure and matrix structure.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.LinkedList;

public class UndirGraph {

    private Edge[][] matrix;
    LinkedList<Vertex> list;

    public UndirGraph(int vertex){
        matrix = new Edge[vertex][vertex];
        list = new LinkedList<>();
    }

    public void add(Vertex v){
        list.add(v);

    }

    public void link(Vertex v, Edge e){
        v.add(e);
        e.add(v);
    }

    public void connectMatrix(Vertex v, Vertex u, Edge e){
        if (list.contains(v) && list.contains(u)){
            int n = list.indexOf(v);
            int m = list.indexOf(u);
            if (v.equals(u)){
                matrix[n][m] = null;
            }
            matrix[n][m] = e;
            matrix[m][n] = e;
        }
    }

    public Edge[][] getMatrix() {
        return matrix;
    }

    public LinkedList<Vertex> getList() {
        return list;
    }
}
