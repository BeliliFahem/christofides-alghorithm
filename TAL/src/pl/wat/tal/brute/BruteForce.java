package pl.wat.tal.brute;

import java.util.Iterator;
import java.util.LinkedList;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

/**
 * 
 * @author k37
 *
 */

public class BruteForce {
	
	public BruteForce(){
		
	}
	
	public int countRoad(WeightedGraph<String, DefaultEdge> graph, String start, String destination){
		LinkedList<String> route = createFirstRoute(graph, start, destination);
		
		return 0;
	}
	
	/**
	 * Metoda tworzy pierwsza przykladowa droge
	 * @author k37
	 * @param graph graf
	 * @param start poczatek trasy
	 * @param destination cel trasy
	 * @return lista wierzcholkow drogi
	 */
	
	private LinkedList<String> createFirstRoute(WeightedGraph<String, DefaultEdge> graph, String start, String destination){
		LinkedList<String> route = new LinkedList<String>();
		
		route.add(start);
		Iterator<String> i = new DepthFirstIterator<String, DefaultEdge>(graph);
		while(i.hasNext()){
			String vertex = i.next();
			if(!vertex.equals(start) && !vertex.equals(destination)){  // musi byc rozny od startu i konca
				route.add(vertex);  // dodanie kolejnego wierzcholka do drogi
			}
		}
		
		route.add(destination);
		route.add(start);
		
		return route;
	}
	
	/**
	 * Metoda zwraca wszystkie permutacje zbioru. Zachowuje kolejnosc pierwszego i dwoch ostatnich elementow.
	 * @author k37
	 * @param route lista z przykladowa droga
	 * @return lista wszystkich mozliwych drog
	 */
	
	private LinkedList<LinkedList<String>> permute(LinkedList<String> route){
		LinkedList<LinkedList<String>> permutations = new LinkedList<LinkedList<String>>();
		
		return permutations;
	}

}
