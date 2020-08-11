/*  Java Class: DAGTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 14, 2018
    Description: Testing driver for topological ordering on a directed acyclic graph (DAG).

    I certify that the code below is my own work.

	Exception(s): The code for graph set up and topological ordering were from the book.

*/

import java.util.Iterator;
import java.util.Stack;

public class DAGTester {
    public static void main(String[] args) {
        Graph graph = new AdjacencyMapGraph(true);
        Vertex A = graph.insertVertex("A");
        Vertex B = graph.insertVertex("B");
        Vertex C = graph.insertVertex("C");
        Vertex D = graph.insertVertex("D");
        Vertex E = graph.insertVertex("E");
        graph.insertEdge(A,B,"AB");
        graph.insertEdge(B,C,"BC");
        graph.insertEdge(C,D,"CD");
        graph.insertEdge(D,E,"DE");
        System.out.println(graph.toString());
        PositionalList list = topologicalSort(graph);
        Iterator iter = list.iterator();
        System.out.println("the topological ordering of the DAG: ");
        while (iter.hasNext()){
            System.out.print(((Vertex)iter.next()).getElement() + " ");
        }
    }

    public static <V,E> PositionalList<Vertex<V>> topologicalSort(Graph<V,E> g) {
        PositionalList<Vertex<V>> topo = new LinkedPositionalList<>( );
        Stack<Vertex<V>> ready = new Stack<>();
        Map<Vertex<V>, Integer> inCount = new ProbeHashMap<>( );
        for (Vertex<V> u : g.vertices( )) {
            inCount.put(u, g.inDegree(u)); // initialize with actual in-degree
            if (inCount.get(u) == 0) // if u has no incoming edges,
                ready.push(u); // it is free of constraints
        }
        while (!ready.isEmpty( )) {
            Vertex<V> u = ready.pop( );
            topo.addLast(u);
            for (Edge<E> e : g.outgoingEdges(u)) { // consider all outgoing neighbors of u
                Vertex<V> v = g.opposite(u, e);
                inCount.put(v, inCount.get(v) - 1); // v has one less constraint without u
                if (inCount.get(v) == 0)
                    ready.push(v);
            }
        }
        return topo;
    }
}
