package pl.wat.tal.view;

import pl.wat.tal.components.ChartWindowComponents;

import javax.swing.*;
import java.awt.*;

public class ChartWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -1800337275238686999L;
    private GridLayout layout;
    private ChartWindowComponents cwc;
    public final static int COMPLEXITY = 0;
    public final static int COMPLEXITY_CHRIST = 3;
    public final static int MEMORY = 1;
    public final static int MEMORY_CHRIST_SMALL = 4;
    public final static int MEMORY_CHRIST = 5;
    public final static int QUALITY = 2;
    public final static int QUALITY_CHRIST = 6;

    public ChartWindow(int option) {
        cwc = new ChartWindowComponents(option);
        layout = new GridLayout(1, 2);
        Container c = this.getContentPane();
        c.setLayout(layout);
        if (option != COMPLEXITY_CHRIST && option != MEMORY_CHRIST && option != MEMORY && option !=
                MEMORY_CHRIST_SMALL && option != QUALITY) {
            c.add(cwc.getBrutePane());
            c.add(cwc.getChrisPane());
        } else if (option == MEMORY) {
            c.add(cwc.getBrutePane());
        } else {
            c.add(cwc.getChrisPane());
        }

        setTitle("Projekt TAL");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //pack();
        setSize(640, 480);
        setLocationRelativeTo(null);
    }

}
