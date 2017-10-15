package evocover;

import java.util.Comparator;

public class SemiVariance implements Measure{

	@Override
	public Double getValue(Portfolio p) {
		return p.getSemiVar();
	}
	
	@Override
	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				return o1.semiVar.compareTo(o2.semiVar);
			}
		};
	}

}
