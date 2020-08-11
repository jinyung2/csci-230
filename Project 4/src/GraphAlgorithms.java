/*  Java Class: GraphAlgorithms.java
    Author: Jin Choi
    Class: CSCI 230
    Date: June 4, 2018
    Description: Holds all the algorithms that can be performed on the graph. Most of the code is recycled from the book.

    I certify that the code below is my own work.

	Exception(s): Code provided by the book.

*/

import java.util.*;

public class GraphAlgorithms<V,E> {

    public GraphAlgorithms(){ }

    public String reconstruct(PositionalList<Edge<E>> list, AdjMatrixGraph<FlightPair<String,String>,E> graph){
        String result = "";
        String[] temp = new String[list.size()];
        Iterator<Edge<E>> iter = list.iterator();
        int counter = 0;
        while (iter.hasNext()){
            Edge<E> o = iter.next();
            Vertex<FlightPair<String,String>>[] arr = graph.endVertices(o);
            temp[counter] = arr[0].getElement().getA() + " -> " + arr[1].getElement().getA() + ", Cost: $" + String.format("%.2f",o.getElement());
            counter++;
        }
        /*Arrays.sort(temp);*/
        StringBuilder sb = new StringBuilder();
        for (String s : temp){
            sb.append(s + "\n");
        }
        result = sb.toString();
        return result;
    }

    public PositionalList<Edge<Integer>> Kruskal(AdjMatrixGraph<V, Integer> g) {
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

    public PositionalList<Edge<Double>> Prim(AdjMatrixGraph<V, Double> g, Vertex<V> start) {
        PositionalList<Edge<Double>> tree = new LinkedPositionalList<>();
        HashMap<Vertex<V>, Double> d = new HashMap<>();
        HashMap<Vertex<V>, Edge<Double>> connect = new HashMap<>();
        HashMap<Vertex<V>, Double> cloud = new HashMap<>();
        AdaptablePriorityQueue<Double, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
        HashMap<Vertex<V>, Entry<Double, Vertex<V>>> pqTokens = new HashMap<>();
        for (Vertex<V> v : g.vertices()) {
            if (v == start) {
                d.put(v, 0.0);
            } else {
                d.put(v, Double.MAX_VALUE);
            }
            pqTokens.put(v, pq.insert(d.get(v), v));
        }
        while(!pq.isEmpty()){
            Entry<Double,Vertex<V>> entry = pq.removeMin();
            double key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u,key);
            pqTokens.remove(u);
            for (Edge<Double> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null){
                    double wgt = e.getElement();
                    if (wgt < d.get(v)){
                        d.put(v,wgt);
                        connect.put(v,e);
                        pq.replaceKey(pqTokens.get(v),d.get(v));
                    }
                }
            }
        }
        for (Edge<Double> e : connect.values()){
            tree.addLast(e);
        }
        return tree;
    }

    public void DFS(AdjMatrixGraph<V,E> g, Vertex<V> u, Set<Vertex<V>> known, ProbeHashMap<Vertex<V>,Edge<E>> forest){
        known.add(u);
        for (Edge<E> e : g.outgoingEdges(u)){
            Vertex<V> v = g.opposite(u,e);
            if(!known.contains(v)){
                forest.put(v,e);
                DFS(g,v,known,forest);
            }
        }
    }


    public PositionalList<Edge<E>> constructPath(AdjMatrixGraph<V,E> g, Vertex<V> u, Vertex<V> v, ProbeHashMap<Vertex<V>,Edge<E>> forest) {
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

    public void BFS(AdjMatrixGraph<V,E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>,Edge<E>> forest){
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

    public void transitiveClosure(AdjMatrixGraph<V,E> g){
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

    private ProbeHashMap<Vertex<V>,Double> d;

    public ProbeHashMap<Vertex<V>,Double> getD(){
        return d;
    }

    public ProbeHashMap<Vertex<V>,Double> dijkstra(AdjMatrixGraph<V,Double> g, Vertex<V> src){
        ProbeHashMap<Vertex<V>, Double> d = new ProbeHashMap<>();
        ProbeHashMap<Vertex<V>, Double> cloud = new ProbeHashMap<>();
        AdaptablePriorityQueue<Double,Vertex<V>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        HashMap<Vertex<V>,Entry<Double,Vertex<V>>> pqTokens;
        pqTokens = new HashMap<>();
        for (Vertex<V> v : g.vertices()){
            if (v == src) {
                d.put(v,0.0);
            }
            else{
                d.put(v,Double.MAX_VALUE);
            }
            pqTokens.put(v,pq.insert(d.get(v),v));
        }
        while(!pq.isEmpty()){
            Entry<Double,Vertex<V>> entry = pq.removeMin();
            double key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u,key);
            pqTokens.remove(u);
            for (Edge<Double> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null){
                    double wgt = e.getElement();
                    if (d.get(u) + wgt < d.get(v)){
                        d.put(v,d.get(u) + wgt);
                        pq.replaceKey(pqTokens.get(v),d.get(v));
                    }
                }
            }
        }
        this.d = d;
        return cloud;
    }


    public PositionalList<Vertex<V>> dijkstraTree(AdjMatrixGraph<V, Double> g, Vertex<V> s, Vertex<V> dest, Map<Vertex<V>, Double> d){
        PositionalList<Vertex<V>> list = new LinkedPositionalList<>();
        Map<Vertex<V>,Edge<Double>> tree = new ProbeHashMap<>();
        list.addFirst(dest);
        Vertex curr = dest;
        while(curr != s){
            for (Edge<Double> e : g.incomingEdges(dest)){
                Vertex v = g.opposite(curr,e);
                if (v.getMinDistance() == curr.getMinDistance()-1){
                    list.addFirst(v);
                    curr = v;
                }
            }
        }
        return list;
    }

    public ProbeHashMap<Vertex<V>,Double> minEdgeDijkstra(AdjMatrixGraph<V,Double> g, Vertex<V> src){
        ProbeHashMap<Vertex<V>, Double> d = new ProbeHashMap<>();
        ProbeHashMap<Vertex<V>, Double> cloud = new ProbeHashMap<>();
        AdaptablePriorityQueue<Double,Vertex<V>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        HashMap<Vertex<V>,Entry<Double,Vertex<V>>> pqTokens;
        pqTokens = new HashMap<>();
        for (Vertex<V> v : g.vertices()){
            if (v == src) {
                d.put(v,0.0);
            }
            else{
                d.put(v,Double.MAX_VALUE);
            }
            pqTokens.put(v,pq.insert(d.get(v),v));
        }
        while(!pq.isEmpty()){
            Entry<Double,Vertex<V>> entry = pq.removeMin();
            double key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u,key);
            pqTokens.remove(u);
            for (Edge<Double> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null){
                    double wgt = 1;
                    if (d.get(u) + wgt < d.get(v)){
                        d.put(v,d.get(u) + wgt);
                        pq.replaceKey(pqTokens.get(v),d.get(v));
                        v.setMinDistance(u.getMinDistance()+1);

                    }
                }
            }
        }
        this.d = d;
        return cloud;
    }
}
