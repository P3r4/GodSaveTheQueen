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
	
	public Double getDelta(){
		return maxW - minW;
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
	
	public Double getHyperVolume(){
		return (mean+1)*(skewness+1)*(1/semiVar)*(1/getDelta());
	}
	
	public Double getSortinoRatio(){
		return mean/semiVar;
	}
	
	
}
