package evoCover;

public class EvoCoverPortfolio {
	int id;
	Double mean;
	Double semiVar;
	Double skewness;
	Double sortinoRatio;
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
