package graph;

public class Edge<C,R> {
	
	Vertex<C,R> a;
	Vertex<C,R> b;
	R relation;
	
	public Edge(Vertex<C,R> a, Vertex<C,R> b, R relation){
		this.relation = relation;
		this.a = a;
		this.b = b;
	}
	
	public Vertex<C,R> getVertexA(){
		return a;
	}
	
	public Vertex<C,R> getVertexB(){
		return b;
	}
	
	public R getRelation(){
		return relation;
	}
}
