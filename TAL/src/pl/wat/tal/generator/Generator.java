package pl.wat.tal.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.BinomialGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.ExponentialGenerator;
import org.uncommons.maths.random.GaussianGenerator;
import org.uncommons.maths.random.PoissonGenerator;

/**
 * 
 * @author k37
 *
 */

public class Generator {
	private List<String> verticesNames;
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
	 * @param mean
	 * @param standardDeviation odchylenie standardowe (do rozkladu gaussa)
	 * @param rate lambda (do rozkladu expotential)
	 * @param probability prawdopodobienstwo otrzymania liczby (do rozkladu binominal)
	 * @return graf
	 */
	
	public WeightedGraph<String, DefaultWeightedEdge> generate(int vertices, boolean trueNames, int minWeight, int maxWeight, long seed, int distribution, double mean, double standardDeviation, double rate, double probability){
		WeightedGraph<String, DefaultWeightedEdge> graph;
		
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
		verticesNames = new LinkedList<String>();
		
		if(trueNames){
			// TODO
		}
		else{
			for(int i=0; i<number; i++){
				String name = "v" + i + 1;
				graph.addVertex(name);
				verticesNames.add(name);
			}
		}
		return graph;
	}
	
	/**
	 * Metoda pomocnicza generujaca krawedzie wraz z ich wagami
	 * @param minWeight minimalna odleglosc miedzy wierzcholkami
	 * @param maxWeight maksymalna odleglosc miedzy wierzcholkami
	 * @param seed
	 * @param distribution rozklad generowanych odleglosci
	 * @param mean
	 * @param standardDeviation odchylenie standardowe (do rozkladu gaussa)
	 * @param rate lambda (do rozkladu expotential)
	 * @param probability prawdopodobienstwo otrzymania liczby (do rozkladu binominal)
	 */
	
	private void generateEdges(int minWeight, int maxWeight, long seed, int distribution, double mean, double standardDeviation, double rate, double probability){
		NumberGenerator<?> generator;
		Random random = new Random(seed);
		
		switch (distribution) {
		case GAUSSIAN:
			generator = new GaussianGenerator (mean, standardDeviation, random);
			break;
			
		case POISSON:
			generator = new PoissonGenerator(mean, random);
			break;
			
		case BINOMIAL:
			generator = new BinomialGenerator(maxWeight, probability, random);
			break;
			
		case UNIFORM:
			generator = new DiscreteUniformGenerator(minWeight, maxWeight, random);
			break;
			
		case EXPOTENTIAL:
			generator = new ExponentialGenerator(rate, random);
			break;

		default:
			System.out.println("Wrong number passed as distribution! Distribution = " + distribution);
			System.exit(1);
			break;
		}
	}

}
