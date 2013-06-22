package pl.wat.tal.common;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AdvancedWeightedEdge;

import java.util.Iterator;
import java.util.List;

/**
 * Klasa AbstractAlgorithm
 *
 * @author Ignacy Sawicki <igesef@gmail.com>
 * @version 1.0 01.06.13
 */
public abstract class AbstractAlgorithm {

    /**
     * Metoda obliczajaca dlugosc drogi
     *
     * @param route lista zawierajaca droge
     * @param graph graf
     * @return dlugosc drogi
     * @author k37
     */

    protected long countDistance(List<String> route, WeightedGraph<String, AdvancedWeightedEdge> graph) {
        long result = 0;
        String from = new String();
        String to = new String();

        Iterator<String> i = route.iterator();
        from = i.next();  // pierwszy wierzcholek
        to = i.next();  // pierwsze odwiedzane miasto

        while (i.hasNext()) {
            result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // dodanie wagi obecnej krawedzi
            from = to;  // zamiana koncowego na poczatkowy
            to = i.next();  // pobranie kolejnego wierzcholka
        }

        result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // ostatni wierzcholek poza petla
        return result;
    }
}
