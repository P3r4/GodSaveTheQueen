package evocover;

import java.util.ArrayList;

import java.util.List;
import java.util.PriorityQueue;

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
	
}
