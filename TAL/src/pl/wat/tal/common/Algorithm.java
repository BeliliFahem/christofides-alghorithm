package pl.wat.tal.common;

import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.misc.TSPResult;

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
    public TSPResult findSolution(String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph);
}
