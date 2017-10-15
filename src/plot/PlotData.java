package plot;

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
	
}
