package com.taylorahawkins.finalproj;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StockWriter {
		private FileWriter fw;
		private BufferedWriter bw;
		
		public StockWriter(String path) {
			
			try {
				fw = new FileWriter(path);
				bw = new BufferedWriter(fw);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't open file to write: " + e.getMessage());
			}
		}
		
		
		public void writeCSV(HashMap<String, ArrayList<Double>> tickerMap) throws IOException {
			System.out.println("Writing to file!");
			//write, newline, close
			bw.write("Name");
			bw.write(",");
			bw.write("EPS");
			bw.write(",");
			bw.write("Price");
			bw.write(",");
			bw.write("PE");
			bw.newLine();
			
			for(String key : tickerMap.keySet()) {
				String line = "";
				double eps = tickerMap.get(key).get(0);
				double price = tickerMap.get(key).get(1);
				double peRatio = price/eps;
				String peString = Double.toString(peRatio);
				
				bw.write(key);
				bw.write(",");
				bw.write(tickerMap.get(key).get(0).toString());
				bw.write(",");
				bw.write(tickerMap.get(key).get(1).toString());
				bw.write(",");
				bw.write(peString);
				bw.newLine();
			}
			bw.close();
			System.out.println("Successfully wrote to file");
		} 
}
