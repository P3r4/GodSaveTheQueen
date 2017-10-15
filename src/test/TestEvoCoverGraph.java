package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import evocover.Cover;
import evocover.CoverGraph;
import evocover.Measure;
import evocover.Portfolio;
import evocover.StockLog;
import graph.Edge;
import graph.Vertex;

public class TestEvoCoverGraph {
		
	
	
	@Test
	public void testCrossOver10() throws IOException {
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcSemiVarAndSkewnessForAll();
		graph.calcMaxAndMinForAll();
		graph.crossOver10(5,Measure.Mean);
		assertEquals(10,graph.getSolutionList().size());

		double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		
		assertEquals(10.0,greatT,0.00000000001);
		
	}

	@Test
	public void testCrossOver16() throws IOException {
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcSemiVarAndSkewnessForAll();
		graph.crossOver16(5, Measure.Mean);
		assertEquals(10,graph.getSolutionList().size());

		double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		
		assertEquals(10.0,greatT,0.00000000001);
		
	}
	
	
	
	@Test
	public void testEmpBee20() throws IOException{
		CoverGraph graph = new CoverGraph(40, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcSemiVarAndSkewnessForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(40.0,greatT,0.00000000001);
		
		graph.employedBeePhase20( 2,  Measure.Mean);
			
		greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p2+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
			
		}
		assertEquals(40.0,greatT,0.00000000001);
		assertNotEquals(p1, p2);
	}

	@Test
	public void testOnlBee20() throws IOException{
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcSemiVarAndSkewnessForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		
		graph.onlookerBeePhase20( Measure.SemiVar);
			
		greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p2+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		assertNotEquals(p1, p2);
	}
	
	@Test
	public void testMutation10() throws IOException {
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcMeanReturnForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation10();
		
		greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p2+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		assertNotEquals(p1, p2);
	}
	
	@Test
	public void testMutation16() throws IOException {
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcMeanReturnForAll();
		
		String p1 = "",p2 = "";

		double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation16(0.2);
		
		greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p2+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		assertNotEquals(p1, p2);
	}
	
	@Test
	public void testMutation15() throws IOException {
		CoverGraph graph = new CoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit(14);
		graph.calcSemiVarAndSkewnessForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation15();
		
		greatT = 0;
		for (Portfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<StockLog, Cover> v : graph.getVertexList()) {
				for (Edge<StockLog, Cover> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p2+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		assertNotEquals(p1, p2);
	}

}
