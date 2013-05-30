package pl.wat.tal.main;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ignacy
 * Date: 30.05.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class Solution {
    /**
     * @param graph
     * @return
     */
    public List<String> getSolution(UndirectedGraph<String, DefaultEdge> graph) {
        List<String> solution = new LinkedList<String>();

        solution.add("Lodz");
        solution.add("Warszawa");
        solution.add("Katowice");
        solution.add("Berlin");
        solution.add("Lodz");

        return solution;
    }
}
