package main.com.jpmorgan.stock.dao;

import java.util.ArrayList;
import java.util.Iterator;

import main.com.jpmorgan.stock.model.Stock;
import main.com.jpmorgan.stock.model.StockSymbol;

public class StockDAOImpl implements StockDAO{

	private ArrayList<Stock> stocks = new ArrayList<Stock>();

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public StockDAOImpl(ArrayList<Stock> stocks) {
		super();
		this.stocks = stocks;
	}

	public Stock fetchStock(StockSymbol symbol){
		Iterator<Stock> iterator = stocks.iterator();
		Stock s = null;		
		while(iterator.hasNext()){
			s = iterator.next();
			if(s.getStockSymbol() == symbol){
				break;
			}
		}
		return s;
	}
	
	public void saveStock(Stock s){
		this.getStocks().add(s);
	}
}
