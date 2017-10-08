package evoCover;

public class Mean implements Measure {
	
	@Override
	public Double getValue(EvoCoverPortfolio p) {
		return p.getMean();
	}

}
