package pl.wat.tal.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.BinomialGenerator;
import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.chris.ChristofidesAlgorithm;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.generator.Generator;
import pl.wat.tal.misc.TSPResult;
import pl.wat.tal.view.ChartWindow;

import java.util.Random;

public class ChartWindowComponents {
    private XYSeries bruteSeries;
    private XYSeries chrisSeries;
    private XYSeries nCubedSeries;
    private XYSeriesCollection bruteCollection;
    private XYSeriesCollection chrisCollection;
    private XYSeriesCollection nCubedCollection;
    private XYDataset bruteSet;
    private XYDataset nCubedSet;
    private XYDataset chrisSet;
    private JFreeChart bruteChart;
    private JFreeChart chrisChart;
    private ChartPanel brutePane;
    private ChartPanel chrisPane;
    private Algorithm algorithm;
    private Generator generator;

    public ChartWindowComponents(int option) {
        init(option);
    }

    protected void init(int option) {
        generator = new Generator();
        bruteSeries = new XYSeries("Algorytm Brute Force");
        chrisSeries = new XYSeries("Algorytm Christofidesa");
        nCubedSeries = new XYSeries("N^2");
//        for(int i=3; i < 200; i++) {
//            nCubedSeries.add(i, i);
//        }

        if (option == ChartWindow.COMPLEXITY_CHRIST || option == ChartWindow.MEMORY_CHRIST) {
            algorithmLoop(new ChristofidesAlgorithm(), option, 150, chrisSeries);  // uzyskanie wartosci dla
            // christofidesa
        } else if (option == ChartWindow.MEMORY) {
            algorithmLoop(new BruteForce(), option, 11, bruteSeries);  // uzyskanie wartosci dla brute force
        } else if (option == ChartWindow.QUALITY) {
            algorithmQualityLoop(chrisSeries);
        } else if (option == ChartWindow.QUALITY_CHRIST) {
            algorithmChristQualityLoop(chrisSeries);
        } else {
            algorithmLoop(new BruteForce(), option, 11, bruteSeries);  // uzyskanie wartosci dla brute force
            algorithmLoop(new ChristofidesAlgorithm(), option, 11, chrisSeries);  // uzyskanie wartosci dla christofidesa
        }

        bruteCollection = new XYSeriesCollection(bruteSeries);
        chrisCollection = new XYSeriesCollection(chrisSeries);
        //chrisCollection.addSeries(nCubedSeries);
        bruteSet = bruteCollection;
        chrisSet = chrisCollection;

        String yTitle = new String();
        switch (option) {
            case 0:
                yTitle = "Czas wykonania [msec]";
                break;

            case 1:
            case 4:
            case 5:
                yTitle = "Zajęta pamięć";
                break;

            case 2:
            case 6:
                yTitle = "Jakość algorytmu";
                break;
            case 3:
                yTitle = "Czas wykonania [msec]";
                break;

            default:
                System.out.println("ERROR!");
                System.exit(1);
                break;
        }

        bruteChart = ChartFactory.createXYLineChart("Algorytm Brute Force", "Rozmiar problemu", yTitle, bruteSet,
                PlotOrientation.VERTICAL, true, true, false);
        chrisChart = ChartFactory.createXYLineChart("Algorytm Christofidesa", "Rozmiar problemu", yTitle, chrisSet,
                PlotOrientation.VERTICAL, true, true, false);
        if (option == ChartWindow.QUALITY || option == ChartWindow.QUALITY_CHRIST) {
            chrisChart.getXYPlot().getRangeAxis().setRange(0.9, 1.5);
        }
        brutePane = new ChartPanel(bruteChart);
        chrisPane = new ChartPanel(chrisChart);
    }

    protected void algorithmChristQualityLoop(XYSeries series) {
        Algorithm christ = new ChristofidesAlgorithm();
        NumberGenerator<? extends Number> generatorm;

        Random random = new Random();
        generatorm = new BinomialGenerator(10000, 0.5, random);

        int length = 0;
        for (int i = 3; i < 70; i++) {
            length = (1000 + (Integer) generatorm.nextValue() % (10000 - 1000 + 1));  // generowanie wagi
            SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = generator.generateForKnownLength(i, length);
            TSPResult christResult = christ.findSolution("v0", graph);
            double howWorse = (double) christResult.getDistance() / (double) length;
            series.add(i, howWorse);
        }
    }

    protected void algorithmLoop(Algorithm algorithm, int option, int problemSize, XYSeries series) {

        switch (option) {
            case 0:
            case 3:
                for (int i = 3; i < problemSize; i++) {
                    SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(i, false, 10, 100,
                            Generator.POISSON, 54, 1, 0, 0);
                    long start = System.currentTimeMillis();
                    algorithm.findSolution("v0", graph);
                    long end = System.currentTimeMillis();

                    long duration = end - start;
                    series.add(i, duration);
                }
                break;

            case 1:
            case 4:
            case 5:
                for (int i = 3; i < problemSize; i++) {
                    SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(i, false, 10, 100, Generator.POISSON, 34, 0, 0, 0);
                    Runtime runtime = Runtime.getRuntime();
                    algorithm.findSolution("v0", graph);
                    long memory = runtime.totalMemory() - runtime.freeMemory();
                    series.add(i, memory);
                }
                break;
            default:
                break;
        }

    }

    public void algorithmQualityLoop(XYSeries series) {
        Algorithm brute = new BruteForce();
        Algorithm christ = new ChristofidesAlgorithm();
        for (int i = 3; i < 11; i++) {
            SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(i, false, 10, 100, Generator.POISSON, 34, 0, 0, 0);
            TSPResult bruteResult = brute.findSolution("v0", graph);
            TSPResult christResult = christ.findSolution("v0", graph);
            System.out.println("Christ = " + christResult.getDistance() + " Brute = " + bruteResult.getDistance());
            double howWorse = (double) christResult.getDistance() / (double) bruteResult.getDistance();
            series.add(i, howWorse);
        }
    }

    public ChartPanel getBrutePane() {
        return brutePane;
    }

    public ChartPanel getChrisPane() {
        return chrisPane;
    }


}
