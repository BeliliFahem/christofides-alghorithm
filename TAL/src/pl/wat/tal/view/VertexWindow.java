package pl.wat.tal.view;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.components.VertexWindowComponents;

import javax.swing.*;
import java.awt.*;

public class VertexWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -5652419898761931333L;
    private BorderLayout layout;
    private VertexWindowComponents vwc;

    public VertexWindow(JFrame parentWindow, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph, int algorithm) {
        vwc = new VertexWindowComponents(this, graph, parentWindow, algorithm);
        layout = new BorderLayout();
        Container c = this.getContentPane();
        c.setLayout(layout);

        c.add(vwc.getTitleLabel(), BorderLayout.PAGE_START);
        c.add(vwc.getVertices(), BorderLayout.CENTER);
        c.add(vwc.getOk(), BorderLayout.PAGE_END);

        setTitle("Projekt TAL");
        setVisible(true);
        setDefaultLookAndFeelDecorated(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
