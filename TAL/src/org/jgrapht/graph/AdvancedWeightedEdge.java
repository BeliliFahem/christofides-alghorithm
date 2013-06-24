package org.jgrapht.graph;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Zwraca wagę krawędzi
     *
     * @return
     */
    public double getWeight() {
        return super.getWeight();
    }

    public void setWeight(double setWeight) {
        weight = setWeight;
    }

    public List<Object> getVertices() {
        // wrzucamy wierzcholki krawedzi do listy wierzcholkow
        List<Object> verticesOfEdge = new LinkedList<Object>();
        verticesOfEdge.add(getSource());
        verticesOfEdge.add(getTarget());

        return verticesOfEdge;
    }
}
