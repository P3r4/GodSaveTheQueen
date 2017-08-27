package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<C,R> {

	Integer seqId;
	C content;
	List<Edge<C,R>> edgeList;
	
	public Vertex(C content, int seqId){
		this.content = content;
		edgeList =  new ArrayList<>();
		this.seqId = seqId;
	}
	
	public Integer getSeqId(){
		return this.seqId;
	}
	
	public C getContent(){
		return content;
	}
	
	public List<Edge<C,R>> getEdgeList(){
		return edgeList;
	}
	
}
