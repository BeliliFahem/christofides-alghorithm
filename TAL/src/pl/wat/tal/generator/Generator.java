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
	public final static int GAUSSIAN = 0;
	public final static int POISSON = 1;
	public final static int BINOMIAL = 2;
	public final static int UNIFORM = 3;
	public final static int EXPOTENTIAL = 4;
	
	public Generator(){
		
	}
	
	/**
	 * Metoda generujaca pelny graf wazony
	 * @param vertices liczba wierzcholkow
	 * @param trueNames nazwy wierzcholkow pobierane z listy
	 * @param minWeight minimalna odleglosc miedzy wierzcholkami
	 * @param maxWeight maksymalna odleglosc miedzy wierzcholkami
	 * @param seed
	 * @param distribution rozklad generowanych odleglosci
	 * @return graf
	 */
	
	public WeightedGraph<String, DefaultWeightedEdge> generate(int vertices, boolean trueNames, int minWeight, int maxWeight, long seed, int distribution){
		WeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		graph = generateVerices(vertices, trueNames);
		
		return graph;
	}
	
	/**
	 * Metoda pomocnicza generujaca wierzcholki w grafie
	 * @param number liczba wierzcholkow
	 * @param trueNames czy nazwy wierzcholkow z listy
	 * @return graf z wierzcholkami
	 */
	
	private WeightedGraph<String, DefaultWeightedEdge> generateVerices(int number, boolean trueNames){
		WeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		if(trueNames){
			// TODO
		}
		else{
			for(int i=0; i<number; i++){
				graph.addVertex("v" + i + 1);
			}
		}
		return graph;
	}

}
