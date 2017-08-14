package data;

import java.util.ArrayList;
import java.util.List;

public class BovespaShare {

	String tradeCode;
	List<BovespaLog> logList;
	
	public BovespaShare(String tradeCode){
		this.logList =  new ArrayList<>();
		this.tradeCode = tradeCode;
	}
	
	
	
}
