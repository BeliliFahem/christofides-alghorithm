package pl.wat.tal.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResultWindowComponents implements ActionListener {
	private JFrame operatingWindow;
	private JTextArea results;
	private JScrollPane resultsScroller;
	private JButton ok;
	private JButton save;
	private JPanel centralPane;
	private JPanel bottomPane;
	private BorderLayout centralLayout;
	private GridLayout bottomLayout;
	public final static int BRUTE = 0;
	public final static int CHRISTOFIDES = 1;
	
	public ResultWindowComponents(JFrame operatingWindow){
		this.operatingWindow = operatingWindow;
		initCentralPanel();
		initBottomPanel();
	}
	
	protected void initCentralPanel(){
		results = new JTextArea(200, 100);
		resultsScroller = new JScrollPane(results);
	}
	
	protected void initBottomPanel(){
		bottomPane = new JPanel();
		bottomLayout = new GridLayout(2, 1, 20, 20);
		
		save = new JButton("Zapisz do pliku");
		ok = new JButton("OK");
		
		save.addActionListener(this);
		ok.addActionListener(this);
		
		bottomPane.setLayout(bottomLayout);
		bottomPane.add(save);
		bottomPane.add(ok);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source == save){
			// TODO
		}
		else
			if(source == ok){
				operatingWindow.setVisible(false);  // nie zamykamy okna chowamy je tylko
			}
		
	}
	
	public void countRoad(int algorithm){
		results.setText(results.getText() + "\n\n" + "====================" + "\n");
		results.setText(results.getText() + "Data: ");
	}

	public JScrollPane getResultsScroller() {
		return resultsScroller;
	}

	public JPanel getBottomPane() {
		return bottomPane;
	}

}
