/*  Java Class: MSTTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: May 30, 2018
    Description: Tester class would static methods for MST algorithms, Prim-Jarnik and Kruskal's.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MSTTester {
    public static void main(String[] args) {
        AdjacencyMapGraph<String, Integer> graph = new AdjacencyMapGraph<>(false);
        Vertex A = graph.insertVertex("A");
        Vertex B = graph.insertVertex("B");
        Vertex C = graph.insertVertex("C");
        Vertex D = graph.insertVertex("D");
        Vertex E = graph.insertVertex("E");
        graph.insertEdge(A, B, 2);
        graph.insertEdge(A, D, 3);
        graph.insertEdge(A, C, 4);
        graph.insertEdge(B, C, 1);
        graph.insertEdge(C, D, 8);
        graph.insertEdge(C, E, 5);
        graph.insertEdge(D, E, 6);
        PositionalList<Edge<Integer>> list = Kruskal(graph);
        PositionalList<Edge<Integer>> list2 = Prim(graph, A);
        System.out.println("Edge weights of the MST using Kruskals: " + list.toString());
        System.out.println("Edge weights of the MST using Prim-Jarnik: " + list2.toString());
        System.out.println("Source Vertex -> Destination Vertex and Weight: \n" + reconstruct(list,graph));
        System.out.println("Source Vertex -> Destination Vertex and Weight: \n" + reconstruct(list2,graph));

    }

    public static String reconstruct(PositionalList<Edge<Integer>> list, AdjacencyMapGraph graph){
        String result = "";
        String[] temp = new String[list.size()];
        Iterator iter = list.iterator();
        int counter = 0;
        while (iter.hasNext()){
            Object o = iter.next();
            Vertex[] arr = graph.endVertices((Edge)o);
            temp[counter] = arr[0].getElement() + " -> " + arr[1].getElement() + ", Weight: " + ((Edge)o).getElement();
            counter++;
        }
        Arrays.sort(temp);
        StringBuilder sb = new StringBuilder();
        for (String s : temp){
            sb.append(s + "\n");
        }
        result = sb.toString();
        return result;
    }

    public static <V> PositionalList<Edge<Integer>> Kruskal(Graph<V, Integer> g) {
        PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();
        PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();
        Partition<Vertex<V>> forest = new Partition<>();
        Map<Vertex<V>, Position<Vertex<V>>> positions = new ProbeHashMap<>();
        for (Vertex<V> v : g.vertices())
            positions.put(v, forest.makeCluster(v));
        for (Edge<Integer> e : g.edges())
            pq.insert(e.getElement(), e);
        int size = g.numVertices();
        while (tree.size() != size - 1 && !pq.isEmpty()) {
            Entry<Integer, Edge<Integer>> entry = pq.removeMin();
            Edge<Integer> edge = entry.getValue();
            Vertex<V>[] endpoints = g.endVertices(edge);
            Position<Vertex<V>> a = forest.find(positions.get(endpoints[0]));
            Position<Vertex<V>> b = forest.find(positions.get(endpoints[1]));
            if (a != b) {
                tree.addLast(edge);
                forest.union(a, b);
            }
        }
        return tree;
    }

    public static <V> PositionalList<Edge<Integer>> Prim(Graph<V, Integer> g, Vertex<V> start) {
        PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();
        HashMap<Vertex<V>, Integer> d = new HashMap<>();
        HashMap<Vertex<V>, Edge<Integer>> connect = new HashMap<>();
        HashMap<Vertex<V>, Integer> cloud = new HashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
        HashMap<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new HashMap<>();
        for (Vertex<V> v : g.vertices()) {
            if (v == start) {
                d.put(v, 0);
            } else {
                d.put(v, Integer.MAX_VALUE);
            }
            pqTokens.put(v, pq.insert(d.get(v), v));
        }
        while(!pq.isEmpty()){
            Entry<Integer,Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u,key);
            pqTokens.remove(u);
            for (Edge<Integer> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null){
                    int wgt = e.getElement();
                    if (wgt < d.get(v)){
                        d.put(v,wgt);
                        connect.put(v,e);
                        pq.replaceKey(pqTokens.get(v),d.get(v));
                    }
                }
            }
        }
        for (Edge<Integer> e : connect.values()){
            tree.addLast(e);
        }
        return tree;
    }
}


