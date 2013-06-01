package pl.wat.tal.common;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Klasa AdvancedWeightedEdge
 *
 * @author Ignacy Sawicki <igesef@gmail.com>
 * @version 1.0 01.06.13
 */
public class AdvancedWeightedEdge extends DefaultWeightedEdge {

    /**
     * Zwraca wierzchołek będący źródłem krawędzi
     *
     * @return źródło krawędzi
     */
    public Object getSourceVertex() {
        return getSource();
    }

    /**
     * Zwraca wierzchołek będący celem krawędzi
     *
     * @return cel krawędzi
     */
    public Object getTargetVertex() {
        return getTarget();
    }
}
