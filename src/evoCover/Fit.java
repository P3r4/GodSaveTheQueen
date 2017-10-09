package evoCover;

import java.util.Comparator;

public class Fit implements Measure{

	
	@Override
	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				Double hv = o2.getFit();
				return hv.compareTo(o1.getFit());
			}
		};
	}

	@Override
	public Double getValue(Portfolio p) {
		return p.getFit();
	}
}
