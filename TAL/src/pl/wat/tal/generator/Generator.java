package pl.wat.tal.generator;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * 
 * @author k37
 *
 */

public class Generator {
	
	public Generator(){
		
	}
	
	/**
	 * Metoda generujaca pelny graf wazony
	 * @param vertices liczba wierzcholkow
	 * @param minWeight minimalna odleglosc miedzy wierzcholkami
	 * @param maxWeight maksymalna odleglosc miedzy wierzcholkami
	 * @return graf
	 */
	
	public WeightedGraph<String, DefaultWeightedEdge> generate(int vertices, int minWeight, int maxWeight){
		SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		return graph;
	}

}
