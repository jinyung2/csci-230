/*  Java Class: Vertex.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 23, 2018
    Description: Class that represents a Vertex object.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class Vertex<V> {

    private V element;

    public Vertex(V elem) {
        element = elem;
    }

    public V getElement() { return element; }
}
