package evocover;

import java.util.Comparator;

public class SortinoRatio implements Measure{

	@Override
	public Double getValue(Portfolio p) {
		return p.getSortinoRatio();
	}

	public Comparator<Portfolio> getComparator() {
		return new Comparator<Portfolio>() {
			@Override
			public int compare(Portfolio o1, Portfolio o2) {
				Double sortino = o2.getSortinoRatio();
				return sortino.compareTo(o1.getSortinoRatio());
			}
			
		};
	}
	
}
