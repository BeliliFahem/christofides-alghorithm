package pl.wat.tal.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import pl.wat.tal.components.StartWindowComponents;

public class StartWindow extends JFrame {

	/**
	 * @author k37
	 */
	private static final long serialVersionUID = -7481062030104359892L;
	private BorderLayout layout;
	private StartWindowComponents swc;
	
	public StartWindow(){
		swc = new StartWindowComponents();
		layout = new BorderLayout();
		Container c = this.getContentPane();
		c.setLayout(layout);
		
		c.add(swc.getUpperPane(), BorderLayout.PAGE_START);
		c.add(swc.getCentralPane(), BorderLayout.CENTER);
		
		setTitle("Projekt TAL");
		setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	}
}
