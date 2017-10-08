package evoCover;

public class SemiVariance implements Measure{

	@Override
	public Double getValue(EvoCoverPortfolio p) {
		return p.getSemiVar();
	}

}
