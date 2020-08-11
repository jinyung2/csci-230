/*  Java Class: AdjMatrixGraph.java
    Author: Jin Choi
    Class: CSCI 230
    Date: June 4, 2018
    Description: Adjacency Matrix Graph class, utilizes modified book code from Map to Matrix.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.ArrayList;
import java.util.List;

public class AdjMatrixGraph<V,E>{

    private boolean isDirected;
    private Edge<E>[][] matrix;
    private List<Vertex<V>> vertices = new ArrayList<>();
    private List<Edge<E>> edges = new ArrayList<>();

    public AdjMatrixGraph(boolean isDirected){
        this.isDirected = isDirected;
        matrix = new Edge[1][1];
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public Edge<E>[][] getMatrix() {

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

    public Iterable<Edge<E>> edges(){
        return edges;
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v){
        List<Edge<E>> temp = new ArrayList<>();
        for (int i = 0; i < matrix[0].length ; i++){
            if (matrix[vertices.indexOf(v)][i] != null){
                temp.add(matrix[vertices.indexOf(v)][i]);
            }
        }
        return temp;
    }


    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        List<Edge<E>> temp = new ArrayList<>();
        for (int i = 0; i < matrix[0].length ; i++){
            if (matrix[i][vertices.indexOf(v)] != null){
                temp.add(matrix[i][vertices.indexOf(v)]);
            }
        }
        return temp;
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        int i = vertices.indexOf(u);
        int j = vertices.indexOf(v);
        Edge<E> edge = matrix[i][j];
        return edge;
    }


    public Vertex<V>[] endVertices(Edge<E> e) {
        if (edges.contains(e)){
            return ((InnerEdge<E>)e).getEndpoints();
        }
        else{
            throw new IllegalArgumentException("Edge is not in graph.");
        }
    }


    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        if (edges.contains(e)){
            Vertex<V>[] arr = ((InnerEdge<E>)e).getEndpoints();
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
        InnerVertex<V> v = new InnerVertex<>(element);
        vertices.add(v);
        resizeMatrix();
        return v;
    }


    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u,v) != null){
            removeEdge(getEdge(u,v));
            matrix[vertices.indexOf(u)][vertices.indexOf(v)] = null;
        }
        InnerEdge<E> newEdge = new InnerEdge<>(u,v,element);
        if (!isDirected){
            matrix[vertices.indexOf(v)][vertices.indexOf(u)] = newEdge;
        }
        matrix[vertices.indexOf(u)][vertices.indexOf(v)] = newEdge;
        edges.add(newEdge);
        return newEdge;
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
            Edge<E>[][] temp = new Edge[numVertices()][numVertices()];
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


    public void removeEdge(Edge<E> e) {
        if (edges.contains(e)){
            edges.remove(e);
            for (int i = 0; i < matrix[0].length;i++){
                for (int j = 0; j < matrix.length;j++){
                    if (matrix[i][j] == e){
                        matrix[i][j] = null;
                    }
                }
            }

        }
        else{
            throw new IllegalArgumentException("Edge is not in graph.");
        }

    }

    private void resizeMatrix(){
        Edge<E>[][] temp = new InnerEdge[numVertices()][numVertices()];
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
                    if (String.format("%.2f", matrix[i][j].getElement()).length() == 6){
                        System.out.printf("[%.2f]", (Double)matrix[i][j].getElement());
                    }
                    else{
                        System.out.printf("[ %.2f]", (Double)matrix[i][j].getElement());
                    }
                }
                else{
                    System.out.print("[ 0.00 ]");
                }
            }
            System.out.println();
        }
    }

    private class InnerEdge<E> implements Edge<E>{

        private E element;
        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u,v};
        }

        public E getElement() { return element; }
        public void setElement(E e) {element = e;}

        public Vertex<V>[] getEndpoints(){
            return endpoints;
        }
    }


    private class InnerVertex<V> implements Vertex<V>{

        private V element;
        private int minDistance;

        public InnerVertex(V elem) {
            element = elem;
            minDistance = 0;
        }

        public V getElement() { return element; }

        public int getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(int minDistance) {
            this.minDistance = minDistance;
        }
    }

}
