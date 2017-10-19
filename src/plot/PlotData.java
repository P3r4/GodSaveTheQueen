package plot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PlotData {
	
	String dataFileName;
	String reviewFileName;
	String data;
	String review;
	
	public String getData(){
		return data;
	}
	
	public PlotData(){
		this.data = "";
		this.review = "";
	}
	
	public void addData(String moreData){
		this.data += moreData;
	}
	
	public void addReview(String moreReview){
		this.review += moreReview;
	}
	
	public void setReviewFileName(String reviewFileName){
		this.reviewFileName =  reviewFileName;
	}
	
	public void setDataFileName(String dataFileName){
		this.dataFileName = dataFileName;
	}
	
	public void printData() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(dataFileName);
		writer.print(data);
		writer.close();
	}
	
	public void printReview() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(reviewFileName);
		writer.print(review);
		writer.close();
		
	}
	
}
