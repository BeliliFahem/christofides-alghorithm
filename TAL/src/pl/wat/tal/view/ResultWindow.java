package pl.wat.tal.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import pl.wat.tal.components.ResultWindowComponents;

public class ResultWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5694842464899873963L;
	private BorderLayout layout;
	private ResultWindowComponents rwc;
	
	public ResultWindow(){
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

}
