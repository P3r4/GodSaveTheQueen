package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<C,R> {

	C content;
	List<Edge<C,R>> edgeList;
	
	public Vertex(C content){
		this.content = content;
		edgeList =  new ArrayList<>();
		
	}
	
	public C getContent(){
		return content;
	}
	
	public List<Edge<C,R>> getEdgeList(){
		return edgeList;
	}
	
}
