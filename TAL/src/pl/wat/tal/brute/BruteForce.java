package pl.wat.tal.brute;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import pl.wat.tal.misc.TSPResult;

/**
 * 
 * @author k37
 *
 */

public class BruteForce {
	private WeightedGraph<String, DefaultEdge> graph;
	
	public BruteForce(WeightedGraph<String, DefaultEdge> graph){
		this.graph = graph;
	}
	
	/**
	 * 
	 * @param start poczatek trasy
	 * @param destination cel trasy
	 * @return droga + obliczona odleglosc
	 */
	
	public TSPResult countRoad(String start, String destination){
		TSPResult result = new TSPResult();
		List<String> route = createFirstRoute(start, destination);
		
		return result;
	}
	
	/**
	 * Metoda tworzy pierwsza przykladowa droge
	 * @author k37
	 * @param start poczatek trasy
	 * @param destination cel trasy
	 * @return lista wierzcholkow drogi
	 */
	
	private List<String> createFirstRoute(String start, String destination){
		List<String> route = new LinkedList<String>();
		
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
	
	private List<LinkedList<String>> permute(List<String> route){
		List<LinkedList<String>> permutations = new LinkedList<LinkedList<String>>();
		
		return permutations;
	}
	
	/**
	 * Metoda obliczajaca dlugosc drogi
	 * @author k37
	 * @param route lista zawierajaca droge
	 * @return dlugosc drogi
	 */
	
	private long countDistance(List<String> route){
		long result = 0;
		String from = new String();
		String to = new String();
		
		Iterator<String> i = route.iterator();
		from = i.next();  // pierwszy wierzcholek
		to = i.next();  // pierwsze odwiedzane miasto
		
		while(i.hasNext()){
			result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // dodanie wagi obecnej krawedzi
			from = to;  // zamiana koncowego na poczatkowy
			to = i.next();  // pobranie kolejnego wierzcholka
		}
		
		result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // ostatni wierzcholek poza petla
		return result;
	}
}
