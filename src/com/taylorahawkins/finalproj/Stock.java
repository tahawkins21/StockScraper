package com.taylorahawkins.finalproj;

public class Stock {
	private double eps;
	private double price;
	private double pe;
	private String ticker;
	public Stock(String ticker, double eps, double price, double pe) {
		super();
		this.eps = eps;
		this.price = price;
		this.pe = pe;
		this.ticker = ticker;
	}
	public double getEps() {
		return eps;
	}
	public double getPrice() {
		return price;
	}
	public double getPe() {
		return pe;
	}
	public String getTicker() {
		return ticker;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(eps);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pe);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (Double.doubleToLongBits(eps) != Double.doubleToLongBits(other.eps))
			return false;
		if (Double.doubleToLongBits(pe) != Double.doubleToLongBits(other.pe))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}
	
	
}
