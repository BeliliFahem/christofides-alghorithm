package pl.wat.tal.components;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.chris.ChristofidesAlgorithm;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.generator.Generator;

public class ChartWindowComponents {
	private XYSeries bruteSeries;
	private XYSeries chrisSeries;
	private XYSeriesCollection bruteCollection;
	private XYSeriesCollection chrisCollection;
	private XYDataset bruteSet;
	private XYDataset chrisSet;
	private JFreeChart bruteChart;
	private JFreeChart chrisChart;
	private ChartPanel brutePane;
	private ChartPanel chrisPane;
	private Algorithm algorithm;
	private Generator generator;
	
	public ChartWindowComponents(int option){
		init(option);
	}
	
	protected void init(int option){
		generator = new Generator();
		bruteSeries = new XYSeries("Algorytm Brute Force");
		chrisSeries = new XYSeries("Algorytm Christofidesa");
		
		algorithmLoop(0, option);  // uzyskanie wartosci dla brute force
		algorithmLoop(1, option);  // uzyskanie wartosci dla christofidesa
		
		bruteCollection = new XYSeriesCollection(bruteSeries);
		chrisCollection = new XYSeriesCollection(chrisSeries);
		bruteSet = bruteCollection;
		chrisSet = chrisCollection;
		
		bruteChart = ChartFactory.createXYLineChart("Algorytm Brute Force", "Rozmiar problemu", "Czas wykonania", bruteSet, PlotOrientation.VERTICAL, true, true, false);
		chrisChart = ChartFactory.createXYLineChart("Algorytm Christofidesa", "Rozmiar problemu", "Czas wykonania", chrisSet, PlotOrientation.VERTICAL, true, true, false);
		
		brutePane = new ChartPanel(bruteChart);
		chrisPane = new ChartPanel(chrisChart);
	}
	
	protected void algorithmLoop(int algorithmOption, int option){
		switch (algorithmOption) {
		case 0:  // BRUTE
			algorithm = new BruteForce();
			break;
			
		case 1:  // CHRIS
			algorithm = new ChristofidesAlgorithm();
			break;

		default:
			algorithm = new BruteForce();
			System.out.println("ERROR!");
			System.exit(1);
			break;
		}
		
		switch (option) {
		case 0:
			for (int i=3; i<11; i++){
				SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(i, false, 10, 100, Generator.GAUSSIAN, 0, 1, 0, 0);
				Date start = new Date();
				algorithm.findSolution("v0", graph);
				Date end = new Date();
				long duration = end.getTime() - start.getTime();
				if(algorithmOption == 0)
					bruteSeries.add(i, duration);  // dodanie wartosci do wykresu
				else
					chrisSeries.add(i, duration);
			}
			break;

		default:
			break;
		}
		
	}

	public ChartPanel getBrutePane() {
		return brutePane;
	}

	public ChartPanel getChrisPane() {
		return chrisPane;
	}


}
