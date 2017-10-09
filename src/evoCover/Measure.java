package evoCover;

import java.util.Comparator;

public interface Measure {

	public Double getValue(Portfolio p);
	
	public Comparator<Portfolio> getComparator();
	
}
