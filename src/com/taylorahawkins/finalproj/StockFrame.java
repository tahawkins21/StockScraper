package com.taylorahawkins.finalproj;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockFrame extends JFrame implements ActionListener{
	private HashMap<String, Stock> possibleStocks;
	
	private JPanel titlePanel = new JPanel();
	private JLabel title = new JLabel("Stock Data Finder");
	
	private JPanel tickerPanel = new JPanel();
	private JLabel tickerLabel = new JLabel("Ticker:");
	private JComboBox tickerField = new JComboBox();
	
	private JPanel dataPanel = new JPanel();
	
	private JLabel epsLabel = new JLabel("EPS:");
	private JTextField epsField = new JTextField();
	
	private JLabel priceLabel = new JLabel("Price:");
	private JTextField priceField = new JTextField();
	
	private JLabel peLabel = new JLabel("PE:");
	private JTextField peField = new JTextField();
	
	private JPanel submitPanel = new JPanel();
	private JButton submit = new JButton("Find Stock Data");
	
	public StockFrame() {
		super();
		this.setSize(400, 300);
		this.setLayout(new GridLayout(0,1));
		titlePanel.add(title);
		add(titlePanel);
		
		tickerPanel.add(tickerLabel);
		tickerPanel.add(tickerField);
		tickerField.addItem("Loading");
		
		add(tickerPanel);
		
		dataPanel.add(epsLabel);
		dataPanel.add(epsField);
		dataPanel.add(priceLabel);
		dataPanel.add(priceField);
		dataPanel.add(peLabel);
		dataPanel.add(peField);
		dataPanel.setLayout(new GridLayout(0,2));
		add(dataPanel);
		
		submitPanel.add(submit);
		add(submitPanel);		
		
		submit.addActionListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Pressed");
		String key = tickerField.getSelectedItem().toString();
		Stock stock = possibleStocks.get(key);
		String eps = String.valueOf(stock.getEps());
		String price = String.valueOf(stock.getPrice());
		String pe = String.valueOf(stock.getPe());

		epsField.setText(eps);
		priceField.setText(price);
		peField.setText(pe);
		
		
	}

	public void setTickerOptions(HashMap<String, Stock> stockMap) {
		possibleStocks = stockMap;
		tickerField.removeAllItems();//clear the loading... string 
		for(String name : possibleStocks.keySet()) {
			tickerField.addItem(name);
		}
		
	}

}
