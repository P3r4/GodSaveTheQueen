package evocover;

import java.util.Comparator;

public enum Measure {

	Delta{
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
	},
	HV{
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
	},
	Mean{
		@Override
		public Double getValue(Portfolio p) {
			return p.mean;
		}

		
		@Override
		public Comparator<Portfolio> getComparator() {
			return new Comparator<Portfolio>() {
				@Override
				public int compare(Portfolio o1, Portfolio o2) {
					return o2.mean.compareTo(o1.mean);
				}
			};
		}
	},DsRisk{
		@Override
		public Double getValue(Portfolio p) {
			return p.getDownsideRisk();
		}
		
		@Override
		public Comparator<Portfolio> getComparator() {
			return new Comparator<Portfolio>() {
				@Override
				public int compare(Portfolio o1, Portfolio o2) {
					Double s = Math.sqrt(o1.semiVar);
					return s.compareTo(Math.sqrt(o2.semiVar));
				}
			};
		}
	},Skewness{
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
	},SortinoRatio{
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
	};
	
	
	public abstract Double getValue(Portfolio p);
	
	public abstract Comparator<Portfolio> getComparator();
	
}
