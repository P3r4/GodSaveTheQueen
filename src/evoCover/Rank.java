package evoCover;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import graph.Edge;
import graph.Vertex;

public class Rank {

	List<Portfolio> rankedList;
	
	public Rank(List<Portfolio> solutionList,
			Comparator<Portfolio> comp) {
		PriorityQueue<Portfolio> rankQ = new PriorityQueue<>(comp);
		for (Portfolio p : solutionList) {
			rankQ.add(p);
		}
		Portfolio p;
		List<Portfolio> rankedList = new ArrayList<>();
		while (!rankQ.isEmpty()) {
			p = rankQ.poll();
			rankedList.add(p);
		}
	}
	
	public Portfolio getFirst(){
		return rankedList.get(0);
	}
	

	public Portfolio lottery(Measure m) {
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
