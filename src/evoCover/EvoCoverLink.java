package evoCover;

import java.util.ArrayList;
import java.util.List;

public class EvoCoverLink {

	double correlation;
	List<Double> coverList;
	int flag;
	
	public EvoCoverLink(){
		coverList = new ArrayList<>();
	}
	
	public void setCorrelation(double corr){
		flag = 0;
		this.correlation = corr;
	}
	
}
