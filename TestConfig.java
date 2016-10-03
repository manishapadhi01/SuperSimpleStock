package test.com.jpmorgan.stock.config;

import java.util.Calendar;

import main.com.jpmorgan.stock.config.StockConfig;
import main.com.jpmorgan.stock.dao.TradeDAO;
import main.com.jpmorgan.stock.dao.TradeDAOImpl;
import main.com.jpmorgan.stock.model.Trade;
import main.com.jpmorgan.stock.model.TradeIndicator;
import main.com.jpmorgan.stock.service.StockMarketService;
import main.com.jpmorgan.stock.service.StockMarketServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Configuration
@ContextConfiguration(classes=StockConfig.class, loader=AnnotationConfigContextLoader.class)
public class TestConfig {
	
	@Autowired
	StockConfig stockConfig;
	
	@Bean
	@Scope("singleton")	
	public StockMarketService stockMarketService(){
		return new StockMarketServiceImpl(stockConfig.tradeDAO(), stockConfig.volWgtStkPrcDAO(), stockConfig.calendarUtil());
	}

	@Bean
	@Scope("singleton")
	public Trade tradeTEABuy(){
		return new Trade(stockConfig.TEA(), 1.0, Calendar.getInstance(), TradeIndicator.BUY, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeTEASell(){
		return new Trade(stockConfig.TEA(), 1.0, Calendar.getInstance(), TradeIndicator.SELL, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradePOPBuy(){
		return new Trade(stockConfig.POP(), 1.0, Calendar.getInstance(), TradeIndicator.BUY, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradePOPSell(){
		return new Trade(stockConfig.POP(), 1.0, Calendar.getInstance(), TradeIndicator.SELL, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeALEBuy(){
		return new Trade(stockConfig.ALE(), 1.0, Calendar.getInstance(), TradeIndicator.BUY, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeALESell(){
		return new Trade(stockConfig.ALE(), 1.0, Calendar.getInstance(), TradeIndicator.SELL, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeGINBuy(){
		return new Trade(stockConfig.GIN(), 10.0, Calendar.getInstance(), TradeIndicator.BUY, 250.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeGINSell(){
		return new Trade(stockConfig.GIN(), 10.0, Calendar.getInstance(), TradeIndicator.SELL, 250.0);
	}
	
	@Bean
	@Autowired
	@Scope("singleton")
	public Trade tradeJOEBuy(){
		return new Trade(stockConfig.JOE(), 1.0, Calendar.getInstance(), TradeIndicator.BUY, 25.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeJOESell(){
		return new Trade(stockConfig.JOE(), 10.0, Calendar.getInstance(), TradeIndicator.SELL, 250.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeGIN1Buy(){
		return new Trade(stockConfig.GIN(), 2.0, Calendar.getInstance(), TradeIndicator.BUY, 200.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeGIN2Buy(){
		return new Trade(stockConfig.GIN(), 4.0, stockConfig.calendarUtil().past(), TradeIndicator.BUY, 100.0);
	}
	
	@Bean
	@Scope("singleton")
	public Trade tradeGIN3Buy(){
		return new Trade(stockConfig.GIN(), 6.0, Calendar.getInstance(), TradeIndicator.BUY, 50.0);
	}	
}
