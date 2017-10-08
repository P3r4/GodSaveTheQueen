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

	List<EvoCoverPortfolio> rankedList;
	
	public Rank(List<EvoCoverPortfolio> solutionList,
			Comparator<EvoCoverPortfolio> comp) {
		PriorityQueue<EvoCoverPortfolio> rankQ = new PriorityQueue<>(comp);
		for (EvoCoverPortfolio p : solutionList) {
			rankQ.add(p);
		}
		EvoCoverPortfolio p;
		List<EvoCoverPortfolio> rankedList = new ArrayList<>();
		while (!rankQ.isEmpty()) {
			p = rankQ.poll();
			rankedList.add(p);
		}
	}
	
	public EvoCoverPortfolio getFirst(){
		return rankedList.get(0);
	}
	
	public String formatResult(List<Vertex<EvoCoverLog, EvoCoverLink>> vertexList){
		DecimalFormat df = new DecimalFormat("#0.000000");
		double weight;
		String text = "";
		for (EvoCoverPortfolio p : rankedList) {
			text += p.id+","+df.format(p.getHyperVolume())+","+df.format(p.mean)+","+df.format(p.semiVar)+","+df.format(p.skewness);
			for (Vertex<EvoCoverLog, EvoCoverLink> v : vertexList) {
				weight =0;
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					weight += e.getRelation().coverList.get(p.id);
				}
				text += ","+v.getContent().tradeCode+","+df.format(weight);
			}
			text += "\n";
		}
		return text;
	}
	
	public EvoCoverPortfolio lottery(Measure m) {
		double rankTotal = 0;
		for (EvoCoverPortfolio p : rankedList) {
			rankTotal += m.getValue(p);
		}
		EvoCoverPortfolio p;
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
