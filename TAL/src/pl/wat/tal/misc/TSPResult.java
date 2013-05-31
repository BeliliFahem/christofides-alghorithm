package pl.wat.tal.misc;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa pomocnicza do przechowywania wyniku dzialania algorytmow
 *
 * @author k37
 */

public class TSPResult {
    private List<String> route;
    private double distance;

    public TSPResult() {
        route = new LinkedList<String>();
        distance = 0.0;
    }

    public TSPResult(List<String> route, double distance) {
        this.route = route;
        this.distance = distance;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
