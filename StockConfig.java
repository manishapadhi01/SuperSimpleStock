package main.com.jpmorgan.stock.config;


import java.util.ArrayList;
import java.util.Calendar;

import main.com.jpmorgan.stock.dao.StockDAO;
import main.com.jpmorgan.stock.dao.StockDAOImpl;
import main.com.jpmorgan.stock.dao.TradeDAO;
import main.com.jpmorgan.stock.dao.TradeDAOImpl;
import main.com.jpmorgan.stock.dao.VolWgtStkPrcDAO;
import main.com.jpmorgan.stock.dao.VolWgtStkPrcDAOImpl;
import main.com.jpmorgan.stock.model.Stock;
import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.StockType;
import main.com.jpmorgan.stock.model.VolumeWeightedStockPrice;
import main.com.jpmorgan.stock.service.StockMarketService;
import main.com.jpmorgan.stock.service.StockMarketServiceImpl;
import main.com.jpmorgan.stock.util.CalendarUtil;
import main.com.jpmorgan.stock.util.CalendarUtilImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StockConfig {
	
	@Bean	
	@Scope("singleton")
	public Stock TEA(){
		return new Stock(StockSymbol.TEA, StockType.COMMON, 0.0, 0.0, 100.0);
	}
	
	@Bean
	@Scope("singleton")
	public Stock POP(){
		return new Stock(StockSymbol.POP, StockType.COMMON, 8.0, 0.0, 100.0);
	}
	
	@Bean
	@Scope("singleton")
	public Stock ALE(){
		return new Stock(StockSymbol.ALE, StockType.COMMON, 23.0, 0.0, 60.0);
	}
	
	@Bean
	@Scope("singleton")	
	public Stock GIN(){
		return new Stock(StockSymbol.GIN, StockType.PREFERRED, 8.0, 2.0, 100.0);
	}
	
	@Bean
	@Scope("singleton")
	public Stock JOE(){
		return new Stock(StockSymbol.JOE, StockType.COMMON, 13.0, 0.0, 250.0);
	}
	
	@Bean
	@Scope("singleton")
	public StockDAO stockDAO(){
		ArrayList<Stock> list = new ArrayList<Stock>();
		list.add(TEA());
		list.add(POP());
		list.add(ALE());
		list.add(GIN());
		list.add(JOE());
		return new StockDAOImpl(list);
	}
	
		
		
	@Bean
	@Scope("prototype")
	public TradeDAO tradeDAO(){
		return new TradeDAOImpl();
	}
	
	@Bean
	@Scope("prototype")
	public VolWgtStkPrcDAO volWgtStkPrcDAO(){
		return new VolWgtStkPrcDAOImpl();
	}
		
	@Bean
	@Scope("prototype")
	public CalendarUtil calendarUtil(){
		return new CalendarUtilImpl(Calendar.getInstance());
	}
	
	@Bean
	@Scope("prototype")
	public VolumeWeightedStockPrice volumeWeightedStockPrice(){
		return new VolumeWeightedStockPrice();
	}
	
}
