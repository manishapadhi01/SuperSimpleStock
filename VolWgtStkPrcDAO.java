package main.com.jpmorgan.stock.dao;

import java.util.Calendar;
import java.util.HashMap;

import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.VolumeWeightedStockPrice;


public interface VolWgtStkPrcDAO {

	public void saveVolWgtStkPrc(double stockPrices, Calendar timeCapturedAt, StockSymbol stockSymbol);
	
	public HashMap<StockSymbol, VolumeWeightedStockPrice> fetchVolWgtStkPrcList();
	
	public double getStockPrices(VolumeWeightedStockPrice volumeWeightedStockPrice);
	}
