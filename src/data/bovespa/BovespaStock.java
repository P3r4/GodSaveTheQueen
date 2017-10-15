package data.bovespa;

import java.util.HashMap;
import java.util.Map;

public class BovespaStock {

	String tradeCode;
	Map<Integer,BovespaLog> logDayMap;
	
	public BovespaStock(String tradeCode){
		this.logDayMap =  new HashMap<>();
		this.tradeCode = tradeCode;
	}	

}
