/*  Java Class: MatrixGraphTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 23, 2018
    Description: The testing driver for the matrix graph tester. Runs the transitive closure and dijkstra algorithm for shortest path computations.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.*;

public class MatrixGraphTester {

    public static void main(String[] args) {
        AdjMatrixGraph<String, Integer> graph = new AdjMatrixGraph<>(true);
        Vertex A = graph.insertVertex("A");
        Vertex B = graph.insertVertex("B");
        Vertex C = graph.insertVertex("C");
        Vertex D = graph.insertVertex("D");
        graph.insertEdge(A, B, 1);
        graph.insertEdge(B, C, 1);
        graph.insertEdge(B, D, 1);
        System.out.println("Current graph matrix: ");
        graph.outputMatrix();
        transitiveClosure(graph);
        System.out.println();
        System.out.println("After Transitive Closure: ");
        graph.outputMatrix();
        System.out.println();

        AdjMatrixGraph<String, Integer> dijkgraph = new AdjMatrixGraph<>(true);
        Vertex A1 = dijkgraph.insertVertex("A");
        Vertex B1 = dijkgraph.insertVertex("B");
        Vertex C1 = dijkgraph.insertVertex("C");
        Vertex D1 = dijkgraph.insertVertex("D");
        Vertex E1 = dijkgraph.insertVertex("E");
        dijkgraph.insertEdge(A1, B1, 2);
        dijkgraph.insertEdge(A1, C1, 1);
        dijkgraph.insertEdge(A1, D1, 5);
        dijkgraph.insertEdge(B1, E1, 8);
        dijkgraph.insertEdge(B1, C1, 3);
        dijkgraph.insertEdge(C1, D1, 9);
        dijkgraph.insertEdge(C1, E1, 4);
        dijkgraph.insertEdge(D1, E1, 7);
        System.out.println("Current content of matrix: ");
        dijkgraph.outputMatrix();
        System.out.println();
        HashMap result = shortestPathLengths(dijkgraph, A1);
        for (Object o : result.entrySet()) {
            System.out.println("Weight from source to " + ((Vertex) ((Map.Entry) o).getKey()).getElement() + ": " + ((Map.Entry) o).getValue());
            int i = dijkgraph.getVertices().indexOf(E1);
        }
    }
    public static <V,E> void transitiveClosure(AdjMatrixGraph<V,E> g){
        for (Vertex<V> k : g.vertices()){
            for (Vertex<V> i : g.vertices()){
                if (i != k && g.getEdge(i,k)!=null){
                    for (Vertex<V> j : g.vertices()){
                        if (i != j && j != k && g.getEdge(k,j) != null){
                            if (g.getEdge(i,j) == null){
                                g.insertEdge(i,j,(E) new Integer(1));
                            }
                        }
                    }
                }
            }
        }
    }

    public static <V> HashMap<Vertex<V>,Integer> shortestPathLengths(AdjMatrixGraph<V,Integer> g, Vertex<V> src){
        HashMap<Vertex<V>, Integer> d = new HashMap<>();
        HashMap<Vertex<V>, Integer> cloud = new HashMap<>();
        AdaptablePriorityQueue<Integer,Vertex<V>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        HashMap<Vertex<V>,Entry<Integer,Vertex<V>>> pqTokens;
        pqTokens = new HashMap<>();
        for (Vertex<V> v : g.vertices()){
            if (v == src) {
                d.put(v,0);
            }
            else{
                d.put(v,Integer.MAX_VALUE);
            }
            pqTokens.put(v,pq.insert(d.get(v),v));
        }
        while(!pq.isEmpty()){
            Entry<Integer,Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u,key);
            pqTokens.remove(u);
            for (Edge<V,Integer> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null){
                    int wgt = e.getElement();
                    if (d.get(u) + wgt < d.get(v)){
                        d.put(v,d.get(u) + wgt);
                        pq.replaceKey(pqTokens.get(v),d.get(v));
                    }
                }
            }
        }
        return cloud;
    }

}
