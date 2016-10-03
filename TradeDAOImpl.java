package main.com.jpmorgan.stock.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.Trade;

public class TradeDAOImpl implements TradeDAO{
	
	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public TradeDAOImpl() {
		super();
		
	}

	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	ArrayList<Trade> trades = new ArrayList<Trade>();

	public void recordTrade(Trade trade){
		trades.add(trade);
	}
	
	public ArrayList<Trade> fetchTrades(Calendar maxRange, Calendar minRange, StockSymbol stockSymbol){
		Iterator<Trade> iterator = trades.iterator();
		ArrayList<Trade> latestTrades = new ArrayList<Trade>();
		Trade t = null;		
		while(iterator.hasNext()){
			t = iterator.next();
			if((t.getTimeStamp().compareTo(maxRange) <=0 || t.getTimeStamp().compareTo(minRange) >=0) && t.getStock().getStockSymbol() == stockSymbol){
				latestTrades.add(t);
			}
		}
		return latestTrades;
	}
	
		
	public void saveDividendYield(Trade trade, double dividendYield){
		
		
		trade.setDividendYield(dividendYield);
		//recordTrade(trade);
		//trades.get(trades.indexOf(trade)).setDividendYield(dividendYield);
	}
	
	public void savePERatio(Trade trade, double peRatio){
		
		trade.setPeRatio(peRatio);
		recordTrade(trade);
		
	}
}
