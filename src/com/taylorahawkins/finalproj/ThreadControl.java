package com.taylorahawkins.finalproj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadControl implements ReturnResults{
	private long tic = (new Date()).getTime();
	private Object lock = new Object();
	private HashMap<String, ArrayList<Double>> tickerMap = new HashMap<String, ArrayList<Double>>();
	private ArrayList<ArrayList<String>> tickerURLs;
	private String path;
	
	public ThreadControl(String filePath, String newPath) {
		//get the list of tickers
		this.path = filePath;
		LoadFile file = new LoadFile(path);
		ArrayList<String> tickers = file.getFileConents();
		
		//build URLS to load
		TickerURLBuilder urlBuilder = new TickerURLBuilder(tickers);
		//get the urls that were built. pass these to the webscraper. includes ticker name
		tickerURLs = urlBuilder.getTickerURLs();
		
		//start the executor to handle threads.
		ExecutorService executor = Executors.newFixedThreadPool(4);//limit the number of threads to be released to the cpu
		
		for(ArrayList<String> t : tickerURLs) {
			
			executor.execute(new WebScraper(t, this));
		}
		//executor.execute(new WebScraper(threadControl.tickerURLs.get(0),threadControl));
				
		executor.shutdown();//stay alive til all threads done, then shutdown
		while(!executor.isTerminated()) {
			Thread.yield();
		}
		for(String key : tickerMap.keySet()) {
			System.out.println(key + " " + tickerMap.get(key));
		}
		
		StockWriter writer = new StockWriter(newPath);
		try {
			writer.writeCSV(tickerMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Couldn't write: " + e.getMessage());
		}
	}
	
	public HashMap<String, ArrayList<Double>> getTickerData(){
		return tickerMap;
	}
	
	@Override
	public void getResults(String ticker, ArrayList<Double> data) {
		// TODO Auto-generated method stub
		//ADD HERE -- Create a class that regexes the webpage inside of WebScraper, and return that scraped shit here
		//System.out.println("In results " + ticker + " " + data.get(0) + " " + data.get(1));
		tickerMap.put(ticker, data);//add the ticker and its eps and price inside data arraylist to the hashmap
		
	}
}