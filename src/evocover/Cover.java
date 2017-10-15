package evocover;

import java.util.ArrayList;
import java.util.List;

public class Cover {

	double correlation;
	List<Double> coverList;
	int flag;
	
	public Cover(){
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
