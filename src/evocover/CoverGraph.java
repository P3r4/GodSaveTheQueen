package evocover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import graph.*;

public class CoverGraph {

	List<Portfolio> solutionList;
	Graph<StockLog, Cover> graph;
	List<Integer> dayList;
	String fileName;
	int solutionQtt;
	String plotData;

	public CoverGraph(int sqtt, String fileName) throws IOException {
		initGraph(fileName);
		initAllRelations();
		solutionQtt = sqtt;
		solutionList = new ArrayList<>();
		for (int i = 0; i < solutionQtt; i++) {
			solutionList.add(new Portfolio(i));
		}
		
	}

	private void initGraph(String fileName) throws IOException {
		graph = new Graph<>();
		dayList = new ArrayList<>();
		this.fileName = fileName;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String[] splitLine = line.split(",");
		for (int i = 1; i < splitLine.length; i++) {
			dayList.add(Integer.parseInt(splitLine[i]));
		}
		StockLog log;
		while (((line = reader.readLine()) != null)) {
			log = new StockLog(line);
			graph.addContent(log);
		}
		reader.close();
	}

	private void initAllRelations() {
		int j;
		Cover link;
		int size = graph.getVertexList().size();
		double corr;
		for (int i = 0; i < size; i++) {
			j = i + 1;
			while (j < size) {
				corr = graph.getContent(i).calcCorrelation(graph.getContent(j));
				link = new Cover();
				link.correlation = corr;
				graph.addRelation(i, j, link);
				link = new Cover();
				link.correlation = corr;
				graph.addRelation(j, i, link);
				j++;
			}
		}
	}

	public void randomOne(int wQtt, int id) {
		Integer r;
		for (int k = 0; k < wQtt; k++) {
			r = new Random().nextInt(graph.getVertexList().size());
			for (Edge<StockLog, Cover> e : graph.getVertexList().get(r).getEdgeList()) {
				Cover link = e.getRelation();
				link.coverList.set(id, Math.pow(new Random().nextInt(10) + 1, new Random().nextInt(4)));
			}
		}

		normalize(id);
	}

