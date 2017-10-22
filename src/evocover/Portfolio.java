package evocover;

public class Portfolio {
	int id;
	Double mean;
	Double semiVar;
	Double skewness;
	double maxW;
	double minW;
	int trail;

	public Portfolio(int id) {
		trail = 0;
		this.id = id;
		maxW = 0;
		minW = 0;
	}

	public Double getDelta() {
		return Math.abs(maxW - minW + 0.00000000000001);
	}

	public Double getMean() {
		return mean;
	}
	
	public Double getDownsideRisk(){
		return Math.sqrt(semiVar);
	}

	public int getId() {
		return id;
	}

	public Double getHyperVolume() {
		return (mean + 1) * (skewness + 1) * (1 / getDownsideRisk()) * (1 / getDelta());
	}

	public Double getSortinoRatio() {
		return mean / getDownsideRisk();
	}

}
