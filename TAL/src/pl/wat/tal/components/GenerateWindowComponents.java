package pl.wat.tal.components;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import pl.wat.tal.misc.DoubleFieldFilter;
import pl.wat.tal.misc.IntegerFieldFilter;
import pl.wat.tal.misc.ProbabilityFieldFilter;

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
		centralPane = new JPanel();
		
		verticesLabel = new JLabel("Liczba wierzcho³ków: ");
		trueNamesLabel = new JLabel("Pobierz nazwy wierzcho³ków z pliku: ");
		minLabel = new JLabel("Minimalna waga krawêdzi: ");
		maxLabel = new JLabel("Maksymalna waga krawêdzi: ");
		distributionLabel = new JLabel("Rozk³ad generowanych wag krawêdzi: ");
		meanLabel = new JLabel("Wartoœæ oczekiwana: ");
		deviationLabel = new JLabel("Odchylenie standardowe: ");
		rateLabel = new JLabel("Wspó³czynnik lambda: ");
		probLabel = new JLabel("Prawdopodobieñstwo zajœcia zdarzenia: ");
		
		vertices = new JTextField();
		min = new JTextField();
		max = new JTextField();
		mean = new JTextField();
		deviation = new JTextField();
		rate = new JTextField();
		prob = new JTextField();
		trueNames = new JCheckBox();
		distribution = new JComboBox<String>();
		
		// WALIDATORY
		setValidators();
	}
	
	protected void initBottomPanel(){
		
	}
	
	protected void setValidators(){
		PlainDocument doc = (PlainDocument) vertices.getDocument();
		doc.setDocumentFilter(new IntegerFieldFilter());
		
		doc = (PlainDocument) min.getDocument();
		doc.setDocumentFilter(new IntegerFieldFilter());
		
		doc = (PlainDocument) max.getDocument();
		doc.setDocumentFilter(new IntegerFieldFilter());
		
		doc = (PlainDocument) mean.getDocument();
		doc.setDocumentFilter(new DoubleFieldFilter());
		
		doc = (PlainDocument) deviation.getDocument();
		doc.setDocumentFilter(new DoubleFieldFilter());
		
		doc = (PlainDocument) rate.getDocument();
		doc.setDocumentFilter(new DoubleFieldFilter());
		
		doc = (PlainDocument) prob.getDocument();
		doc.setDocumentFilter(new ProbabilityFieldFilter());
	}

	public JPanel getCentralPane() {
		return centralPane;
	}

	public JPanel getBottomPane() {
		return bottomPane;
	}

}
