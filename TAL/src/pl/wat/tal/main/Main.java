package pl.wat.tal.main;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.misc.TSPResult;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        graph.addVertex("Lodz");
        graph.addVertex("Warszawa");
        graph.addVertex("Berlin");
        graph.addVertex("Katowice");

        DefaultWeightedEdge e1 = graph.addEdge("Warszawa", "Lodz");
        graph.setEdgeWeight(e1, 129);

        DefaultWeightedEdge e2 = graph.addEdge("Warszawa", "Katowice");
        graph.setEdgeWeight(e2, 289);

        DefaultWeightedEdge e3 = graph.addEdge("Warszawa", "Berlin");
        graph.setEdgeWeight(e3, 575);

        DefaultWeightedEdge e4 = graph.addEdge("Lodz", "Katowice");
        graph.setEdgeWeight(e4, 193);

        DefaultWeightedEdge e5 = graph.addEdge("Lodz", "Berlin");
        graph.setEdgeWeight(e5, 465);

        DefaultWeightedEdge e7 = graph.addEdge("Katowice", "Berlin");
        graph.setEdgeWeight(e7, 520);

        BruteForce test = new BruteForce(graph);
        test.countRoad("Lodz", "Berlin");

    }


}
