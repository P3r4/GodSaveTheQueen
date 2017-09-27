package evoCover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import graph.*;

public class EvoCoverGraph {

	List<EvoCoverPortfolio> solutionList;
	Graph<EvoCoverLog, EvoCoverLink> graph;
	List<Integer> dayList;
	String fileName;
	int solutionQtt;

	public EvoCoverGraph(int qtt, String fileName) throws IOException {
		solutionQtt = qtt;
		solutionList = new ArrayList<>();
		for (int i = 0; i < solutionQtt; i++) {
			solutionList.add(new EvoCoverPortfolio(i));
		}
		init(fileName);
		initAllRelations();
	}

	public List<EvoCoverPortfolio> getSolutionList() {
		return solutionList;
	}

	public void markGraph(double corrTLimit, double meanBLimit, double semiVarTLimit) {

		for (Vertex<EvoCoverLog, EvoCoverLink> vertex : graph.getVertexList()) {
			if (vertex.getContent().mean < meanBLimit && vertex.getContent().variance > semiVarTLimit) {
				vertex.getContent().flag = -1;
			} else {
				vertex.getContent().flag = 0;
			}
		}

		for (Edge<EvoCoverLog, EvoCoverLink> edge : this.graph.getEdgeList()) {
			if (edge.getRelation().correlation > corrTLimit) {
				edge.getRelation().flag = -1;
			} else {
				edge.getRelation().flag = 0;
			}
		}

	}

	private void initAllRelations() {
		int j;
		EvoCoverLink link;
		int size = graph.getVertexList().size();
		double corr;
		for (int i = 0; i < size; i++) {
			j = i + 1;
			while (j < size) {
				corr = graph.getContent(i).calcCorrelation(graph.getContent(j));
				link = new EvoCoverLink();
				link.correlation = corr;
				graph.addRelation(i, j, link);
				link = new EvoCoverLink();
				link.correlation = corr;
				graph.addRelation(j, i, link);
				j++;
			}
		}
	}

	public List<Vertex<EvoCoverLog, EvoCoverLink>> getVertexList() {
		return graph.getVertexList();
	}

	public void randomInit() {
		for (Edge<EvoCoverLog, EvoCoverLink> e : graph.getEdgeList()) {
			EvoCoverLink link = e.getRelation();
			for (int i = 0; i < solutionQtt; i++) {
				link.coverList.add(new Random().nextDouble());
			}
		}
		normalizeAll();
	}

	public void normalize(int solutionId) {
		double total, normCover;
		total = 0;
		for (Edge<EvoCoverLog, EvoCoverLink> e : graph.getEdgeList()) {
			EvoCoverLink link = e.getRelation();
			total += link.coverList.get(solutionId);
		}
		for (Edge<EvoCoverLog, EvoCoverLink> e : graph.getEdgeList()) {
			EvoCoverLink link = e.getRelation();
			normCover = link.coverList.get(solutionId) / total;
			link.coverList.set(solutionId, normCover);
		}
	}

	public void normalizeAll() {
		for (EvoCoverPortfolio p : solutionList) {
			normalize(p.id);
		}
	}

	public void maxAndMin() {
		double w;
		for (EvoCoverPortfolio p : solutionList) {
			for (Edge<EvoCoverLog, EvoCoverLink> e : graph.getEdgeList()) {
				w = e.getRelation().coverList.get(p.id);
				if (p.maxW < w)
					p.maxW = w;
				if (p.minW > w)
					p.minW = w;
			}
		}
	}

