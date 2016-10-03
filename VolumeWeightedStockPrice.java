package main.com.jpmorgan.stock.model;

import java.util.Calendar;

public class VolumeWeightedStockPrice {

	Calendar timeCapturedAt;
	
	public VolumeWeightedStockPrice() {
		super();
		}

	double stockPrices = 0.0;
	
	public Calendar getTimeCapturedAt() {
		return timeCapturedAt;
	}

	public void setTimeCapturedAt(Calendar timeCapturedAt) {
		this.timeCapturedAt = timeCapturedAt;
	}

	public double getStockPrices() {
		return stockPrices;
	}

	public void setStockPrices(Double stockPrices) {
		this.stockPrices = stockPrices;
	}
	

}
