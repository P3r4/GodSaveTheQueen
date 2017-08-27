package measure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MeasureCalculator {

	List<String> colIdList;
	List<String> rowIdList;
	List<List<Float>> measureGroupList;
	List<List<Float>> logList;
	String fileName;
	
	public MeasureCalculator(String fileName) throws IOException{
		this.fileName = fileName;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String [] splitLine = line.split(",");
		
		rowIdList =  new ArrayList<>();
		for (int i = 2; i < splitLine.length; i++) {
			rowIdList.add(splitLine[2]);
		}
		
		logList = new ArrayList<>();
		
		List<Float> row;
		while ((line = reader.readLine()) != null) {
			splitLine = line.split(",");
			rowIdList.add(splitLine[1]);
			row = new ArrayList<>();
			for (int i = 2; i < splitLine.length; i++) {
				row.add(Float.parseFloat(splitLine[i]));
			}
			logList.add(row);
		}
		
		reader.close();
	
	
		Float last = logList.get(rowIdList.size()-1).get(rowIdList.size()-1);
		for (int i = rowIdList.size()-1; i > -1; i--) {
			for (int j = colIdList.size()-1; j > -1; j--) {
				if(last != null && logList.get(i).get(j) == null){
					logList.get(i).set(j, last);
				}
				
			}	
		}
	}
	
    
	
	
	
	
}
