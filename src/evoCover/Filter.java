package evoCover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public class Filter {

	Graph<StockLog, CoverLink> graph;
	List<Integer> dayList;
	String fileName;

	public Filter(String fileName) throws IOException {
		init(fileName);
		initAllRelations();
	}

	public void markGraph(double corrTLimit, double meanBLimit, double semiVarTLimit) {

		for (Vertex<StockLog, CoverLink> vertex : graph.getVertexList()) {
			if (vertex.getContent().mean < meanBLimit && vertex.getContent().variance > semiVarTLimit) {
				vertex.getContent().flag = 1;
				System.out.println("mark1");
			} else if (vertex.getContent().mean < meanBLimit) {
				vertex.getContent().flag = 2;
				System.out.println("mark2");
			} else if (vertex.getContent().variance > semiVarTLimit) {
				vertex.getContent().flag = 3;
				System.out.println("mark3");
			} else {
				vertex.getContent().flag = 4;
				System.out.println("mark4");
			}
		}

		for (Edge<StockLog, CoverLink> edge : this.graph.getEdgeList()) {
			if (edge.getRelation().correlation > corrTLimit) {
				edge.getRelation().flag = 0;
			} else {
				edge.getRelation().flag = 1;
			}
		}

	}

	private void initAllRelations() {
		int j;
		CoverLink link;
		int size = graph.getVertexList().size();
		double corr;
		for (int i = 0; i < size; i++) {
			j = i + 1;
			while (j < size) {
				corr = graph.getContent(i).calcCorrelation(graph.getContent(j));
				link = new CoverLink();
				link.correlation = corr;
				graph.addRelation(i, j, link);
				link = new CoverLink();
				link.correlation = corr;
				graph.addRelation(j, i, link);
				j++;
			}
		}
	}

	public List<Vertex<StockLog, CoverLink>> getVertexList() {
		return graph.getVertexList();
	}

	public void normalize(int solutionId) {
		double total, normCover;
		total = 0;
		for (Edge<StockLog, CoverLink> e : graph.getEdgeList()) {
			CoverLink link = e.getRelation();
			total += link.coverList.get(solutionId);
		}
		for (Edge<StockLog, CoverLink> e : graph.getEdgeList()) {
			CoverLink link = e.getRelation();
			normCover = link.coverList.get(solutionId) / total;
			link.coverList.set(solutionId, normCover);
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
		StockLog log;
		while (((line = reader.readLine()) != null)) {
			log = new StockLog(line);
			graph.addContent(log);
		}
		reader.close();
	}

	public void writeFilteredCSVFile(String dirName, double corrTLimit, double meanBLimit, double semiVarTLimit)
			throws IOException {
		PrintWriter writer;
		int k;
		String text, head;
		head = "tradeCode";
		for (Integer day : dayList) {
			head += "," + day;
		}
		for (int i = 1; i < 5; i++) {
			text = head + "\n";
			k = 0;
			for (Vertex<StockLog, CoverLink> vertex : this.graph.getVertexList()) {
				if (vertex.getContent().flag == i) {
					k++;
					text += vertex.getContent().toString();
					text += "\n";
				}
			}
			if (k > 3) {
				writer = new PrintWriter(dirName + "part" + i + ".csv");
				writer.print(text);
				writer.close();
			}
		}
	}
	

	public void countLevelForAll() {
		int count;

		for (Vertex<StockLog, CoverLink> v : graph.getVertexList()) {
			count = 0;
			for (Edge<StockLog, CoverLink> edge : v.getEdgeList()) {
				if (edge.getRelation().flag != -1 && edge.getVertexB().getContent().flag != -1) {
					count++;
				}
			}
			v.getContent().level = count;

		}
	}

	public Comparator<Vertex<StockLog, CoverLink>> getLevelAscComparator() {
		return new Comparator<Vertex<StockLog, CoverLink>>() {
			@Override
			public int compare(Vertex<StockLog, CoverLink> o1, Vertex<StockLog, CoverLink> o2) {
				return o1.getContent().level.compareTo(o2.getContent().level);
			}
		};
	}

	public Comparator<Vertex<StockLog, CoverLink>> getLevelDescComparator() {
		return new Comparator<Vertex<StockLog, CoverLink>>() {
			@Override
			public int compare(Vertex<StockLog, CoverLink> o1, Vertex<StockLog, CoverLink> o2) {
				return o2.getContent().level.compareTo(o1.getContent().level);
			}
		};
	}

	public int divideGraph(double corrTLimit, double meanBLimit, double semiVarTLimit) {
	
		int initialQtt = 19;
		countLevelForAll();
		PriorityQueue<Vertex<StockLog, CoverLink>> sortedByLevelQ = new PriorityQueue<>(this.getLevelDescComparator());

		for (Vertex<StockLog, CoverLink> v : graph.getVertexList()) {
			sortedByLevelQ.add(v);
		}

		PriorityQueue<Vertex<StockLog, CoverLink>> sortedAdjQ;
		ArrayDeque<Vertex<StockLog, CoverLink>> mainQ = new ArrayDeque<>();

		int mark = 0;
		int qtt = 0;
		Vertex<StockLog, CoverLink> marked, sortedVertex, sortedAdjVertex;
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

				for (Edge<StockLog, CoverLink> edge : marked.getEdgeList()) {
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
