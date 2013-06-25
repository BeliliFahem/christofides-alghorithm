package pl.wat.tal.main;

import pl.wat.tal.view.StartWindow;

import javax.swing.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        SimpleWeightedGraph<String, AdvancedWeightedEdge> graph = new SimpleWeightedGraph<String, AdvancedWeightedEdge>(AdvancedWeightedEdge.class);
//        graph.addVertex("Lodz");
//        graph.addVertex("Warszawa");
//        graph.addVertex("Berlin");
//        graph.addVertex("Katowice");
//
//        AdvancedWeightedEdge e1 = graph.addEdge("Warszawa", "Lodz");
//        graph.setEdgeWeight(e1, 129);
//
//        AdvancedWeightedEdge e2 = graph.addEdge("Warszawa", "Katowice");
//        graph.setEdgeWeight(e2, 289);
//
//        AdvancedWeightedEdge e3 = graph.addEdge("Warszawa", "Berlin");
//        graph.setEdgeWeight(e3, 575);
//
//        AdvancedWeightedEdge e4 = graph.addEdge("Lodz", "Katowice");
//        graph.setEdgeWeight(e4, 193);
//
//        AdvancedWeightedEdge e5 = graph.addEdge("Lodz", "Berlin");
//        graph.setEdgeWeight(e5, 465);
//
//        AdvancedWeightedEdge e7 = graph.addEdge("Katowice", "Berlin");
//        graph.setEdgeWeight(e7, 520);
//
//        BruteForce test = new BruteForce();
//        test.findSolution("Lodz", graph);
//
//        Generator gen = new Generator();
//        gen.generate(10, false, 70, 100, Generator.UNIFORM, 10, 10, 10, 0.1);
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        new StartWindow();

    }


}
