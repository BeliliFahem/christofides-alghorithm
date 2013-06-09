package pl.wat.tal.components;

import java.awt.Color;
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

import net.miginfocom.swing.MigLayout;

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
	private MigLayout centralLayout;
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
		centralLayout = new MigLayout("fill, wrap 2", "[left]rel[grow,fill]", "[]10[fill]");
		
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
		
		setValidators();
		initComboBox();
		
		centralPane.setLayout(centralLayout);
		centralPane.add(verticesLabel);
		centralPane.add(vertices, "wrap");
		centralPane.add(trueNamesLabel);
		centralPane.add(trueNames, "wrap");
		centralPane.add(minLabel);
		centralPane.add(min, "wrap");
		centralPane.add(maxLabel);
		centralPane.add(max, "wrap");
		centralPane.add(distributionLabel);
		centralPane.add(distribution, "wrap");
		centralPane.add(meanLabel);
		centralPane.add(mean, "wrap");
		centralPane.add(deviationLabel);
		centralPane.add(deviation, "wrap");
		centralPane.add(rateLabel);
		centralPane.add(rate, "wrap");
		centralPane.add(probLabel);
		centralPane.add(prob, "wrap");
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
				generateListener();
			}
			else
				if(source == cancel){
					operatingWindow.dispose();  // usuniecie okna
				}
	}
	
	protected void generateListener(){
		int verticesCount = 0;
		int minWeight = 0;
		int maxWeight = 0;
		double meanValue = 0.0;
		double deviationValue = 0.0;
		double rateValue = 0.0;
		double probValue = 0.0;
		
		// ilosc wierzcholkow i przedzial wag musza byc niepuste
		if(!vertices.getText().isEmpty() && !min.getText().isEmpty() && !max.getText().isEmpty()){
			verticesCount = Integer.parseInt(vertices.getText());
			minWeight = Integer.parseInt(min.getText());
			maxWeight = Integer.parseInt(max.getText());
			
			if(minWeight > maxWeight){
				min.setBackground(Color.RED);
				max.setBackground(Color.RED);
			}
			else {
				if(!mean.getText().isEmpty())
					meanValue = Double.parseDouble(mean.getText());
				if(!deviation.getText().isEmpty())
					deviationValue = Double.parseDouble(deviation.getText());
				if(!rate.getText().isEmpty())
					rateValue = Double.parseDouble(rate.getText());
				if(!prob.getText().isEmpty())
					probValue = Double.parseDouble(prob.getText());
			
				WeightedGraph<String, AdvancedWeightedEdge> graph = generator.generate(verticesCount, trueNames.isSelected(), minWeight, maxWeight, distribution.getSelectedIndex(), meanValue, deviationValue, rateValue, probValue);
				swc.setGraph(graph);  // przekazanie grafu
				operatingWindow.dispose();
			}
		}
		else {
			if(vertices.getText().isEmpty())
				vertices.setBackground(Color.RED);
			if(min.getText().isEmpty())
				min.setBackground(Color.RED);
			if(max.getText().isEmpty())
				max.setBackground(Color.RED);
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
