package evoCover;

import java.util.ArrayList;
import java.util.List;

public class CoverLink {

	double correlation;
	List<Double> coverList;
	int flag;
	
	public CoverLink(){
		coverList = new ArrayList<>();
	}
	
	public void setCorrelation(double corr){
		flag = 0;
		this.correlation = corr;
	}
	
	public double getCoverValue(int solutionId){
		return coverList.get(solutionId);
	}
	
}
