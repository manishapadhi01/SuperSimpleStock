package main.com.jpmorgan.stock.util;

import java.util.Calendar;

public class CalendarUtilImpl implements CalendarUtil{

	Calendar now = Calendar.getInstance();

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
	}
	
	public CalendarUtilImpl(Calendar now) {
		super();
		this.now = now;
	}

	public Calendar past(){
		now.add(Calendar.MINUTE, -5);
		return now;
	}
	
	public Calendar fetch(){
		return this.getNow();
	}
}
