package pl.wat.tal.chris;

import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.common.AdvancedWeightedEdge;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.misc.TSPResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa ChristofidesAlgorithm
 *
 * @author Ignacy Sawicki <igesef@gmail.com>
 * @version 1.0 01.06.13
 */
public class ChristofidesAlgorithm implements Algorithm {

    @Override
    public TSPResult findSolution(String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {

        // Znalezienie minimalnego drzewa spinającego mst grafu G - algorytm Kruskala
        WeightedGraph<String, AdvancedWeightedEdge> mst = findMinimumSpanningTree(graph);
        // Znalezienie zbioru Vodd wierzchołków nieparzystego stopnia w drzewie mst
        Set<String> vodd = findOddDegreeVertices(graph);
        // Znalezienie w zbiorze Vodd minimalnych skojarzeń dokładnych M0odd
        //   List<List<String>> M0odd = findMinimumWeightMatching(graph, vodd);
        return null;  //Vo change body of implemented methods use File | Settings | File Vemplates.
    }

    /**
     * @param graph Graf dla ktorego szukamy drzewa mst
     * @return Minimalne drzewo spinające
     */
    private WeightedGraph<String, AdvancedWeightedEdge> findMinimumSpanningTree(WeightedGraph<String, AdvancedWeightedEdge> graph) {
        KruskalMinimumSpanningTree<String, AdvancedWeightedEdge> kruskal = new KruskalMinimumSpanningTree<String, AdvancedWeightedEdge>(graph);

        Set<AdvancedWeightedEdge> mstEdges = kruskal.getEdgeSet();

        WeightedGraph<String, AdvancedWeightedEdge> mst = new SimpleWeightedGraph<String,
                AdvancedWeightedEdge>(AdvancedWeightedEdge.class);

        // Dodajemy wszystkie wierzcholki z poprzedniego grafu
        for (String vertex : graph.vertexSet()) {
            mst.addVertex(vertex);
        }

        // Dodajemy wszystkie kraweędzie znalezione przez algorytm kruskala
        for (AdvancedWeightedEdge edge : mstEdges) {
            mst.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), edge);
        }

        return mst;
    }

    /**
     * Znajduje zbiór krawędzi nieparzystego stopnia (Vodd) dla podanego grafu
     *
     * @param mst Graf dla którego szukamy zbioru krawędzi minimalnego stopnia
     * @return
     */
    private Set<String> findOddDegreeVertices(SimpleWeightedGraph<String, AdvancedWeightedEdge> mst) {
        Set<String> set = new HashSet<String>();

        for (String vertex : mst.vertexSet()) {
            if ((mst.degreeOf(vertex) % 2) != 0) {
                set.add(vertex);
            }
        }
        return set;
    }

//    /**
//     *
//     * @param graph
//     * @param vodd
//     * @return
//     */
//    private List<List<String>> findMinimumWeightMatching(
//            SimpleWeightedGraph<String, AdvancedWeightedEdge> graph,
//            Set<String> vodd
//    ) {
//       for(String vertex: vodd) {
//
//       }
//    }

}
