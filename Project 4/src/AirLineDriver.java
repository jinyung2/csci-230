/*  Java Class: AirLineDriver.java
    Author: Jin Choi
    Class: CSCI 230
    Date: June 4, 2018
    Description: The driver that runs the simulation for the airline. Contains all options and reader writer classes to take input file and output results to text file.

    I certify that the code below is my own work.

	Exception(s): N/A

*/


import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class AirLineDriver {
    public static void main(String[] args) {

        BufferedReader reader = null;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/john/Documents/CSCI230/P4AirportsRev1.txt"))){
            FileReader airports = new FileReader("/home/john/Documents/CSCI230/P4Airports.txt");
            FileReader flights = new FileReader("/home/john/Documents/CSCI230/P4Flights.txt");
            GraphAlgorithms<FlightPair<String,String>,Double> gAlgo = new GraphAlgorithms<>();
            reader = new BufferedReader(airports);
            AdjMatrixGraph<FlightPair<String,String>,Double> graph = new AdjMatrixGraph<>(true);
            Stream<String> stream = reader.lines();
            Object[] arr = stream.toArray();
            for (Object o : arr){
                String[] temp = o.toString().split(" ");
                String index = temp[1];
                String flightCode = temp[3];
                String city = temp[7];
                if (temp.length == 9){
                    city = city + " " + temp[8];
                }
                graph.insertVertex(new FlightPair<>(flightCode,city));
            }
            reader.close();
            reader = new BufferedReader(flights);
            stream = reader.lines();
            arr = stream.toArray();
            for (Object o : arr){
                String[] temp = o.toString().split(" ");
                Integer src = Integer.parseInt(temp[1]);
                Integer dest = Integer.parseInt(temp[4]);
                Double cost = null;
                if (temp[8].equals("")){
                    cost = new Double(temp[9]);
                }
                else{
                    cost = new Double(temp[8]);
                }
                Vertex<FlightPair<String,String>> srcV = graph.getVertices().get(src);
                Vertex<FlightPair<String,String>> destV = graph.getVertices().get(dest);
                graph.insertEdge(srcV, destV, cost);
            }



            int i = 0;
            for (Vertex<FlightPair<String,String>> v : graph.getVertices()){
                System.out.println(i++ + " " + v.getElement().getA() + " " + v.getElement().getB());
            }
            for (Edge<Double> e : graph.getEdges()){
                Vertex<FlightPair<String,String>>[] vert = graph.endVertices(e);
                System.out.print("Source: " + vert[0].getElement().getB() + "   Destination: " + vert[1].getElement().getB() + "\nCost: $");
                System.out.printf("%.2f\n",e.getElement());
            }

            System.out.println();
            graph.outputMatrix();
            System.out.println();

            boolean looping = true;
            String finalAirports = "";
            String finalFlights = "";
            while(looping){
                Scanner in = new Scanner(System.in);
                System.out.println("Airport and Flight Information:\n1. Display airport information\n2. Find a cheapest flight from one airport to another airport\n3. Add a flight from one airport to another airport\n4. Delete a flight from one airport to another airport\n5. Find a cheapest roundtrip from one airport to another airport\n6. Find a flight with fewest stops from one airport to another airport\n7. Find all flights from one airport to another airport\n8. Find an order to visit all airports starting from an airport\n9. Add a new airport\nQ. Exit");
                String choice = in.next();

                switch (choice){
                    case "0":
                        System.out.println("Hidden debug option selected. Displaying graph.");
                        System.out.println("Airports: ");
                        StringBuilder sb = new StringBuilder();
                        for (Vertex<FlightPair<String,String>> v : graph.getVertices()){
                            sb.append(graph.getVertices().indexOf(v)).append(" ").append(v.getElement().getA()).append(" ").append(v.getElement().getB()).append("\n");
                        }
                        finalAirports = sb.toString();
                        System.out.println(finalAirports);
                        sb.delete(0,sb.length());
                        System.out.println("\nFlights: ");
                        for (Edge<Double> e : graph.getEdges()){
                            Vertex<FlightPair<String,String>>[] vertarr = graph.endVertices(e);
                            sb.append(vertarr[0].getElement().getB()).append(" -> ").append(vertarr[1].getElement().getB()).append("  Cost: $").append(String.format("%.2f", e.getElement()) + "\n");
                        }
                        finalFlights = sb.toString();
                        System.out.println(finalFlights + "\n");
                        break;
                    case "1":
                        System.out.println("Select airport code to display info: ");
                        String code = in.next();
                        //ADD CODE FOR DISPLAYING INFO OF PARTICULAR AIRPORT VERTEX//
                        for (Vertex<FlightPair<String,String>> v : graph.vertices()){
                            if (code.equals(v.getElement().getA())){
                                System.out.println("Vertex #: " + graph.getVertices().indexOf(v) + "  City: " + v.getElement().getB() + "\nOutgoing Flights: \n");
                                for(Edge<Double> e : graph.outgoingEdges(v)){
                                    Vertex<FlightPair<String,String>> u = graph.opposite(v,e);
                                    System.out.println("Code: " + u.getElement().getA() + "  City: " + u.getElement().getB() + "  Cost: $" + String.format("%.2f", e.getElement()) + "\n");
                                }
                                break;
                            }
                        }
                        break;

                    case "2": {
                        System.out.println("Cheapest flight from source to destination. ");
                        System.out.println("Input source airport code: ");
                        String srcCheap = in.next();
                        System.out.println("Input destination airport code: ");
                        String destCheap = in.next();
                        Vertex<FlightPair<String,String>> srcV = null;
                        Vertex<FlightPair<String,String>> destV = null;
                        srcV = find(srcCheap,graph);
                        destV = find(destCheap,graph);

                        ProbeHashMap<Vertex<FlightPair<String,String>>,Double> map = gAlgo.dijkstra(graph,srcV);
                        for (Entry<Vertex<FlightPair<String,String>>, Double> e : map.entrySet()) {
                            if (destV == e.getKey()){
                                System.out.println("Cost from source to " + e.getKey().getElement().getA() + ": $" + String.format("%.2f\n", e.getValue()));
                            }
                        }
                        System.out.println("Path From Source to Destination: ");
                        ProbeHashMap<Vertex<FlightPair<String,String>>, Edge<Double>> forest = new ProbeHashMap<>();
                        Set<Vertex<FlightPair<String,String>>> set = new HashSet<>();

                        gAlgo.DFS(graph,srcV,set,forest);
                        PositionalList<Edge<Double>> list = gAlgo.constructPath(graph,srcV,destV,forest);
                        System.out.println(gAlgo.reconstruct(list,graph));
                        //ADD CODE TO FIND CHEAPEST FLIGHT//
                    }
                    break;
                    case "3": {
                        System.out.println("Add a flight from source to destination. ");
                        System.out.println("Input source airport code: ");
                        String srcAdd = in.next();
                        System.out.println("Input destination airport code: ");
                        String destAdd = in.next();
                        System.out.println("Input cost (two decimals): ");
                        Double cost = in.nextDouble();
                        Vertex vSrcAdd = find(srcAdd,graph);
                        Vertex vDestAdd = find(destAdd,graph);
                        graph.insertEdge(vSrcAdd,vDestAdd,cost);
                        System.out.println("Added Flight from " + srcAdd + " to "+ destAdd + " at cost $" + String.format("%.2f\n",cost));
                        //Add Flight (Edge) from one airport to another//
                    }
                    break;
                    case "4": {
                        System.out.println("Delete a flight from source to destination. ");
                        System.out.println("Input source airport code: ");
                        String srcDelete = in.next();
                        System.out.println("Input destination airport code: ");
                        String destDelete = in.next();
                        Vertex vSrcDelete = find(srcDelete,graph);
                        Vertex vDestDelete = find(destDelete,graph);
                        try{
                            graph.removeEdge(graph.getEdge(vSrcDelete,vDestDelete));

                        }catch(IllegalArgumentException e){
                            graph.removeEdge(graph.getEdge(vDestDelete,vSrcDelete));
                        }
                        finally {
                            System.out.println("Flight between " + srcDelete + " and " + destDelete + " has been removed. \n");
                        }
                    }
                        break;
                    case "5": {
                        System.out.println("Find cheapest roundtrip from source to destination. ");
                        System.out.println("Input source airport code: ");
                        String srcRound = in.next();
                        System.out.println("Input destination airport code: ");
                        String destRound = in.next();
                        Vertex vSrcRound = find(srcRound,graph);
                        Vertex vDestRound = find(destRound,graph);

                        try{
                            ProbeHashMap<Vertex<FlightPair<String,String>>, Edge<Double>> forest = new ProbeHashMap<>();
                            Set<Vertex<FlightPair<String,String>>> set = new HashSet<>();

                            gAlgo.DFS(graph,vSrcRound,set,forest);
                            PositionalList<Edge<Double>> list = gAlgo.constructPath(graph,vSrcRound,vDestRound,forest);
                            String cost = gAlgo.reconstruct(list,graph);
                            System.out.println(gAlgo.reconstruct(list,graph));

                            ProbeHashMap<Vertex<FlightPair<String,String>>,Double> map = gAlgo.dijkstra(graph,vSrcRound);
                            for (Entry<Vertex<FlightPair<String,String>>, Double> e : map.entrySet()) {
                                if (vDestRound == e.getKey()){
                                    System.out.println("Total Cost: $" + String.format("%.2f\n", e.getValue()));
                                }
                            }
                            ProbeHashMap<Vertex<FlightPair<String,String>>, Edge<Double>> forest2 = new ProbeHashMap<>();
                            Set<Vertex<FlightPair<String,String>>> set2 = new HashSet<>();

                            gAlgo.DFS(graph,vDestRound,set2,forest2);
                            PositionalList<Edge<Double>> list2 = gAlgo.constructPath(graph,vDestRound,vSrcRound,forest2);
                            String cost2 = gAlgo.reconstruct(list2,graph);
                            System.out.println(gAlgo.reconstruct(list2,graph));

                            ProbeHashMap<Vertex<FlightPair<String,String>>,Double> map2 = gAlgo.dijkstra(graph,vDestRound);
                            for (Entry<Vertex<FlightPair<String,String>>, Double> e : map2.entrySet()) {
                                if (vSrcRound == e.getKey()){
                                    System.out.println("Total Cost: $" + String.format("%.2f\n", e.getValue()));
                                }
                            }
                        }catch(IllegalArgumentException e){
                            System.out.println("No Connection. \n");
                        }
                    }
                    break;
                    case "6": {
                        System.out.println("Fewest stops from source to destination. ");
                        System.out.println("Input source airport code: ");
                        String srcFewest = in.next();
                        System.out.println("Input destination airport code: ");
                        String destFewest = in.next();
                        Vertex vSrcFewest = find(srcFewest,graph);
                        Vertex vDestFewest = find(destFewest,graph);
                        StringBuilder minPath = new StringBuilder();
                        gAlgo.minEdgeDijkstra(graph,vSrcFewest);
                        PositionalList<Vertex<FlightPair<String,String>>> list = gAlgo.dijkstraTree(graph,vSrcFewest,vDestFewest,gAlgo.getD());
                        for (Position<Vertex<FlightPair<String,String>>> p : list.positions()){
                            minPath.append(p.getElement().getElement().getA());
                            if (list.last() != p){
                                minPath.append(" -> ");
                            }
                        }
                        System.out.println(minPath.toString() + "\n");
                    }
                    break;
                    case "7":
                        System.out.println("Unavailable. \n");
                    break;
                    case "8":
                        System.out.println("Unavailable. \n");
                    break;
                    case "9": {
                        System.out.println("Add a new airport. ");
                        System.out.println("Enter three letter code: ");
                        String airCode = in.next();
                        System.out.println("Enter full name of airport: ");
                        String airName = in.next();
                        graph.insertVertex(new FlightPair<>(airCode,airName));
                        }
                        break;

                    case "Q":{
                        System.out.println("Exiting Program.");
                        looping = false;}
                        break;

                    case "q":{
                        System.out.println("Exiting Program.");
                        looping = false;}
                        break;

                    default:
                        System.out.println("Bad Input, Re-enter selection: \n");
                    break;
                }

            }
            writer.write(finalAirports);
            writer.close();
            try(BufferedWriter writer2 = new BufferedWriter(new FileWriter("/home/john/Documents/CSCI230/P4FlightsRev1.txt"))){
                writer2.write(finalFlights);
                writer2.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static <E> Vertex<FlightPair<String,String>> find(String s,AdjMatrixGraph<FlightPair<String,String>,E> graph){
        Vertex srcV = null;
        for (Vertex<FlightPair<String,String>> v : graph.vertices()){
            if (s.equals(v.getElement().getA())){
                srcV = v;
                break;
            }
        }
        return srcV;
    }
}
