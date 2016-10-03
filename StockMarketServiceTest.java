package test.com.jpmorgan.stock.service;

import main.com.jpmorgan.stock.config.StockConfig;
import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.Trade;
import main.com.jpmorgan.stock.service.StockMarketService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import test.com.jpmorgan.stock.config.TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, StockConfig.class}, loader=AnnotationConfigContextLoader.class)
public class StockMarketServiceTest {

	@Autowired
	TestConfig testConfig;
	
	@Autowired 
	StockConfig stockConfig;
	
	
	@Test
	public void testDividendYieldPass() {
		double dividendYield = 0.8;
		
		assertEquals(dividendYield, testConfig.stockMarketService().calculateDividendYield(testConfig.tradeGINBuy()), 0.01);
		}
	
	@Test
	public void testDividendYieldFail() {
		double dividendYield = 0.1;
		
		assertNotEquals(dividendYield, testConfig.stockMarketService().calculateDividendYield(testConfig.tradeGINSell()), 0.01);
		}
	@Test
	public void testPERatioPass() {
		double peRatio = 312.5;
		assertEquals(peRatio, testConfig.stockMarketService().calculatePERatio(testConfig.tradeGINBuy()), 0.01);
		}
	
	@Test
	public void testPERatioFail() {
		double peRatio = 12.0;
		assertNotEquals(peRatio, testConfig.stockMarketService().calculatePERatio(testConfig.tradeGINSell()), 0.01);
		}
	
	@Test
	public void testVolumeWeightedStockPricePass(){
		double volumeWeightedStockPrice = 91.66;
		testConfig.stockMarketService().savePERatio(testConfig.tradeALEBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeALESell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeJOEBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeJOESell());
		testConfig.stockMarketService().savePERatio(testConfig.tradePOPBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradePOPSell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeTEABuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeTEASell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN1Buy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN2Buy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN3Buy());
		assertEquals(volumeWeightedStockPrice, testConfig.stockMarketService().calculateVolumeWeightedStockPrice(StockSymbol.GIN), 0.01);
		
	}
	
	@Test
	public void testVolumeWeightedStockPriceFail(){
		double volumeWeightedStockPrice = 100.66;
		testConfig.stockMarketService().savePERatio(testConfig.tradeALEBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeALESell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeJOEBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeJOESell());
		testConfig.stockMarketService().savePERatio(testConfig.tradePOPBuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradePOPSell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeTEABuy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeTEASell());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN1Buy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN2Buy());
		testConfig.stockMarketService().savePERatio(testConfig.tradeGIN3Buy());
		assertNotEquals(volumeWeightedStockPrice, testConfig.stockMarketService().calculateVolumeWeightedStockPrice(StockSymbol.GIN), 0.01);
	}
	
	
	}
