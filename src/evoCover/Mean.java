package evoCover;

public class Mean implements Measure {
	
	@Override
	public Double getValue(Portfolio p) {
		return p.getMean();
	}

}
