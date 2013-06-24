package pl.wat.tal.components;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.view.GenerateWindow;
import pl.wat.tal.view.ResultWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author k37
 */

public class StartWindowComponents implements ActionListener {
    private JLabel graphLabel;
    private JButton generateButton;
    private JButton showGraphButton;
    private JButton bruteButton;
    private JButton christofidesButton;
    private JPanel upperPane;
    private JPanel centralPane;
    private FlowLayout upperLayout;
    private GridLayout centralLayout;
    private SimpleWeightedGraph<String, AdvancedWeightedEdge> graph;
    private ResultWindow results;

    public StartWindowComponents() {
        initUpperPanel();
        initCentralPanel();
    }

    protected void initUpperPanel() {
        upperPane = new JPanel();
        upperLayout = new FlowLayout(FlowLayout.LEADING);
        graphLabel = new JLabel("Wygenerowano graf: NIE");
        graphLabel.setForeground(Color.RED);
        generateButton = new JButton("Wygeneruj graf");
        showGraphButton = new JButton("Poka� graf");

        generateButton.addActionListener(this);
        showGraphButton.addActionListener(this);

        showGraphButton.setEnabled(false);  // poczatkowo nieaktywny bo nie ma grafu

        upperPane.setLayout(upperLayout);
        upperPane.add(graphLabel);
        upperPane.add(generateButton);
        upperPane.add(showGraphButton);
    }

    protected void initCentralPanel() {
        centralPane = new JPanel();
        centralLayout = new GridLayout(2, 1, 20, 20);
        bruteButton = new JButton("Uruchom algorytm Brute Force");
        christofidesButton = new JButton("Uruchom algorytm Christofidesa");

        bruteButton.addActionListener(this);
        christofidesButton.addActionListener(this);

        bruteButton.setEnabled(false);  // nieaktywny brak grafu
        christofidesButton.setEnabled(false);  // nieaktywny brak grafu

        centralPane.setLayout(centralLayout);
        centralPane.add(bruteButton);
        centralPane.add(christofidesButton);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton source = (JButton) arg0.getSource();
        if (source == generateButton) {
            new GenerateWindow(this);
        } else if (source == showGraphButton) {
            // TODO show graph window
        } else if (source == bruteButton) {
            if (results == null) {
                results = new ResultWindow();
            }
            results.selectVertex(ResultWindow.BRUTE, graph);
        } else if (source == christofidesButton) {
            if (results == null) {
                results = new ResultWindow();
            }
            results.selectVertex(ResultWindow.CHRISTOFIDES, graph);
        }
    }

    public JPanel getUpperPane() {
        return upperPane;
    }

    public JPanel getCentralPane() {
        return centralPane;
    }

    public void setGraph(SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        this.graph = graph;
        graphLabel.setText("Wygenerowano graf: TAK");
        graphLabel.setForeground(Color.GREEN);

        showGraphButton.setEnabled(true);  // bo jest graf
        bruteButton.setEnabled(true);
        christofidesButton.setEnabled(true);
    }

}
