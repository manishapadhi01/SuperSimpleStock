package main.com.jpmorgan.stock.dao;

import java.util.ArrayList;
import java.util.Calendar;

import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.Trade;

public interface TradeDAO {

	public void recordTrade(Trade trade);
	
	public ArrayList<Trade> fetchTrades(Calendar maxRange, Calendar minRange, StockSymbol stockSymbol);
	
	public void saveDividendYield(Trade trade, double dividendYield);
	
	public void savePERatio(Trade trade, double peRatio);
}
