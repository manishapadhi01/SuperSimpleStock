package main.com.jpmorgan.stock.model;

import java.util.Calendar;

public class Trade {
	
	private Stock stock;	

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double quantity;
	
	private Calendar timeStamp;


	public Trade(Stock stock, double quantity, Calendar timeStamp,
			TradeIndicator indicator, double price) {
		super();
		this.stock = stock;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
		this.indicator = indicator;
		this.price = price;
	}

	private TradeIndicator indicator;
	
	private double price;
	
	private double dividendYield;
	
	private double peRatio;
	
	public double getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(double dividendYield) {
		this.dividendYield = dividendYield;
	}

	public double getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(double peRatio) {
		this.peRatio = peRatio;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Calendar timeStamp) {
		this.timeStamp = timeStamp;
	}

	public TradeIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(TradeIndicator indicator) {
		this.indicator = indicator;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
