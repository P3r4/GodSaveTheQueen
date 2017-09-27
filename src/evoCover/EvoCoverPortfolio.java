package evoCover;

public class EvoCoverPortfolio {
	int id;
	double mean;
	double semiVar;
	double skewness;
	double sortinoRatio;
	double maxW;
	double minW;
	
	public EvoCoverPortfolio(int id){
		this.id = id;
		maxW = 0;
		minW = 10;
	}
	
	public int getId(){
		return id;
	}
	
}
