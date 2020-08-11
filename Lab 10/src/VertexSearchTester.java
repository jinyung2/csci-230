/*  Java Class: VertexSearchTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 14, 2018
    Description: Testing driver for Vertex Search methods for Depth First Search (DFS) and Breadth First Search (BFS)

    I certify that the code below is my own work.

	Exception(s): The code for graph set up and BFS DFS were from the book.

*/

import java.util.*;

public class VertexSearchTester {
    public static void main(String[] args) {
        Graph digraph = new AdjacencyMapGraph(true);
        Vertex A = digraph.insertVertex("A");
        Vertex B = digraph.insertVertex("B");
        Vertex C = digraph.insertVertex("C");
        Vertex D = digraph.insertVertex("D");
        digraph.insertEdge(A,B,"AB");
        digraph.insertEdge(B,C,"BC");
        digraph.insertEdge(C,D,"CD");

        transitiveClosure(digraph);
        System.out.println(digraph.toString());

        ProbeHashMap<Vertex<String>, Edge<String>> map = new ProbeHashMap();
        Set<Vertex<String>> set = new HashSet<>();

        /*DFS(digraph,A,set,map);
        System.out.println(digraph.toString());
        System.out.println("DFS: Order of Vertices visited");
        while (iter.hasNext()){
            System.out.println(((Edge)iter.next()).getElement());
        }
        set.clear();*/

        BFS(digraph,A,set,map);
        PositionalList list = constructPath(digraph,A,D,map);
        System.out.println(list.toString());
        /*iter = list.iterator();
        System.out.println("\nBFS: Order of Vertices visited");
        while (iter.hasNext()){
            System.out.println(((Edge)iter.next()).getElement());
        }*/
    }

    public static <V,E> void DFS(Graph<V,E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>,Edge<E>> forest){
        known.add(u);
        for (Edge<E> e : g.outgoingEdges(u)){
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
    }

    public static <V,E> void transitiveClosure(Graph<V,E> g) {
        for (Vertex<V> k : g.vertices( ))
            for (Vertex<V> i : g.vertices( ))
            // verify that edge (i,k) exists in the partial closure
        if (i != k && g.getEdge(i,k) != null)
            for (Vertex<V> j : g.vertices( ))
            // verify that edge (k,j) exists in the partial closure
        if (i != j && j != k && g.getEdge(k,j) != null)
            // if (i,j) not yet included, add it to the closure
        if (g.getEdge(i,j) == null)
            g.insertEdge(i, j, null);
        }


}
