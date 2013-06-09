package pl.wat.tal.components;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author k37
 *
 */

public class GenerateWindowComponents {
	private JLabel verticesLabel;
	private JLabel trueNamesLabel;
	private JLabel minLabel;
	private JLabel maxLabel;
	private JLabel distributionLabel;
	private JLabel meanLabel;
	private JLabel deviationLabel;
	private JLabel rateLabel;
	private JLabel probLabel;
	private JTextField vertices;
	private JTextField min;
	private JTextField max;
	private JTextField mean;
	private JTextField deviation;
	private JTextField rate;
	private JTextField prob;
	private JCheckBox trueNames;
	private JComboBox<String> distribution;
	private JButton generate;
	private JButton cancel;
	private JPanel centralPane;
	private JPanel bottomPane;
	
	public GenerateWindowComponents(){
		initCentralPanel();
	}
	
	protected void initCentralPanel(){
		
	}

	public JPanel getCentralPane() {
		return centralPane;
	}

	public JPanel getBottomPane() {
		return bottomPane;
	}

}
