package evoCover;

public class Portfolio {
	int id;
	Double mean;
	Double semiVar;
	Double skewness;
	double maxW;
	double minW;
	int trail;
	
	public Portfolio(int id){
		trail = 0;
		this.id = id;
		maxW = 0;
		minW = 10;
	}
	
	
	public Double getMean(){
		return mean;
	}
	
	public Double getSemiVar(){
		return semiVar;
	}
	
	public int getId(){
		return id;
	}
	
	public double getFit(){
		return calcFit(mean)*calcFit(skewness)*calcFit(semiVar);
	}
	
	public double calcFit(double value){
		double out;
		if(value >= 0){
			out = 1/(1+value);
		}else{
			out = 1 + Math.abs(value);
		}
		return out; 
	}
	
	public double getHyperVolume(){
		return (mean+1)*(skewness+1)*(1/semiVar);
	}
	
	public double getSortinoRatio(){
		return mean/semiVar;
	}
	
	
}
