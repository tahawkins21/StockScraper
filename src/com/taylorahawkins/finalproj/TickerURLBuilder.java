package com.taylorahawkins.finalproj;

import java.util.ArrayList;

public class TickerURLBuilder {
	//https://finance.yahoo.com/quote/AAPL/key-statistics 
	private String baseURL1 = "https://finance.yahoo.com/quote/";
	private String baseURL2 = "/key-statistics";
	private ArrayList<ArrayList<String>> tickerURLs = new ArrayList<ArrayList<String>>();
	
	public TickerURLBuilder(ArrayList<String> tickers) {
		for(String t: tickers) {
			String url = baseURL1 + t + baseURL2;
			ArrayList<String> thisTicker = new ArrayList<String>();
			thisTicker.add(t);
			thisTicker.add(url);
			
			tickerURLs.add(thisTicker);
		}
	}

	public ArrayList<ArrayList<String>> getTickerURLs() {
		return tickerURLs;
	}
	
}
