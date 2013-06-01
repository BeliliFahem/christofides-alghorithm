package pl.wat.tal.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.BinomialGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.ExponentialGenerator;
import org.uncommons.maths.random.GaussianGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.PoissonGenerator;

import pl.wat.tal.common.AdvancedWeightedEdge;

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
	 * @param distribution rozklad generowanych odleglosci
	 * @param mean
	 * @param standardDeviation odchylenie standardowe (do rozkladu gaussa)
	 * @param rate lambda (do rozkladu expotential)
	 * @param probability prawdopodobienstwo otrzymania liczby (do rozkladu binominal, musi byc w przedziale 0 do 1)
	 * @return graf
	 */
	
	public WeightedGraph<String, AdvancedWeightedEdge> generate(int vertices, boolean trueNames, int minWeight, int maxWeight, int distribution, double mean, double standardDeviation, double rate, double probability){
		WeightedGraph<String, AdvancedWeightedEdge> graph;
		
		graph = generateVerices(vertices, trueNames);  // wygenerowanie krawedzi
		
		generateEdges(minWeight, maxWeight, distribution, mean, standardDeviation, rate, probability, graph);  // wygenerowanie krawedzi
		
		return graph;
	}
	
	/**
	 * Metoda pomocnicza generujaca wierzcholki w grafie
	 * @param number liczba wierzcholkow
	 * @param trueNames czy nazwy wierzcholkow z listy
	 * @return graf z wierzcholkami
	 */
	
	private WeightedGraph<String, AdvancedWeightedEdge> generateVerices(int number, boolean trueNames){
		WeightedGraph<String, AdvancedWeightedEdge> graph = new SimpleWeightedGraph<String, AdvancedWeightedEdge>(AdvancedWeightedEdge.class);
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
	 * @param distribution rozklad generowanych odleglosci
	 * @param mean
	 * @param standardDeviation odchylenie standardowe (do rozkladu gaussa)
	 * @param rate lambda (do rozkladu expotential)
	 * @param probability prawdopodobienstwo otrzymania liczby (do rozkladu binominal, musi byc w przedziale 0 do 1)
	 * @param graph graf
	 */
	
	private void generateEdges(int minWeight, int maxWeight, int distribution, double mean, double standardDeviation, double rate, double probability, WeightedGraph<String, AdvancedWeightedEdge> graph){
		NumberGenerator<? extends Number> generator;
		Random random = new MersenneTwisterRNG();
		
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
			generator = new GaussianGenerator(0.0,1.0, random);
			System.out.println("Wrong number passed as distribution! Distribution = " + distribution);  // jesli bedzie interfejs to na JOptionPane zamienic
			System.exit(1);
			break;
		}
		
		for(int i=0; i<verticesNames.size(); i++){
			for(int j=i+1; j<verticesNames.size(); j++){
				double weight = 0.0;
				AdvancedWeightedEdge edge = graph.addEdge(verticesNames.get(i), verticesNames.get(j));  // dodanie krawedzi
				
				switch (distribution) {
				case EXPOTENTIAL:
					weight = Math.round(minWeight + (Double) generator.nextValue() * (maxWeight - minWeight + 1));
					break;
					
				case GAUSSIAN:
					double temp = (Double) generator.nextValue();
					temp = Math.abs(temp);
					weight = Math.round(minWeight + temp % (maxWeight - minWeight + 1));
					break;
					
				case UNIFORM:
					weight = (double) ((Integer) generator.nextValue());
					break;

				default:
					weight = (double) (minWeight + (Integer) generator.nextValue() % (maxWeight - minWeight + 1));  // generowanie wagi
					break;
				}

				System.out.println("Generated value: " + weight);  // DEBUG 
				graph.setEdgeWeight(edge, weight);
			}
		}
	}

}
