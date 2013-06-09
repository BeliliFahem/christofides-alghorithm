package pl.wat.tal.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import org.jgrapht.WeightedGraph;

import pl.wat.tal.common.AdvancedWeightedEdge;
import pl.wat.tal.generator.Generator;
import pl.wat.tal.misc.DoubleFieldFilter;
import pl.wat.tal.misc.IntegerFieldFilter;
import pl.wat.tal.misc.ProbabilityFieldFilter;

/**
 * 
 * @author k37
 *
 */

public class GenerateWindowComponents implements ActionListener {
	private JFrame operatingWindow;
	private StartWindowComponents swc;
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
	private BoxLayout bottomLayout;
	private Generator generator;
	
	public GenerateWindowComponents(JFrame operatingWindow, StartWindowComponents swc){
		generator = new Generator();
		this.operatingWindow = operatingWindow;
		this.swc = swc;
		initCentralPanel();
		initBottomPanel();
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
		initComboBox();
	}
	
	protected void initBottomPanel(){
		bottomPane = new JPanel();
		bottomLayout = new BoxLayout(bottomPane, BoxLayout.LINE_AXIS);
		
		generate = new JButton("Generuj");
		cancel = new JButton("Anuluj");
		generate.addActionListener(this);
		cancel.addActionListener(this);
		
		bottomPane.setLayout(bottomLayout);
		bottomPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // oddzielenie od poprzedniego panelu
		bottomPane.add(generate);
		bottomPane.add(Box.createHorizontalGlue());
		bottomPane.add(cancel);
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
	
	protected void initComboBox(){
		distribution.addItem("Rozk³ad Gaussa");
		distribution.addItem("Rozk³ad Poissona");
		distribution.addItem("Rozk³ad dwumianowy");
		distribution.addItem("Rozk³ad jednostajny dyskretny");
		distribution.addItem("Rozk³ad wyk³adniczny");
		
		distribution.addActionListener(this);
		
		distribution.setSelectedIndex(0);
	}

	public JPanel getCentralPane() {
		return centralPane;
	}

	public JPanel getBottomPane() {
		return bottomPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == distribution){
			comboBoxListener();  // wywolanie listenera comboboxu
		} else 
			if(source == generate){
				WeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(Integer.parseInt(vertices.getText()), trueNames.isSelected(), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), distribution.getSelectedIndex(), Double.parseDouble(mean.getText()), Double.parseDouble(deviation.getText()), Double.parseDouble(rate.getText()), Double.parseDouble(prob.getText()));
				swc.setGraph(graph);  // przekazanie grafu
				operatingWindow.dispose();
			}
			else
				if(source == cancel){
					operatingWindow.dispose();  // usuniecie okna
				}
	}
	
	protected void comboBoxListener(){
		int index = distribution.getSelectedIndex();
		
		switch (index) {
		case 0:
			mean.setEditable(true);
			deviation.setEditable(true);
			rate.setEditable(false);
			prob.setEditable(false);
			break;
			
		case 1:
			mean.setEditable(true);
			deviation.setEditable(false);
			rate.setEditable(false);
			prob.setEditable(false);
			break;
			
		case 2:
			mean.setEditable(false);
			deviation.setEditable(false);
			rate.setEditable(false);
			prob.setEditable(true);
			break;
			
		case 3:
			mean.setEditable(false);
			deviation.setEditable(false);
			rate.setEditable(false);
			prob.setEditable(false);
			break;
			
		case 4:
			mean.setEditable(false);
			deviation.setEditable(false);
			rate.setEditable(true);
			prob.setEditable(false);
			break;

		default:
			System.out.println("ERROR in distribution ComboBox");
			break;
		}
	}

}
