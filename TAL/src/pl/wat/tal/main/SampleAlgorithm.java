package pl.wat.tal.main;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

import static org.jgrapht.alg.HamiltonianCycle.getApproximateOptimalForCompleteGraph;

/**
 * Created with IntelliJ IDEA.
 * User: ignacy
 * Date: 30.05.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class SampleAlgorithm implements Algorithm {

    public List<String> findSolution(String startVertex, SimpleWeightedGraph<String, DefaultWeightedEdge> graph) {

        return getApproximateOptimalForCompleteGraph(graph);
    }
}
