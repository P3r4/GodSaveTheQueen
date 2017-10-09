package evoCover;

import java.util.Comparator;

public class HV implements Measure {
	
	@Override
	public Double getValue(Portfolio p) {
		return p.getHyperVolume();
	}
	@Override
	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				Double hv = o2.getHyperVolume();
				return hv.compareTo(o1.getHyperVolume());
			}
		};
	}

}