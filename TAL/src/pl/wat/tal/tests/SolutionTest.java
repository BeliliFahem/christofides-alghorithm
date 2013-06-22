package pl.wat.tal.tests;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.Assert;
import org.junit.Test;
import pl.wat.tal.chris.ChristofidesAlgorithm;
import pl.wat.tal.common.Algorithm;
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


        SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = new SimpleWeightedGraph<String, AdvancedWeightedEdge>(AdvancedWeightedEdge.class);
        graph.addVertex("Lodz");
        graph.addVertex("Warszawa");
        graph.addVertex("Berlin");
        graph.addVertex("Katowice");

        AdvancedWeightedEdge e1 = graph.addEdge("Warszawa", "Lodz");
        graph.setEdgeWeight(e1, 129);

        AdvancedWeightedEdge e2 = graph.addEdge("Warszawa", "Katowice");
        graph.setEdgeWeight(e2, 289);

        AdvancedWeightedEdge e3 = graph.addEdge("Warszawa", "Berlin");
        graph.setEdgeWeight(e3, 575);

        AdvancedWeightedEdge e4 = graph.addEdge("Lodz", "Katowice");
        graph.setEdgeWeight(e4, 193);

        AdvancedWeightedEdge e5 = graph.addEdge("Lodz", "Berlin");
        graph.setEdgeWeight(e5, 465);

        AdvancedWeightedEdge e7 = graph.addEdge("Katowice", "Berlin");
        graph.setEdgeWeight(e7, 520);

        //BruteForce alg = new BruteForce(graph);

        Algorithm alg = new ChristofidesAlgorithm();
        TSPResult test = alg.findSolution("Lodz", graph);

        //BruteForce test = new BruteForce(graph);

        Assert.assertEquals(solution, test);
    }
}
