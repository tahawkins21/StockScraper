package com.taylorahawkins.finalproj;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainApp{
	public static void main(String[] args) {
		String csvPath = ""; //= "C:\\Users\\tahaw\\JavaProjects\\SP500tickers.csv";
		String newCsvPath= ""; //= "C:\\Users\\tahaw\\JavaProjects\\StockOutput2.csv";
		StockFrame frame = new StockFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JOptionPane.showMessageDialog(frame, "Choose the location of the SP500Tickers CSV file");
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    System.out.println("You chose to open this file: " +
		    chooser.getSelectedFile());
		    csvPath = chooser.getSelectedFile().toString();
		}else {
			System.out.println("Ticker file is needed to run application. Exiting...");
			frame.dispose();
		}
		JOptionPane.showMessageDialog(frame, "Choose the location of the output csv file (choose a folder and type a new filename with .csv extension");
		returnVal = chooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    System.out.println("You chose this file to create " +
		    chooser.getSelectedFile());
		    newCsvPath = chooser.getSelectedFile().toString();
		}else {
			System.out.println("exiting");
			return;
		}
		
		HashMap<String, Stock> stockMap = new HashMap<String, Stock>();//stores the created stock and its ticker name as the key
		
		ThreadControl tc = new ThreadControl(csvPath, newCsvPath);//starts all threads that go off and web scrape. Will write the results to the newCsvPath file, or create it if not there
		
		StockFileReader reader = new StockFileReader(newCsvPath);//reads our newly created CSV file
		
		ArrayList<String[]> fileContents = reader.readCSV();//grab the contents of the file we read
		
		for(String[] stock: fileContents) {//for each line, make a new Stock object with ticker name, eps, price, and PE ratio. 
			String name = stock[0];
			double eps = Double.parseDouble(stock[1]);
			double price = Double.parseDouble(stock[2]);
			double peRatio = Double.parseDouble(stock[3]);
			
			Stock newStock = new Stock(name, eps, price, peRatio);
			stockMap.put(name, newStock);
		}
		
		frame.setTickerOptions(stockMap); //populates the frame with the newly created stock objects to view
		
		System.out.println("Should be ready to use now");//when you see this, its ready
	}
}
