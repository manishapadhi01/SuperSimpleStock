package main.com.jpmorgan.stock.service;

import java.util.Calendar;

import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.Trade;

public interface StockMarketService {

		public double calculateDividendYield(Trade trade);
	
		public void saveDividendYield(Trade trade, double dividendYield);
	
		public double calculatePERatio(Trade trade);
		
		public void savePERatio(Trade trade);
		
		public void recordTrade(Trade trade);
				
		public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol);
		
		public void saveVolWgtStkPrc(StockSymbol stockSymbol);
		
		public double calculateGBCE();		
}
