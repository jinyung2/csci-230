/*  Java Class: Edge.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 23, 2018
    Description: Class that holds the Edge object.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class Edge<V,E> {

    private E element;
    private Vertex<V>[] endpoints;

    public Edge(Vertex<V> u, Vertex<V> v, E elem) {
        element = elem;
        endpoints = (Vertex<V>[]) new Vertex[]{u,v};
    }

    public E getElement() { return element; }

    public Vertex<V>[] getEndpoints(){
        return endpoints;
    }
}