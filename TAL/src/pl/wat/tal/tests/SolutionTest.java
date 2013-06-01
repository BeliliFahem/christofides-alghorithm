package pl.wat.tal.tests;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.Assert;
import org.junit.Test;

import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.main.Algorithm;
import pl.wat.tal.main.SampleAlgorithm;
import pl.wat.tal.misc.TSPResult;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ignacy Sawicki
 * Date: 30.05.13
 * Time: 22:51
 */
public class SolutionTest {
    @Test
    public void testGetSolution() throws Exception {

        List<String> solution = new LinkedList<String>();

        solution.add("Lodz");
        solution.add("Warszawa");
        solution.add("Katowice");
        solution.add("Berlin");
        solution.add("Lodz");

        Algorithm alg = new SampleAlgorithm();

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

        TSPResult result = alg.findSolution("Lodz", graph);
        BruteForce test = new BruteForce(graph);

        Assert.assertEquals(solution, test.countRoad("Lodz", "Berlin").getRoute());
    }
}
