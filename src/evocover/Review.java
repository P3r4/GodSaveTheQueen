package evocover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Review {

	String fileName;
	
	List<StockLog> stockList;
	List<Integer> dayList;
	List<Double> portfolioReturnList;

	public Review(String fileName) throws IOException{
		init(fileName);
	}
	
	public void init(String fileName) throws IOException {
		stockList = new ArrayList<>();
		dayList = new ArrayList<>();
		this.fileName = fileName;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String[] splitLine = line.split(",");
		for (int i = 1; i < splitLine.length; i++) {
			dayList.add(Integer.parseInt(splitLine[i]));
		}
		StockLog log;
		while (((line = reader.readLine()) != null)) {
			log = new StockLog(line);
			stockList.add(log);
			
		}
		reader.close();
		reset();
		
	}
	
	public void reset(){
		portfolioReturnList = new ArrayList<>();
		for(Integer day : dayList){
			portfolioReturnList.add(0.0);
		}
	}
	
	
	public int getStock(String tradeCode){
		StockLog stock;
		for (int i = 0; i < stockList.size(); i++) {
			stock = stockList.get(i);
			if(stock.tradeCode.equals(tradeCode)) return i;
		}
		return -1;
	}
	
	
	public String formatPortfolioReturn(){
		String out = "";
		DecimalFormat df = new DecimalFormat("#0.000000");
		for(Double d : portfolioReturnList){
			out += ","+df.format(d);
		}
		return out;
	}
	
	public void addWeight(double weight, String tradeCode){
		int i = getStock(tradeCode);
		StockLog stock;
		if(i != -1){
			stock = stockList.get(i);
			for(int j = 0; j<stock.returnLog.size(); j++){
				portfolioReturnList.set(j,portfolioReturnList.get(j)
						+ weight*stock.returnLog.get(j));
				
				
			}
			
		}
	}
}
