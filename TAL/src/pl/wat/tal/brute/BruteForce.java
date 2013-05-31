package pl.wat.tal.brute;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import pl.wat.tal.misc.TSPResult;

/**
 * 
 * @author k37
 *
 */

public class BruteForce {
	private WeightedGraph<String, DefaultWeightedEdge> graph;
	private long bestDistance;
	private List<String> bestRoute;
	private List<List<String>> permutations = new LinkedList<List<String>>();
	
	public BruteForce(WeightedGraph<String, DefaultWeightedEdge> graph){
		this.graph = graph;
		bestDistance = 0;
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
		
		
		permute(route, 1); // znalezienie wszystkich permutacji
		
		Iterator<List<String>> i = permutations.iterator();
		
		List<String> permutation = i.next();
		bestDistance = countDistance(permutation); // pierwsza odleglosc
		bestRoute = permutation;  // pierwsza droga
		
		while(i.hasNext()){
			long distance;
			permutation = i.next();
			distance = countDistance(permutation);
			if(bestDistance > distance){
				bestDistance = distance;
				bestRoute = permutation;
			}
		}
		
		result.setDistance(bestDistance);
		result.setRoute(bestRoute);
		
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
		Iterator<String> i = new DepthFirstIterator<String, DefaultWeightedEdge>(graph);
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
	 * @param index aktualny element permutacji
	 * @param permutations lista zwrotna
	 */
	
	private void permute(List<String> route, int index){
		if(index >= route.size() - 2){  // koniec permutacji
			permutations.add(route);
			System.out.println(route.toString());  // DEBUG
		}
		else{
			// puszczenie elemntu bez permutacji
			permute(route, index+1);
			
			String element = route.get(index);
			for(int i = index + 1; i < route.size() - 2; i++){
				// zamiana elemntow na pozycjach index oraz i
				route.set(index, route.get(i));
				route.set(i, element);
				
				// rekurencja
				permute(route, index+1);
				
				// powrot do poprzedniej postaci
				route.set(i, route.get(index));
				route.set(index, element);
			}
		}
		
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
