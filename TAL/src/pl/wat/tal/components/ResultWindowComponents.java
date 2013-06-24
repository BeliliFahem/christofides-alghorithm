package pl.wat.tal.components;


import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.chris.ChristofidesAlgorithm;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.misc.TSPResult;
import pl.wat.tal.view.VertexWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ResultWindowComponents implements ActionListener {
    private JFrame operatingWindow;
    private JTextArea resultsConsole;
    private JScrollPane resultsScroller;
    private JButton ok;
    private JButton save;
    private JPanel bottomPane;
    private GridLayout bottomLayout;
    private Algorithm algorithm;
    private List<TSPResult> results;

    public ResultWindowComponents(JFrame operatingWindow) {
        this.operatingWindow = operatingWindow;
        results = new LinkedList<TSPResult>();
        initCentralPanel();
        initBottomPanel();
    }

    protected void initCentralPanel() {
        resultsConsole = new JTextArea(200, 100);
        resultsScroller = new JScrollPane(resultsConsole);
    }

    protected void initBottomPanel() {
        bottomPane = new JPanel();
        bottomLayout = new GridLayout(2, 1, 20, 20);

        save = new JButton("Zapisz do pliku");
        ok = new JButton("OK");

        save.addActionListener(this);
        ok.addActionListener(this);

        bottomPane.setLayout(bottomLayout);
        bottomPane.add(save);
        bottomPane.add(ok);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == save) {
            // TODO
        } else if (source == ok) {
            operatingWindow.setVisible(false);  // nie zamykamy okna chowamy je tylko
        }

    }

    public void selectVertex(int algorithm, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        new VertexWindow(operatingWindow, graph, algorithm);
    }

    public void countRoad(int algorithm, String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        resultsConsole.setText(resultsConsole.getText() + "\n\n" + "====================" + "\n");
        resultsConsole.setText(resultsConsole.getText() + "Data: " + Calendar.DAY_OF_MONTH + "." + Calendar.MONTH + 1 + "." + Calendar.YEAR + "  " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + "\n");

        if (algorithm == 0) {
            resultsConsole.setText(resultsConsole.getText() + "Użyto algorytmu brute force \n");
            this.algorithm = new BruteForce();
        } else if (algorithm == 1) {
            resultsConsole.setText(resultsConsole.getText() + "Użyto algorytmu Christofidesa \n");
            this.algorithm = new ChristofidesAlgorithm();
        }

        TSPResult result = this.algorithm.findSolution(startVertex, graph);
        results.add(result);  // dodanie do listy poprzednich wynikow
        resultsConsole.setText(resultsConsole.getText() + "Wybrany wierzchołek początkowy: " + startVertex + "\n");
        resultsConsole.setText(resultsConsole.getText() + "Wyznaczona droga: " + result.getRoute().toString() + "\n");
        resultsConsole.setText(resultsConsole.getText() + "Obliczona długość: " + result.getDistance() + "\n");
        resultsConsole.setText(resultsConsole.getText() + "====================" + "\n");
    }

    public JScrollPane getResultsScroller() {
        return resultsScroller;
    }

    public JPanel getBottomPane() {
        return bottomPane;
    }

}
