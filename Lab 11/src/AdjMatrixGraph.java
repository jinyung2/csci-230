/*  Java Class: AdjMatrixGraph.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 23, 2018
    Description: Adjacency Matrix Graph class, utilizes modified book code from Map to Matrix.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.ArrayList;
import java.util.List;

public class AdjMatrixGraph<V,E>{

    private boolean isDirected;
    private Edge<V,E>[][] matrix;
    private List<Vertex<V>> vertices = new ArrayList<>();
    private List<Edge<V,E>> edges = new ArrayList<>();

    public AdjMatrixGraph(boolean isDirected){
        this.isDirected = isDirected;
        matrix = new Edge[1][1];
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }

    public List<Edge<V, E>> getEdges() {
        return edges;
    }

    public Edge<V, E>[][] getMatrix() {

        return matrix;
    }

    public int numVertices(){
        return vertices.size();
    }

    public int numEdges(){
        return edges.size();
    }
    public Iterable<Vertex<V>> vertices(){
        return vertices;
    }

    public Iterable<Edge<V,E>> edges(){
        return edges;
    }

    /*public int outDegree(Vertex<V> v){
        return 0;
    }

    public int inDegree(Vertex<V> v){
        return 0;
    }*/

    public Iterable<Edge<V,E>> outgoingEdges(Vertex<V> v){
        List<Edge<V,E>> temp = new ArrayList<>();
        for (int i = 0; i < matrix[0].length ; i++){
            if (matrix[vertices.indexOf(v)][i] != null){
                temp.add(matrix[vertices.indexOf(v)][i]);
            }
        }
        return temp;
    }


    public Iterable<Edge<V,E>> incomingEdges(Vertex<V> v) {
        List<Edge<V,E>> temp = new ArrayList<>();
        for (int i = 0; i < matrix[0].length ; i++){
            if (matrix[i][vertices.indexOf(v)] != null){
                temp.add(matrix[i][vertices.indexOf(v)]);
            }
        }
        return temp;
    }

    public Edge<V,E> getEdge(Vertex<V> u, Vertex<V> v) {
        Edge<V,E> edge = matrix[vertices.indexOf(u)][vertices.indexOf(v)];
        return edge;
    }


    public Vertex<V>[] endVertices(Edge<V,E> e) {
        if (edges.contains(e)){
            return e.getEndpoints();
        }
        else{
            throw new IllegalArgumentException("Edge is not in graph.");
        }
    }


    public Vertex<V> opposite(Vertex<V> v, Edge<V,E> e) {
        if (edges.contains(e)){
            Vertex<V>[] arr = e.getEndpoints();
            if (arr[0].equals(v)){
                return arr[1];
            }
            else{
                return arr[0];
            }
        }
        else{
            throw new IllegalArgumentException("Edge is not in graph.");
        }
    }


    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new Vertex<>(element);
        vertices.add(v);
        resizeMatrix();
        return v;
    }


    public Edge<V,E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u,v) == null){
            Edge<V,E> newEdge = new Edge<>(u,v,element);
            if (!isDirected){
                matrix[vertices.indexOf(v)][vertices.indexOf(u)] = newEdge;
            }
            matrix[vertices.indexOf(u)][vertices.indexOf(v)] = newEdge;
            edges.add(newEdge);
            return newEdge;
        }
        else{
            throw new IllegalArgumentException("Edge from u to v exists");
        }
    }


    public void removeVertex(Vertex<V> v) {
        if (vertices.contains(v)){
            int removeIndex = vertices.indexOf(v);
            for (int i = 0; i < matrix[removeIndex].length; i++){
                removeEdge(matrix[removeIndex][i]);
            }
            for (int j = 0; j < matrix.length; j++){
                removeEdge(matrix[j][removeIndex]);
            }
            vertices.remove(v);

            //resize for removal//
            Edge<V,E>[][] temp = new Edge[numVertices()][numVertices()];
            for (int i = 0; i < matrix[0].length;i++){
                if (i == removeIndex){
                    i++;
                }
                for (int j = 0; j < matrix.length; j++){
                    if (j == removeIndex){
                        j++;
                    }
                    temp[i][j] = matrix[i][j];
                }
            }
            matrix = temp;
        }
        else{
            throw new IllegalArgumentException("Vertex is not in graph.");
        }
    }


    public void removeEdge(Edge<V,E> e) {
        if (edges.contains(e)){
            edges.remove(e);
            e = null;
        }
        else{
            throw new IllegalArgumentException("Edge is not in graph.");
        }

    }

    private void resizeMatrix(){
        Edge<V,E>[][] temp = new Edge[numVertices()][numVertices()];
        for (int i = 0; i < matrix[0].length; i++){
            for (int j = 0; j < matrix.length; j++){
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    public void outputMatrix(){
        for (int i = 0; i < matrix[0].length; i++){
            for (int j = 0; j < matrix.length; j++){
                if (matrix[i][j] != null){
                    System.out.print("[" + matrix[i][j].getElement() + "]");
                }
                else{
                    System.out.print("[0]");
                }
            }
            System.out.println();
        }
    }
}
