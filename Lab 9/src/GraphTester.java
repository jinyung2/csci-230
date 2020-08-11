/*  Java Class: GraphTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI230
    Date: May 7, 2018
    Description: A driver class to test our simple representation of a graph.
    Uses the example outined in the book.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class GraphTester {
    public static void main(String[] args) {
        UndirGraph graph = new UndirGraph(4);
        System.out.println("Creating 4 vertices (u,v,w,z) and 4 edges (e,f,g,h) identical to the example given in book.");
        Vertex v = new Vertex("v");
        Vertex u = new Vertex("u");
        Vertex w = new Vertex("w");
        Vertex z = new Vertex("z");
        Edge e = new Edge("e");
        Edge f = new Edge("f");
        Edge g = new Edge("g");
        Edge h = new Edge("h");
        System.out.println("\nAdding the vertexes into the graph and storing the incident edges into the container.");
        graph.add(v);
        graph.add(u);
        graph.add(w);
        graph.add(z);
        graph.link(v,e);
        graph.link(v,f);
        graph.link(u,e);
        graph.link(u,g);
        graph.link(w,f);
        graph.link(w,g);
        graph.link(w,h);
        graph.link(z,h);
        System.out.println("\nComputing stored edges in matrix.");
        graph.connectMatrix(v,u,e);
        graph.connectMatrix(u,w,g);
        graph.connectMatrix(v,w,f);
        graph.connectMatrix(w,z,h);
        System.out.println("\nThe contents of the Edge List of each vertex: ");
        for (Vertex vertex : graph.getList()){
            System.out.println("Vertex " + vertex.getName() + " contains: " + vertex.toString());
        }
        System.out.println("\nThe matrix outputs: ");
        for (int i = 0; i < graph.getMatrix().length; i++){
            for (int j = 0; j < graph.getMatrix()[0].length;j++){
                if (graph.getMatrix()[i][j] != null){
                    System.out.print("[" + graph.getMatrix()[i][j].getName() + "]");
                }
                else{
                    System.out.print("[0]");
                }
            }
            System.out.println();
        }

    }
}
