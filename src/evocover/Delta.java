package evocover;

import java.util.Comparator;

public class Delta implements Measure{

		@Override
		public Double getValue(Portfolio p) {
			return p.getDelta();
		}
		
		@Override
		public Comparator<Portfolio> getComparator() {
			return new Comparator<Portfolio>() {
				@Override
				public int compare(Portfolio o1, Portfolio o2) {
					return o1.getDelta().compareTo(o2.getDelta());
				}
			};
		}
}
