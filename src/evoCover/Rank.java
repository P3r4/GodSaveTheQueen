package evoCover;

import java.util.ArrayList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Rank {

	List<Portfolio> rankedList;
	Measure m;
	public Rank(List<Portfolio> solutionList,
			Measure measure) {
		this.m = measure;
		rankedList = new ArrayList<Portfolio>();
		PriorityQueue<Portfolio> rankQ = new PriorityQueue<>(m.getComparator());
		for (Portfolio p : solutionList) {
			rankQ.add(p);
		}
		Portfolio p;
		while (!rankQ.isEmpty()) {
			p = rankQ.poll();
			rankedList.add(p);
		}
	}
	
	public Portfolio getFirst(){
		return rankedList.get(0);
	}
	

	public Portfolio lottery() {
		double rankTotal = 0;
		for (Portfolio p : rankedList) {
			rankTotal += m.getValue(p);
		}
		Portfolio p;
		double limit = Math.abs((new Random().nextDouble()) * rankTotal);
		double part = 0;
		int i = 0;
		do {
			p = rankedList.get(i);
			part +=  m.getValue(p);
			i++;
		} while ((i < rankedList.size()) && (part < limit));
		return p;
	}

	

	
}
