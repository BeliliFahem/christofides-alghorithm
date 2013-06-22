package pl.wat.tal.view;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.components.ResultWindowComponents;

import javax.swing.*;
import java.awt.*;

public class ResultWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 5694842464899873963L;
    private BorderLayout layout;
    private ResultWindowComponents rwc;
    public final static int BRUTE = 0;
    public final static int CHRISTOFIDES = 1;

    public ResultWindow() {
        rwc = new ResultWindowComponents(this);
        layout = new BorderLayout();
        Container c = this.getContentPane();
        c.setLayout(layout);

        c.add(rwc.getResultsScroller(), BorderLayout.CENTER);
        c.add(rwc.getBottomPane(), BorderLayout.PAGE_END);

        setTitle("Projekt TAL");
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void count(int algorithm, String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        this.setVisible(true);
        rwc.countRoad(algorithm, startVertex, graph);
    }

}
