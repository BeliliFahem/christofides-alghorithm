package pl.wat.tal.main;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

/**
 *
 */
public interface Algorithm {

    /**
     * Znajduje rozwiązanie dla przykladowego grafu z podanym wierzcholkiem początkowym
     *
     * @param startVertex
     * @param graph
     * @return
     */
    public List<String> findSolution(String startVertex, SimpleWeightedGraph<String, DefaultWeightedEdge> graph);
}
