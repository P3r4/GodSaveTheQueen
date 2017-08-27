package measure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

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
		int qtt = markEdgesAboveLimit(limit);
		PrintWriter writer = new PrintWriter(fileName);

		for (int i = -1; i < qtt; i++) {
			writer.println("#vertexList: " + i);
			for (Vertex<MeasureLog, MeasureLink> vertex : this.graph.getVertexList()) {
				if (vertex.getContent().flag == i) {
					writer.println(vertex.getSeqId() + " " + vertex.getContent().tradeCode);
				}
			}
		}

		writer.println("#edgeList: ");
		for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
			writer.println(edge.getVertexA().getSeqId() + " " + edge.getVertexB().getSeqId() + " "
					+ edge.getRelation().correlation);
		}

		writer.close();
	}

	public int markEdgesAboveLimit(double limit) {
		for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
			if (edge.getRelation().correlation > limit) {
				edge.getRelation().flag = -1;
			}
		}

		Stack<Vertex<MeasureLog, MeasureLink>> s = new Stack<>();
		int mark = 0;
		Vertex<MeasureLog, MeasureLink> marked;
		for (Vertex<MeasureLog, MeasureLink> v : graph.getVertexList()) {
			mark++;
			if (v.getContent().flag == 0) {
				v.getContent().flag = mark;
				s.push(v);
			}
			while (!s.isEmpty()) {
				marked = s.pop();
				System.out.println(marked.getSeqId());
				for (Edge<MeasureLog, MeasureLink> edge : marked.getEdgeList()) {
					System.out.println(edge.getRelation().flag + " " + edge.getRelation().correlation + " "
							+ edge.getVertexB().getContent().flag + " " + edge.getVertexB().getSeqId());

					if (edge.getRelation().flag == 0 && edge.getVertexB().getContent().flag == 0) {
						edge.getRelation().flag = mark;
						edge.getVertexB().getContent().flag = mark;
						s.push(edge.getVertexB());
					}
				}
				System.out.println(">>>>>>" + s.size());

			}

		}

		return mark;
	}

}