	// [10]
	public void geneticMutation3() {
		maxAndMin();
		double rand, deltaQ, delta, cover, newCover;
		double chance = 1 / (graph.getEdgeList().size());
		int i;
		for (EvoCoverPortfolio p : solutionList) {
			i = 0;
			for (Edge<EvoCoverLog, EvoCoverLink> e1 : graph.getEdgeList()) {
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
				}
				i++;
			}
		}
	}

	// [15]
	public void geneticMutation1() {
		double tradeE1, tradeE2;
		int e2Id;
		double chance = 1 / (graph.getEdgeList().size());
		for (EvoCoverPortfolio p : solutionList) {
			for (Edge<EvoCoverLog, EvoCoverLink> e1 : graph.getEdgeList()) {
				if (new Random().nextDouble() < chance) {
					tradeE1 = e1.getRelation().coverList.get(p.id);
					e2Id = new Random().nextInt(graph.getEdgeList().size());
					tradeE2 = graph.getEdgeList().get(e2Id).getRelation().coverList.get(p.id);
					e1.getRelation().coverList.set(p.id, tradeE2);
					graph.getEdgeList().get(e2Id).getRelation().coverList.set(p.id, tradeE1);
				}
			}
		}
	}

	// [16]
	public void geneticMutation2(double chance) {
		double newCover;
		for (EvoCoverPortfolio p : solutionList) {
			if (new Random().nextDouble() < chance) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : graph.getEdgeList()) {
					newCover = e.getRelation().coverList.get(p.id) * (new Random().nextDouble()) * 2;
					e.getRelation().coverList.set(p.id, newCover);
				}

			}
			normalize(p.id);
		}
	}

	public void calcMeanReturnForAll() {
		double term, weight;
		for (EvoCoverPortfolio p : solutionList) {
			// System.out.println("solution-" + p.id);
			term = 0;

			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				weight = 0;
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					weight += e.getRelation().coverList.get(p.id);
				}

				// System.out.println(df.format(v.getContent().mean * weight));
				term += (v.getContent().mean * weight);
			}

			p.mean = term;
			System.out.println(term);

		}
	}

	private void init(String fileName) throws IOException {
		graph = new Graph<>();
		dayList = new ArrayList<>();
		this.fileName = fileName;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String[] splitLine = line.split(",");
		for (int i = 1; i < splitLine.length; i++) {
			dayList.add(Integer.parseInt(splitLine[i]));
		}
		EvoCoverLog log;
		while (((line = reader.readLine()) != null)) {
			log = new EvoCoverLog(line);
			graph.addContent(log);
		}
		reader.close();
	}

	public void writeFilteredCSVFile(String dirName, double corrTLimit, double meanBLimit, double semiVarTLimit)
			throws IOException {
		int qtt = divideGraph(corrTLimit, meanBLimit, semiVarTLimit);
		PrintWriter writer;
		int k;
		String text, head;
		head = "tradeCode";
		for (Integer day : dayList) {
			head += "," + day;
		}
		for (int i = -1; i < qtt; i++) {
			text = head + "\n";
			k = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> vertex : this.graph.getVertexList()) {
				if (vertex.getContent().flag == i) {
					k++;
					text += vertex.getContent().toString();
					text += "\n";
				}
			}

			if (k > 7) {
				writer = new PrintWriter(dirName + "part" + i + ".csv");
				writer.print(text);
				writer.close();
			}
		}
	}

	public void countLevelForAll() {
		int count;

		for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
			count = 0;
			for (Edge<EvoCoverLog, EvoCoverLink> edge : v.getEdgeList()) {
				if (edge.getRelation().flag != -1 && edge.getVertexB().getContent().flag != -1) {
					count++;
				}
			}
			v.getContent().level = count;

		}
	}

	public Comparator<Vertex<EvoCoverLog, EvoCoverLink>> getLevelAscComparator() {
		return new Comparator<Vertex<EvoCoverLog, EvoCoverLink>>() {
			@Override
			public int compare(Vertex<EvoCoverLog, EvoCoverLink> o1, Vertex<EvoCoverLog, EvoCoverLink> o2) {
				return o1.getContent().level.compareTo(o2.getContent().level);
			}
		};
	}

	public Comparator<Vertex<EvoCoverLog, EvoCoverLink>> getLevelDescComparator() {
		return new Comparator<Vertex<EvoCoverLog, EvoCoverLink>>() {
			@Override
			public int compare(Vertex<EvoCoverLog, EvoCoverLink> o1, Vertex<EvoCoverLog, EvoCoverLink> o2) {
				return o2.getContent().level.compareTo(o1.getContent().level);
			}
		};
	}

	public int divideGraph(double corrTLimit, double meanBLimit, double semiVarTLimit) {
		markGraph(corrTLimit, meanBLimit, semiVarTLimit);
		int initialQtt = 20;
		countLevelForAll();
		PriorityQueue<Vertex<EvoCoverLog, EvoCoverLink>> sortedByLevelQ = new PriorityQueue<>(
				this.getLevelDescComparator());

		for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
			sortedByLevelQ.add(v);
		}

		PriorityQueue<Vertex<EvoCoverLog, EvoCoverLink>> sortedAdjQ;
		ArrayDeque<Vertex<EvoCoverLog, EvoCoverLink>> mainQ = new ArrayDeque<>();

		int mark = 0;
		int qtt = 0;
		Vertex<EvoCoverLog, EvoCoverLink> marked, sortedVertex, sortedAdjVertex;
		while (!sortedByLevelQ.isEmpty()) {
			sortedVertex = sortedByLevelQ.poll();
			if (sortedVertex.getContent().flag == 0) {
				mark++;
				qtt = initialQtt;
				sortedVertex.getContent().flag = mark;
				mainQ.add(sortedVertex);
			}
			while (!mainQ.isEmpty()) {
				marked = mainQ.poll();
				sortedAdjQ = new PriorityQueue<>(this.getLevelDescComparator());

				for (Edge<EvoCoverLog, EvoCoverLink> edge : marked.getEdgeList()) {
					if (edge.getRelation().flag != -1 && edge.getVertexB().getContent().flag == 0) {
						sortedAdjQ.add(edge.getVertexB());
					}
				}

				while (qtt > 0 && !sortedAdjQ.isEmpty()) {
					sortedAdjVertex = sortedAdjQ.poll();
					sortedAdjVertex.getContent().flag = mark;
					mainQ.add(sortedAdjVertex);
					qtt--;
				}
			}

		}

		return mark + 1;
	}

}
