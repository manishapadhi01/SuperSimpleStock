package main.com.jpmorgan.stock.model;

public class Stock{

	private StockSymbol stockSymbol;
	
	public Stock(StockSymbol stockSymbol, StockType stockType,
			double lastDividend, double fixedDividend, double parValue) {
		super();
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	private StockType stockType;
	
	private double lastDividend;
	
	private double fixedDividend;
	
	private double parValue;

	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
		
}
