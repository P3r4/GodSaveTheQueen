package dataDigest.bovespa;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BovespaShare {

	String tradeCode;
	Map<Integer,BovespaLog> logDayMap;
	
	public BovespaShare(String tradeCode){
		this.logDayMap =  new HashMap<>();
		this.tradeCode = tradeCode;
	}	

}
