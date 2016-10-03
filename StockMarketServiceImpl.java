package main.com.jpmorgan.stock.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import test.com.jpmorgan.stock.config.TestConfig;
import main.com.jpmorgan.stock.config.StockConfig;
import main.com.jpmorgan.stock.dao.TradeDAO;
import main.com.jpmorgan.stock.dao.TradeDAOImpl;
import main.com.jpmorgan.stock.dao.VolWgtStkPrcDAO;
import main.com.jpmorgan.stock.dao.VolWgtStkPrcDAOImpl;
import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.StockType;
import main.com.jpmorgan.stock.model.Trade;
import main.com.jpmorgan.stock.model.VolumeWeightedStockPrice;
import main.com.jpmorgan.stock.util.CalendarUtil;
import main.com.jpmorgan.stock.util.CalendarUtilImpl;


public class StockMarketServiceImpl implements StockMarketService{
	
		
	TradeDAO tradeDAO;
	
	VolWgtStkPrcDAO volWgtStkPrc;
	
	CalendarUtil now;

	private final static Logger logger = Logger.getLogger(StockMarketServiceImpl.class.getName());

public StockMarketServiceImpl(TradeDAO tradeDAO,
			VolWgtStkPrcDAO volWgtStkPrc, CalendarUtil now) {
		super();
		this.tradeDAO = tradeDAO;
		this.volWgtStkPrc = volWgtStkPrc;
		this.now = now;
	}

public TradeDAO getTradeDAO() {
		return tradeDAO;
	}

	public void setTradeDAO(TradeDAO tradeDAO) {
		this.tradeDAO = tradeDAO;
	}

	public VolWgtStkPrcDAO getVolWgtStkPrc() {
		return volWgtStkPrc;
	}

	public void setVolWgtStkPrc(VolWgtStkPrcDAO volWgtStkPrc) {
		this.volWgtStkPrc = volWgtStkPrc;
	}

	public CalendarUtil getNow() {
		return now;
	}

	public void setNow(CalendarUtil now) {
		this.now = now;
	}

	public double calculateDividendYield(Trade trade){
		double dividendYield = 0.0;
		
		try{
		if(trade.getStock().getStockType() == StockType.COMMON){
			dividendYield = trade.getStock().getLastDividend()/trade.getPrice();
		}
		else if(trade.getStock().getStockType() == StockType.PREFERRED){
			dividendYield = trade.getStock().getFixedDividend() * trade.getStock().getParValue()/trade.getPrice();
		}
		return dividendYield;
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error calculating dividend yield", e.getMessage());
			return dividendYield;
		}
	}

	public void saveDividendYield(Trade trade, double dividendYield){
		try{
		tradeDAO.saveDividendYield(trade, dividendYield);
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error saving dividend yield", e.getMessage());
		}
	}
	
	public double calculatePERatio(Trade trade){
		double peRatio = 0.0;
		double dividendYield = 0.0;		
		dividendYield = calculateDividendYield(trade);	
		saveDividendYield(trade, dividendYield);
		
		try{
		peRatio = trade.getPrice()/dividendYield;	
		return peRatio;		
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error calculating P/E Ratio", e.getMessage());
			return peRatio;
		}
	}
	
	public void savePERatio(Trade trade){
		double peRatio = 0.0;
		peRatio = calculatePERatio(trade);	
		try{
		tradeDAO.savePERatio(trade, peRatio);
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error saving P/E Ratio", e.getMessage());
		}
	}
	
	public void recordTrade(Trade trade){
		try{
		tradeDAO.recordTrade(trade);
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error recording trade", e.getMessage());
		}
		
	}
	
	
	public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol){
		
		
		ArrayList<Trade> tradeList = new ArrayList<Trade>();
		tradeList = tradeDAO.fetchTrades(now.fetch(), now.past(), stockSymbol);
		Iterator<Trade> iterator = tradeList.iterator();
		double num = 0.0;
		double den = 0.0;
		double stockPrices= 0.0;
		Trade t = null;		
		try{
		while(iterator.hasNext()){
			t = iterator.next();
			num+= t.getPrice()*t.getQuantity();
			den+= t.getQuantity();
		}
		stockPrices = num/den;
		
		return stockPrices;
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error calculating volume weighted stock price", e.getMessage());
			return stockPrices;
		}
				
	}
	
	public void saveVolWgtStkPrc(StockSymbol stockSymbol){
		double stockPrices = 0.0;
		stockPrices = calculateVolumeWeightedStockPrice(stockSymbol);
		try{
		volWgtStkPrc.saveVolWgtStkPrc(stockPrices, now.fetch(), stockSymbol);
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error saving volume weighted stock price", e.getMessage());
		}
	}
		
	public double calculateGBCE(){
		
		HashMap<StockSymbol, VolumeWeightedStockPrice> list = new HashMap<StockSymbol, VolumeWeightedStockPrice>();
		
		double price = 1.0;
		double GBCE = 0.0;
		double i = 0.0;
		for (StockSymbol sym : StockSymbol.values()) {
			saveVolWgtStkPrc(sym);
			}			
		list = volWgtStkPrc.fetchVolWgtStkPrcList();		
		Iterator<Map.Entry<StockSymbol, VolumeWeightedStockPrice>> iterator = list.entrySet().iterator();
		try{
		while(iterator.hasNext()){
			Map.Entry<StockSymbol, VolumeWeightedStockPrice> entry = iterator.next();
			VolumeWeightedStockPrice v = entry.getValue();
			price = price * volWgtStkPrc.getStockPrices(v);
		}
		i = (double)list.size();
		double n = 1/i;
		GBCE = Math.pow(price, n);
		return i;
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Error calculating GBCE", e.getMessage());
			return GBCE;
		}
	}
	
}
