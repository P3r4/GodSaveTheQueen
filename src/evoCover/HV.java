package evoCover;

public class HV implements Measure {
	
	@Override
	public Double getValue(Portfolio p) {
		return p.getHyperVolume();
	}

}