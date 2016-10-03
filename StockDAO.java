package main.com.jpmorgan.stock.dao;

import main.com.jpmorgan.stock.model.Stock;
import main.com.jpmorgan.stock.model.StockSymbol;

public interface StockDAO {

	public Stock fetchStock(StockSymbol symbol);
	
	public void saveStock(Stock s);
}
