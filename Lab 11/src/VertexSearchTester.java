/*  Java Class: VertexSearchTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 14, 2018
    Description: Testing driver for Vertex Search methods for Depth First Search (DFS) and Breadth First Search (BFS)

    I certify that the code below is my own work.

	Exception(s): The code for graph set up and BFS DFS were from the book.

*/

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class VertexSearchTester {
    public static void main(String[] args) {

        }

    /*public static <V,E> void DFS(AdjMatrixGraph<V,E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>,Edge<E>> forest){
        known.add(u);
        for (Edge<V,E> e : g.outgoingEdges(u)){
            Vertex<V> v = g.opposite(u,e);
            if(!known.contains(v)){
                forest.put(v,e);
                DFS(g,v,known,forest);
            }
        }
    }

    public static <V,E> PositionalList<Edge<E>> constructPath(Graph<V,E> g, Vertex<V> u, Vertex<V> v, Map<Vertex<V>,Edge<E>> forest) {
        PositionalList<Edge<E>> path = new LinkedPositionalList<>();
        if (forest.get(v) != null) {
            Vertex<V> walk = v;
            while (walk != u) {
                Edge<E> edge = forest.get(walk);
                path.addFirst(edge);
                walk = g.opposite(walk, edge);
            }
        }
        return path;
    }

    public static <V,E> void BFS(Graph<V,E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>,Edge<E>> forest){
        PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
        known.add(s);
        level.addLast(s);
        while(!level.isEmpty()){
            PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
            for (Vertex<V> u : level){
                for (Edge<E> e : g.outgoingEdges(u)){
                    Vertex<V> v = g.opposite(u,e);
                    if (!known.contains(v)){
                        known.add(v);
                        forest.put(v,e);
                        nextLevel.addLast(v);
                    }
                }
            }
            level = nextLevel;
        }
    }*/

}
