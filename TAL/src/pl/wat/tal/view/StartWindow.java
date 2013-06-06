package pl.wat.tal.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class StartWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7481062030104359892L;
	private BorderLayout layout;
	
	public StartWindow(){
		layout = new BorderLayout();
		this.getContentPane().setLayout(layout);
		
		setTitle("Projekt TAL");
		setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	}
}
