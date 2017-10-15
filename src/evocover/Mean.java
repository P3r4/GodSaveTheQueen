package evocover;

import java.util.Comparator;

public class Mean implements Measure {
	
	@Override
	public Double getValue(Portfolio p) {
		return p.mean;
	}

	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				return o2.mean.compareTo(o1.mean);
			}
		};
	}
	
}
