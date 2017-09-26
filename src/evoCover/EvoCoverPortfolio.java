package evoCover;

public class EvoCoverPortfolio {
	int id;
	double mean;
	double semiVar;
	double skewness;
	double sortinoRatio;
	double totalWeight;
	
	public EvoCoverPortfolio(int id){
		this.id = id;
		totalWeight = 0;
	}
	
}
