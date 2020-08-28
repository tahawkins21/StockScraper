package com.taylorahawkins.finalproj;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class WebScraper implements Runnable{
	private String urlString;
	private URLConnection connection = null;
	private InputStreamReader in;
	private BufferedReader data;
	private String line;
	private StringBuffer buff = new StringBuffer();
	private ReturnResults results;
	private String tickerName;
	private ArrayList<Double> stockData = new ArrayList<Double>();
	
	public WebScraper(ArrayList<String> urlString, ReturnResults results) {
		this.tickerName = urlString.get(0);
		this.urlString = urlString.get(1);
		this.results = results;
	}
	
	private void process() {
		try {
			URL url = new URL(urlString);
			try {
				connection = url.openConnection();
				connection.connect();
				data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = data.readLine()) != null) {
					buff.append(line + "\n");
				}
				data.close();
			} catch (IOException e) {
				System.err.println("Error connecting: " +  e.getMessage());
			}
		} catch (MalformedURLException e) {
			System.err.println("Misformed URL " + e.getMessage());
			
		}
	}
	
	public StringBuffer getPage() {
		return buff;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//ADD THE MEAT AND BONES HERE 
		process();
		System.out.println(urlString);
		//System.out.println(getPage().substring(100,1000));
		//need to make a regex class and have it search the getPage() and return those results
		TickerRegEx regex = new TickerRegEx(getPage());//set up the regex with the html we got
		double eps = regex.findEPS();
		double price = regex.findPrice();
		stockData.add(eps);
		stockData.add(price);
		results.getResults(tickerName, stockData);//pass the page, and the found results here? ### ### ### 
	}
}
