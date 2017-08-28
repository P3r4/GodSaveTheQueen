package measure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
		int qtt = markEdges(limit);
		PrintWriter writer = new PrintWriter(fileName);

		for (int i = -1; i < qtt; i++) {
			writer.println("#vertexList: " + i);
			for (Vertex<MeasureLog, MeasureLink> vertex : this.graph.getVertexList()) {
				if (vertex.getContent().flag == i) {
					writer.println(vertex.getSeqId() + "," + vertex.getContent().tradeCode);
				}
			}
		}

		for (int i = -1; i < qtt; i++) {

			writer.println("#edgeList: " + i);
			for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
				if (edge.getRelation().flag == i) {
					writer.println(edge.getVertexA().getSeqId() + "," + edge.getVertexB().getSeqId() + ","
							+ edge.getRelation().correlation);
				}
			}
		}

		writer.close();
	}

	public void insertSorted(int [][] pairs,  int [] pair){
		int i = 0;
		while(i < pairs.length && pairs[i][1] <= pair[1]){
			i++;
		}
		if(i < pairs.length){
			
		}
	}
	
	public void countLevelForAll(){
		int count;
		float x=0;
		
		for (Vertex<MeasureLog, MeasureLink> v : graph.getVertexList()) {
			count = 0;
			for (Edge<MeasureLog, MeasureLink> edge : v.getEdgeList()) {
				if(edge.getRelation().flag != -1){
					count++;
				}
			}
			v.getContent().level = count;
			x += count;
			
		}
		System.out.println(x/graph.getVertexList().size());
	}
	
	
	public int markEdges(double limit) {
		for (Edge<MeasureLog, MeasureLink> edge : this.graph.getEdgeList()) {
			if (edge.getRelation().correlation > limit) {
				edge.getRelation().flag = -1;
			}
		}

		countLevelForAll();
		
		
		Stack<Vertex<MeasureLog, MeasureLink>> s = new Stack<>();
		int mark = 0;
		Vertex<MeasureLog, MeasureLink> marked;
		//System.out.println(graph.getVertexList().size());
		for (Vertex<MeasureLog, MeasureLink> v : graph.getVertexList()) {

			if (v.getContent().flag == 0) {
				mark++;
				v.getContent().flag = mark;
				s.push(v);
			}
			while (!s.isEmpty()) {
				marked = s.pop();
			//	System.out.println(marked.getSeqId());
				for (Edge<MeasureLog, MeasureLink> edge : marked.getEdgeList()) {
				//	System.out.println(edge.getRelation().flag + " " + edge.getRelation().correlation + " "
				//			+ edge.getVertexB().getContent().flag + " " + edge.getVertexB().getSeqId());

					if (edge.getRelation().flag == 0 && edge.getVertexB().getContent().flag == 0) {
						edge.getVertexB().getContent().flag = mark;
						s.push(edge.getVertexB());
					} else if (edge.getRelation().flag > -1) {
						edge.getRelation().flag = mark;
					}
				}
				//System.out.println(">>>>>>" + s.size());

			}

		}

		return mark + 1;
	}

}
