package measure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

import graph.*;

public class MeasureGraph {

	Graph<MeasureLog, MeasureLink> graph;
	String fileName;

	public MeasureGraph(String fileName) throws IOException {
		init(fileName);
		CalcMeasures();
		CalcCorrelationForAll();
	}

	private void init(String fileName) throws IOException {
		graph = new Graph<>();
		this.fileName = fileName;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String[] splitLine = line.split(",");

		MeasureLog row;
		while ((line = reader.readLine()) != null) {
			splitLine = line.split(",");
			row = new MeasureLog(splitLine[0]);
			for (int i = 1; i < splitLine.length; i++) {
				if (splitLine[i].equals("null")) {
					row.log.add(null);
				} else {
					row.log.add(Double.parseDouble(splitLine[i]));
				}
			}
			graph.addContent(row);
		}
		reader.close();
	}

	private void CalcMeasures() {
		for (Vertex<MeasureLog, MeasureLink> l : graph.getVertexList()) {
			l.getContent().solveNull();
			l.getContent().calcReturn();
			l.getContent().calcMean();
			l.getContent().calcStdDeviationAndVariance();
		}
	}

	private void CalcCorrelationForAll() {
		int j;
		MeasureLink link;
		int size = graph.getVertexList().size();
		for (int i = 0; i < size; i++) {
			j = i + 1;
			while (j < size) {
				link = new MeasureLink();
				link.correlation = graph.getContent(i).calcCorrelation(graph.getContent(j));
				graph.addRelation(i, j, link);
				graph.addRelation(j, i, link);
				j++;
			}
		}
		for (int i = 0; i < size; i++) {
			link = new MeasureLink();
			link.correlation = 1;
			graph.addRelation(i, i, link);
		}
	}

	public void writeCorrCSVFile(String fileName, double limit) throws IOException {
		int qtt = markEdges(limit);
		PrintWriter writer = new PrintWriter(fileName);

		for (int i = -1; i < qtt; i++) {
			writer.println("#vertexList: " + i);
			for (Vertex<MeasureLog, MeasureLink> vertex : this.graph.getVertexList()) {
				if (vertex.getContent().flag == i) {
					writer.println(vertex.getContent().tradeCode);
				}
			}
		}

		for (int i = -1; i < qtt; i++) {

			writer.println("#edgeList: " + i);
			for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
				if (edge.getRelation().flag == i) {
					writer.println(edge.getVertexA().getContent().tradeCode + ","
							+ edge.getVertexB().getContent().tradeCode + "," + edge.getRelation().correlation);
				}
			}
		}

		writer.close();
	}

	public void countLevelForAll() {
		int count;

		for (Vertex<MeasureLog, MeasureLink> v : graph.getVertexList()) {
			count = 0;
			for (Edge<MeasureLog, MeasureLink> edge : v.getEdgeList()) {
				if (edge.getRelation().flag != -1) {
					count++;
				}
			}
			v.getContent().level = count;

		}
	}
	
	public Comparator<Vertex<MeasureLog, MeasureLink>> getLevelAscComparator(){
		return new Comparator<Vertex<MeasureLog, MeasureLink>>() {
			@Override
			public int compare(Vertex<MeasureLog, MeasureLink> o1, Vertex<MeasureLog, MeasureLink> o2) {
				return o1.getContent().level.compareTo(o2.getContent().level);
			}
		};
	}
	
	public Comparator<Vertex<MeasureLog, MeasureLink>> getLevelDescComparator(){
		return new Comparator<Vertex<MeasureLog, MeasureLink>>() {
			@Override
			public int compare(Vertex<MeasureLog, MeasureLink> o1, Vertex<MeasureLog, MeasureLink> o2) {
				return o2.getContent().level.compareTo(o1.getContent().level);
			}
		};
	}

	public int markEdges(double limit) {
		for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
			if (edge.getRelation().correlation > limit) {
				edge.getRelation().flag = -1;
			}
		}

		countLevelForAll();
		PriorityQueue<Vertex<MeasureLog, MeasureLink>> sortedByLevelQ = new PriorityQueue<>(this.getLevelDescComparator());

		
		for (Vertex<MeasureLog, MeasureLink> v : graph.getVertexList()) {
			sortedByLevelQ.add(v);
		}
		
		PriorityQueue<Vertex<MeasureLog, MeasureLink>> sortedAdjQ;
		ArrayDeque<Vertex<MeasureLog, MeasureLink>> mainQ = new ArrayDeque<>();
		
		int mark = 0;
		int qtt = 0;
		Vertex<MeasureLog, MeasureLink> marked, sortedVertex, sortedAdjVertex;
		while(!sortedByLevelQ.isEmpty()){
        	sortedVertex = sortedByLevelQ.poll();
        	if (sortedVertex.getContent().flag == 0) {
				mark++;
				qtt = 20;
				sortedVertex.getContent().flag = mark;
				mainQ.add(sortedVertex);
		    }
			while (!mainQ.isEmpty()) {
				marked = mainQ.poll();
				sortedAdjQ = new PriorityQueue<>(this.getLevelDescComparator());
				
				for(Edge<MeasureLog, MeasureLink> edge : marked.getEdgeList()){
					if (edge.getRelation().flag != -1 && edge.getVertexB().getContent().flag == 0) {
						sortedAdjQ.add(edge.getVertexB());
					}
				}
				
				while( qtt>0  && !sortedAdjQ.isEmpty()){
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
