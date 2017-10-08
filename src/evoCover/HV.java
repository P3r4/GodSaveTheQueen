package evoCover;

public class HV implements Measure {
	
	@Override
	public Double getValue(EvoCoverPortfolio p) {
		return p.getHyperVolume();
	}

}