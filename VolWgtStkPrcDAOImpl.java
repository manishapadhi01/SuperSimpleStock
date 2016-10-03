package main.com.jpmorgan.stock.dao;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import main.com.jpmorgan.stock.config.StockConfig;
import main.com.jpmorgan.stock.model.StockSymbol;
import main.com.jpmorgan.stock.model.VolumeWeightedStockPrice;

public class VolWgtStkPrcDAOImpl implements VolWgtStkPrcDAO{
	
	@Autowired
	StockConfig stockConfig;
	
	public VolWgtStkPrcDAOImpl() {
		super();
		
	}

	HashMap<StockSymbol, VolumeWeightedStockPrice> volWgtStkPrcList = new HashMap<StockSymbol, VolumeWeightedStockPrice>();

	public HashMap<StockSymbol, VolumeWeightedStockPrice> getVolWgtStkPrcList() {
		return volWgtStkPrcList;
	}

	public void setVolWgtStkPrcList(HashMap<StockSymbol, VolumeWeightedStockPrice> volWgtStkPrcList) {
		this.volWgtStkPrcList = volWgtStkPrcList;
	}
	
	public HashMap<StockSymbol, VolumeWeightedStockPrice> fetchVolWgtStkPrcList(){
		return this.volWgtStkPrcList;
	}

	public void saveVolWgtStkPrc(double stockPrices, Calendar timeCapturedAt, StockSymbol stockSymbol){
		VolumeWeightedStockPrice volumeWeightedStockPrice = stockConfig.volumeWeightedStockPrice();		
		volumeWeightedStockPrice.setStockPrices(stockPrices);
		volumeWeightedStockPrice.setTimeCapturedAt(timeCapturedAt);
		
		volWgtStkPrcList.put(stockSymbol, volumeWeightedStockPrice);
	}
	
	public double getStockPrices(VolumeWeightedStockPrice volumeWeightedStockPrice){
		return volumeWeightedStockPrice.getStockPrices();
	}
	
	
}
