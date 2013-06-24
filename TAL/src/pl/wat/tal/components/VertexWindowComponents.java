package pl.wat.tal.components;

import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import pl.wat.tal.view.ResultWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public class VertexWindowComponents implements ActionListener {
    private JFrame operatingWindow;
    private ResultWindow parentWindow;
    private JLabel titleLabel;
    private JComboBox<String> vertices;
    private JButton ok;
    private SimpleWeightedGraph<String, AdvancedWeightedEdge> graph;
    private int algorithm;

    public VertexWindowComponents(JFrame operatingWindow, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph, JFrame parentWindow, int algorithm) {
        this.operatingWindow = operatingWindow;
        this.parentWindow = (ResultWindow) parentWindow;
        this.graph = graph;
        this.algorithm = algorithm;
        init();
    }

    protected void init() {
        titleLabel = new JLabel("Wybierz wierzchołek początkowy");

        vertices = new JComboBox<String>();
        Set<String> set = graph.vertexSet();
        Iterator<String> i = set.iterator();
        while (i.hasNext()) {
            vertices.addItem(i.next());
        }

        ok = new JButton("OK");
        ok.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton source = (JButton) arg0.getSource();
        if (source == ok) {
            parentWindow.setEnabled(true);
            parentWindow.count(algorithm, vertices.getItemAt(vertices.getSelectedIndex()), graph);
            operatingWindow.dispose();
        }
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JComboBox<String> getVertices() {
        return vertices;
    }

    public JButton getOk() {
        return ok;
    }

}
