package com.taylorahawkins.finalproj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TickerRegEx {
	private StringBuffer html;
	private double price;
	private String priceString;
	private double eps;
	private String epsString;//data-reactid=\"34\">(.*?)</span>    Mb(-4px)
	private Pattern pricePattern = Pattern.compile("currentPrice\".*?raw.{2}(.*?),");//price regEx
	private Pattern epsPattern = Pattern.compile("Diluted EPS.*?data-reactid.*?data-reactid.*?>(.*?)</");//eps regex
	private Matcher matcher;
	
	public TickerRegEx(StringBuffer html) {
		this.html = html;
	}
	
	public double findEPS() {
		matcher = epsPattern.matcher(html);
		try {
			if(matcher.find()) {
				epsString = matcher.group(1);
				System.out.println("Found eps -- \n" + epsString);
				eps = Double.parseDouble(epsString);
			}else {
				System.err.println("EPS not found!");
			}
		}catch(Exception e) {
			System.err.println("Error finding or parsing EPS :" + e.getMessage());
		}
		return eps;
	}
	
	public double findPrice() {
		matcher = pricePattern.matcher(html);
		try {
			if(matcher.find()) {
				//System.out.println(html.substring(matcher.start(),matcher.end()));
				priceString = matcher.group(1);
				System.out.println("Found price -- \n" + priceString);
				price = Double.parseDouble(priceString);
			}else {
								System.err.println("Price not found!");
			}
		}catch(Exception e) {
			System.out.println("Error finding or parsing PRICE :" + e.getMessage());
		}
		return price;
	}

}
