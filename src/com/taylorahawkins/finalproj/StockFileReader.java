package com.taylorahawkins.finalproj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class StockFileReader {
	private FileReader fr;
	private BufferedReader br;
	ArrayList<String[]> lineArrList = new ArrayList<String[]>();
	
	public StockFileReader(String file) {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String[]> readCSV() {		
		
		String line = "";
		try {
			br.readLine();//skip headers
			while((line = br.readLine()) != null) {
				System.out.println(line);
				String[] lineArr = line.split(",");
				lineArrList.add(lineArr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineArrList;
	}//end main
}