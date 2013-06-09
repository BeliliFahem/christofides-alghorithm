package pl.wat.tal.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import pl.wat.tal.components.GenerateWindowComponents;
import pl.wat.tal.components.StartWindowComponents;

public class GenerateWindow extends JFrame {

	/**
	 * @author k37
	 */
	private static final long serialVersionUID = -5820439676343953143L;
	private BorderLayout layout;
	private GenerateWindowComponents gwc;
	
	public GenerateWindow(StartWindowComponents swc){
		gwc = new GenerateWindowComponents(this, swc);
		layout = new BorderLayout();
		Container c = this.getContentPane();
		c.setLayout(layout);
		
		c.add(gwc.getCentralPane(), BorderLayout.CENTER);
		c.add(gwc.getBottomPane(), BorderLayout.PAGE_END);
		
		setTitle("Projekt TAL");
		setVisible(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	}

}
