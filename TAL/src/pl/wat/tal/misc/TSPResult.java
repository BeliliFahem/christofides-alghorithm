package pl.wat.tal.misc;

import java.util.LinkedList;

/**
 * Klasa pomocnicza do przechowywania wyniku dzialania algorytmow
 * @author k37
 *
 */

public class TSPResult {
	private LinkedList<String> route;
	private long distance;
	
	public TSPResult(){
		route = new LinkedList<String>();
		distance = 0;
	}
	
	public TSPResult(LinkedList<String> route, long distance){
		this.route = route;
		this.distance = distance;
	}

	public LinkedList<String> getRoute() {
		return route;
	}

	public void setRoute(LinkedList<String> route) {
		this.route = route;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

}
