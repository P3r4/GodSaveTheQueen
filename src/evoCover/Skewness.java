package evoCover;

import java.util.Comparator;

public class Skewness implements Measure{
	
	@Override
	public Double getValue(Portfolio p) {
		return p.skewness;
	}

	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				return o2.skewness.compareTo(o1.skewness);
			}
		};
	}

	
}
