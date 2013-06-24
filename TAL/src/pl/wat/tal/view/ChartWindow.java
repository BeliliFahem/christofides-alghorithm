package pl.wat.tal.view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import pl.wat.tal.components.ChartWindowComponents;

public class ChartWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1800337275238686999L;
	private GridLayout layout;
	private ChartWindowComponents cwc;
	public final static int COMPLEXITY = 0;
	public final static int MEMORY = 1;
	
	public ChartWindow(int option) {
        cwc = new ChartWindowComponents(option);
        layout = new GridLayout(1, 2);
        Container c = this.getContentPane();
        c.setLayout(layout);

        c.add(cwc.getBrutePane());
        c.add(cwc.getChrisPane());

        setTitle("Projekt TAL");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //pack();
        setSize(640, 480);
        setLocationRelativeTo(null);
    }

}
