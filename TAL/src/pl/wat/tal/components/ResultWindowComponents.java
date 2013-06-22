package pl.wat.tal.components;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.brute.BruteForce;
import pl.wat.tal.chris.ChristofidesAlgorithm;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.misc.TSPResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ResultWindowComponents implements ActionListener {
    private JFrame operatingWindow;
    private JTextArea results;
    private JScrollPane resultsScroller;
    private JButton ok;
    private JButton save;
    private JPanel centralPane;
    private JPanel bottomPane;
    private BorderLayout centralLayout;
    private GridLayout bottomLayout;
    private Algorithm algorithm;

    public ResultWindowComponents(JFrame operatingWindow) {
        this.operatingWindow = operatingWindow;
        initCentralPanel();
        initBottomPanel();
    }

    protected void initCentralPanel() {
        results = new JTextArea(200, 100);
        resultsScroller = new JScrollPane(results);
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

    public void countRoad(int algorithm, String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        results.setText(results.getText() + "\n\n" + "====================" + "\n");
        results.setText(results.getText() + "Data: " + Calendar.DAY_OF_MONTH + "." + Calendar.MONTH + 1 + "." + Calendar.YEAR + "  " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + "\n");

        if (algorithm == 0) {
            results.setText(results.getText() + "U�yto algorytmu brute force \n");
            this.algorithm = new BruteForce();
        } else if (algorithm == 1) {
            results.setText(results.getText() + "U�yto algorytmu Christofidesa \n");
            this.algorithm = new ChristofidesAlgorithm();
        }

        TSPResult result = this.algorithm.findSolution(startVertex, graph);
        results.setText(results.getText() + "Wybrany wierzcho�ek pocz�tkowy: " + startVertex + "\n");
        results.setText(results.getText() + "Wyznaczona droga: " + result.getRoute().toString() + "\n");
        results.setText(results.getText() + "Obliczona d�ugo��: " + result.getDistance() + "\n");
        results.setText(results.getText() + "====================" + "\n");
    }

    public JScrollPane getResultsScroller() {
        return resultsScroller;
    }

    public JPanel getBottomPane() {
        return bottomPane;
    }

}