	public void normalize(int solutionId) {
		double total, normCover;
		total = 0;
		for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
			Cover link = e.getRelation();
			total += link.coverList.get(solutionId);
		}
		for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
			Cover link = e.getRelation();
			normCover = link.coverList.get(solutionId) / total;
			link.coverList.set(solutionId, normCover);
		}
	}

	public void normalizeAll() {
		for (Portfolio p : solutionList) {
			normalize(p.id);
		}
	}

	public void calcMaxAndMinForAll() {
		for (Portfolio p : solutionList) {
			calcMaxAndMin(p);
		}
	}

	public void calcMaxAndMin(Portfolio p) {
		double w;
		w = 0;
		Vertex<StockLog, Cover> v0 = graph.getVertexList().get(0);
		for (Edge<StockLog, Cover> e : v0.getEdgeList()) {
			w += e.getRelation().coverList.get(p.id);
		}
		p.maxW = w;
		p.minW = w;
		for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
			w = 0;
			for (Edge<StockLog, Cover> e : v.getEdgeList()) {
				w += e.getRelation().coverList.get(p.id);
			}
			if (p.maxW < w)
				p.maxW = w;
			if (p.minW > w)
				p.minW = w;
		}

	}
	
	public void calcSemiVarAndSkewness(Portfolio p) {
		double term, semiTerm, skewTerm, varTerm, weight;
		List<Double> dayReturnList;
		dayReturnList = new ArrayList<>();
		for (int i = 0; i < dayList.size(); i++) {
			dayReturnList.add(0.0);
		}
		for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
			weight = 0;
			for (Edge<StockLog, Cover> e : v.getEdgeList()) {
				weight += e.getRelation().coverList.get(p.id);
			}
			int t = 0;
			for (Double r : v.getContent().returnLog) {
				term = r * weight + dayReturnList.get(t);
				dayReturnList.set(t, term);
				t++;
			}
		}
		semiTerm = 0;
		skewTerm = 0;
		varTerm = 0;
		for (Double d : dayReturnList) {
			semiTerm += Math.min(0, (d - p.mean)) * Math.min(0, (d - p.mean));
			varTerm += (d - p.mean) * (d - p.mean);
		}
		p.semiVar = semiTerm / (dayReturnList.size() - 1);
		varTerm /= (dayReturnList.size() - 1);
		for (Double d : dayReturnList) {
			skewTerm += Math.pow((d - p.mean) / Math.pow(varTerm, 1 / 2), 3);
		}
		p.semiVar = semiTerm / (dayReturnList.size() - 1);
		p.skewness = skewTerm / (dayReturnList.size() - 1);
	}

	public void calcSemiVarAndSkewnessForAll() {
		calcMeanReturnForAll();
		for (Portfolio p : solutionList) {
			calcSemiVarAndSkewness(p);
		}
	}

	public void calcMeanReturnForAll() {
		for (Portfolio p : solutionList) {
			calcMeanReturn(p);
		}
	}

	public void calcMeanReturn(Portfolio p) {
		double term, weight;
		term = 0;
		for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
			weight = 0;
			for (Edge<StockLog, Cover> e : v.getEdgeList()) {
				weight += e.getRelation().coverList.get(p.id);
			}
			term += (v.getContent().mean * weight);
		}
		p.mean = term;
	}


	public List<Portfolio> getSolutionList() {
		return solutionList;
	}


	public List<Vertex<StockLog, Cover>> getVertexList() {
		return graph.getVertexList();
	}

	
	public void zeroOne(int id){
		for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
			e.getRelation().coverList.set(id,0.0);
		}
	}

	public Portfolio lottery(Measure m) {
		double total = 0;
		double minMe, maxMe;
		Portfolio p;
		minMe = m.getValue(solutionList.get(0));
		maxMe = m.getValue(solutionList.get(0));

		for (int i = 0; i < solutionQtt; i++) {
			p = solutionList.get(i);
			if (m.getValue(p) > maxMe) {
				maxMe = m.getValue(p);
			}
			if (m.getValue(p) < minMe) {
				minMe = m.getValue(p);
			}
		}

		minMe -= 0.01;

		for (int i = 0; i < solutionQtt; i++) {
			p = solutionList.get(i);
			// System.out.println((m.getValue(p)-minMe));
			total += (m.getValue(p) - minMe);
		}
		double limit = new Random().nextDouble() * total;
		double part = 0;
		int i = 0;
		Portfolio pOut;
		do {
			pOut = solutionList.get(i);
			part += (m.getValue(pOut) - minMe);
			i++;
		} while ((i < solutionQtt) && (part < limit));
		//System.out.println(pOut.id);
		return pOut;
	}


	
	public void calcAllMeasures(Portfolio p){
		calcMeanReturn(p);
		calcSemiVarAndSkewness(p);
		calcMaxAndMin(p);
	}

	public String formatHeader(Measure measure) {
		String header = "id," + measure.toString() + ",Mean,DownSideRisk";
		for (Vertex<StockLog, Cover> v : graph.getVertexList())
			header += "," + v.getContent().tradeCode;
		header += "\n";
		return header;
	}
	
	

	public String formatResult(Measure measure) {
		Rank rank = new Rank(solutionList, measure);
		DecimalFormat df = new DecimalFormat("#0.000000");
		double weight;
		String text = "";

		Portfolio p;

		for (int i = 0; i < 3; i++) {
			p = rank.rankedList.get(i);
			text += p.id + "," + df.format(measure.getValue(p)) + "," + df.format(p.mean) + "," + df.format(p.getDownsideRisk());
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				weight = 0;
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					weight += e.getRelation().coverList.get(p.id);
				}
				text += "," + df.format(weight);
			}
			text += "\n";
		}
		return text;
	}
	
	
	public String formatReview(Review review, Measure measure){
		Rank rank = new Rank(solutionList, measure);
		DecimalFormat df = new DecimalFormat("#0.000000");
		double weight;
		String text = "";

		Portfolio p;
		for (int i = 0; i < 3; i++) {
			p = rank.rankedList.get(i);
			review.reset();
			text += p.id + "," + df.format(measure.getValue(p)) + "," + df.format(p.mean) + "," + df.format(p.getDownsideRisk());
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				weight = 0;
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					weight += e.getRelation().coverList.get(p.id);
				}
				review.addWeight(weight, v.getContent().tradeCode);
			}
			text += review.formatPortfolioReturn();
			
			text += "\n";
		}
		return text;
	}

	public String getMeanAndSemiVar() {
		String out = "";
		for (Portfolio p : solutionList) {
			out += p.mean + "," + p.semiVar + "\n";
		}
		return out;
	}

	public void printResult(Measure measure) {
		System.out.print(formatResult(measure));
		System.out.println("------------");
	}

	public void printResult(String resultFile, Measure measure) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(resultFile);
		writer.print(formatResult(measure));
		writer.close();
	}

	public void fixGraph(int coupleQtt, Measure m) {
		calcSemiVarAndSkewnessForAll();
		calcMaxAndMinForAll();
		Rank rank = new Rank(solutionList, m);
		swapCoverListValues(rank.rankedList, buildSwapList(coupleQtt, rank.rankedList));
		solutionList = rank.rankedList;
		fixCoverListForAllEdges(coupleQtt);
	}

	public List<Portfolio> buildSwapList(int coupleQtt, List<Portfolio> rankedList) {
		Portfolio p2;
		List<Portfolio> swapList = new ArrayList<>();
		for (int i = 0; i < coupleQtt * 2; i++) {
			p2 = rankedList.remove(rankedList.size() - 1);
			if (p2.id < solutionQtt) {
				swapList.add(p2);
			}
		}
		return swapList;
	}

	public void swapCoverListValues(List<Portfolio> rankedList, List<Portfolio> swapList) {
		int k = 0;
		for (Portfolio p : rankedList) {
			if (p.id >= solutionQtt) {
				for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
					e.getRelation().coverList.set(swapList.get(k).id, e.getRelation().coverList.get(p.id));
				}
				p.id = swapList.get(k).id;
				k++;
			}
		}
	}

	public void fixCoverListForAllEdges(int coupleQtt) {
		for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
			for (int i = 0; i < coupleQtt * 2; i++) {
				e.getRelation().coverList.remove(e.getRelation().coverList.size() - 1);
			}
		}
	}
	
	
    //[15]
	public void randomInit(int wQtt) {

		for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
			Cover link = e.getRelation();
			for (int i = 0; i < solutionQtt; i++) {
				link.coverList.add(0.0);
			}
		}

		for (int i = 0; i < solutionQtt; i++) {
			randomOne(wQtt,i);
		}
		calcSemiVarAndSkewnessForAll();
		calcMaxAndMinForAll();
	}
	
	// [20]
	public void onlookerBeePhase20(Measure measure) {
		Portfolio p2;
		for (Portfolio p1 : solutionList) {
			p2 = lottery(measure);
			if (measure.getComparator().compare(p1, p2) > 0) {
				System.out.println(p2.id + " onlooker");
				for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
					e.getRelation().coverList.set(p1.id, e.getRelation().coverList.get(p2.id));
				}
				p1.trail = 0;
				calcAllMeasures(p1);
			}
		}
	}

	public void scoutBeePhase20(int wQtt, int limit) {
		for (int i = 0; i < solutionQtt; i++) {
			if (solutionList.get(i).trail > limit) {
				System.out.println("scout");
				solutionList.get(i).trail = 0;
				zeroOne(solutionList.get(i).id);
				randomOne(wQtt, solutionList.get(i).id);
				normalize(solutionList.get(i).id);
				calcAllMeasures(solutionList.get(i));
			}
		}
		
	}

	// [20]
	public void employedBeePhase20(int c, Measure measure) {
		
		double newCover;
		Rank rank = new Rank(solutionList, measure);
		Portfolio p1, p2, pNew;
		int bestId = rank.getFirst().id;

		for (int i = 0; i < solutionList.size(); i++) {
			p1 = solutionList.get(i);
			p2 = lottery(measure);
			p1.trail++;
			for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
				newCover = e.getRelation().coverList.get(p1.id)
						+ new Random().nextDouble()
								* (e.getRelation().coverList.get(p1.id) - e.getRelation().coverList.get(p2.id))
						+ new Random().nextInt(c + 1)
								* (e.getRelation().coverList.get(bestId) - e.getRelation().coverList.get(p1.id));
				e.getRelation().coverList.add(Math.max(0, newCover));
				// System.out.println(newCover);
			}
			pNew = new Portfolio(graph.getEdgeList().get(0).getRelation().coverList.size() - 1);
			normalize(pNew.id);
			calcAllMeasures(pNew);
			int rmv = pNew.id;
			if (measure.getComparator().compare(p1, pNew) > 0) {
				for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
					e.getRelation().coverList.set(p1.id, e.getRelation().coverList.get(pNew.id));
				}
				pNew.trail = 0;
				pNew.id = p1.id;
				solutionList.set(i, pNew);
			}
			for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
				e.getRelation().coverList.remove(rmv);
			}
		}
	}

	// [10]
	public void crossOver10(int coupleQtt, Measure measure) {
		Portfolio solution1, solution2;
		int id, i = 0;
		double cover1, cover2, rand, beta;
		while (i < coupleQtt) {
			i++;
			solution1 = lottery(measure);
			solution2 = lottery(measure);
			rand = new Random().nextDouble();
			id = graph.getEdgeList().get(0).getRelation().coverList.size();
			for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
				cover1 = e.getRelation().coverList.get(solution1.id);
				cover2 = e.getRelation().coverList.get(solution2.id);
				if (rand <= 0.5)
					beta = Math.pow(2 * rand, 1 / (1 + graph.getEdgeList().size()));
				else
					beta = Math.pow(1 / (2 * (1 - rand)), 1 / (1 + graph.getEdgeList().size()));
				e.getRelation().coverList.add((cover1 * (1 + beta) + cover2 * (1 - beta)) / 2);
				e.getRelation().coverList.add((cover1 * (1 - beta) + cover2 * (1 + beta)) / 2);
			}
			solutionList.add(new Portfolio(id));
			solutionList.add(new Portfolio(id + 1));
			normalize(id);
			normalize(id + 1);
		}
		fixGraph(coupleQtt, measure);
	}

	// [16]
	public void crossOver16(int coupleQtt, Measure measure) {
		calcSemiVarAndSkewnessForAll();
		calcMaxAndMinForAll();
		Portfolio solution1, solution2;
		int id, i = 0;
		double c1, c2, rand;
		while (i < coupleQtt) {
			i++;
			solution1 = lottery(measure);
			solution2 = lottery(measure);
			rand = new Random().nextDouble();
			id = graph.getEdgeList().get(0).getRelation().coverList.size();
			for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
				c1 = e.getRelation().coverList.get(solution1.id);
				c2 = e.getRelation().coverList.get(solution2.id);
				e.getRelation().coverList.add(rand * c1 + (1 - rand) * c2);
				e.getRelation().coverList.add(rand * c2 + (1 - rand) * c1);
			}
			solutionList.add(new Portfolio(id));
			solutionList.add(new Portfolio(id + 1));
			normalize(id);
			normalize(id + 1);
		}
		fixGraph(coupleQtt, measure);
	}

	// [10]
	public void mutation10() {
		double rand, deltaQ, delta, cover, newCover;
		double chance = 1.0 / graph.getEdgeList().size();
		int i;
		for (Portfolio p : solutionList) {
			i = 0;
			for (Edge<StockLog, Cover> e1 : graph.getEdgeList()) {
				if (new Random().nextDouble() < chance) {
					rand = new Random().nextDouble();
					cover = e1.getRelation().coverList.get(p.id);
					if (rand <= 0.5) {
						delta = (cover - p.minW) / (p.maxW - p.minW);
						deltaQ = Math.pow(2 * rand + (1 - 2 * rand) * Math.pow((1 - delta), i + 1), 1 / (i + 1)) - 1;
					} else {
						delta = (p.maxW - cover) / (p.maxW - p.minW);
						deltaQ = 1 - Math.pow(2 * (1 - rand) + 2 * (rand - 0.5) * Math.pow((1 - delta), i + 1),
								1 / (i + 1));
					}
					newCover = cover + deltaQ * (p.maxW - p.minW);
					e1.getRelation().coverList.set(p.id, newCover);

				}
				i++;
			}
			normalize(p.id);
			calcAllMeasures(p);
		}
	}

	// [15]
	public void mutation15() {
		double tradeE1, tradeE2;
		int e2Id;
		double chance = 1.0 / graph.getEdgeList().size();
		for (Portfolio p : solutionList) {
			for (Edge<StockLog, Cover> e1 : graph.getEdgeList()) {
				if (new Random().nextDouble() < chance) {
					tradeE1 = e1.getRelation().coverList.get(p.id);
					e2Id = new Random().nextInt(graph.getEdgeList().size());
					tradeE2 = graph.getEdgeList().get(e2Id).getRelation().coverList.get(p.id);
					e1.getRelation().coverList.set(p.id, tradeE2);
					graph.getEdgeList().get(e2Id).getRelation().coverList.set(p.id, tradeE1);
				}
			}
			normalize(p.id);
			calcAllMeasures(p);
		}
	}

	// [16]
	public void mutation16(double chance) {
		double newCover;
		for (Portfolio p : solutionList) {
			if (new Random().nextDouble() < chance) {
				for (Edge<StockLog, Cover> e : graph.getEdgeList()) {
					newCover = e.getRelation().coverList.get(p.id) * (new Random().nextDouble()) * 2;
					e.getRelation().coverList.set(p.id, newCover);
				}
			}
			normalize(p.id);
			calcAllMeasures(p);
		}
	}


}
