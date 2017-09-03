package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<C,R> {

	List<Vertex<C,R>> vertexAdjList;
	List<Edge<C,R>> edgeList;
	
	public List<Vertex<C,R>> getVertexList(){
		return this.vertexAdjList;
	}
	
	public Graph(){
		vertexAdjList = new ArrayList<>();
		edgeList = new ArrayList<>();
	}
	
	public List<Edge<C,R>> getEdgeList(){
		return edgeList;
	}
	
	public C getContent(int seqId){
		return vertexAdjList.get(seqId).content;
	}
	
	public void addContent(C c){
		vertexAdjList.add(new Vertex<C, R>(c));
	}
	
	public void addRelation(int i, int j, R relation){
		Edge<C,R> edge = new Edge<C, R>(vertexAdjList.get(i), vertexAdjList.get(j), relation);
		vertexAdjList.get(i).edgeList.add(edge);
		edgeList.add(edge);
	}
	
	public Edge<C, R> getEdge(int i, int j){
		return vertexAdjList.get(i).edgeList.get(j);
	}
	
	public R getRelation(int i, int j){
		return vertexAdjList.get(i).edgeList.get(j).relation;
	}
	
	
}
