package evoCover;

public class SemiVariance implements Measure{

	@Override
	public Double getValue(Portfolio p) {
		return p.getSemiVar();
	}

}
