package plot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PlotData {
	
	String fileName;
	String data;
	
	public PlotData(String fileName){
		this.data = "";
		this.fileName = fileName;
	}
	
	public void add(String moreData){
		this.data += moreData;
	}
	
	public void print() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(fileName);
		writer.print(data);
		writer.close();
	}
	
}
