package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import evoCover.EvoCoverGraph;
import evoCover.EvoCoverLink;
import evoCover.EvoCoverLog;
import evoCover.EvoCoverPortfolio;
import graph.Edge;
import graph.Vertex;

public class TestEvoCoverGraph {
		
	
	
	@Test
	public void testCrossOver2() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcMeanReturnForAll();
		graph.crossOver2(5);
		assertEquals(20,graph.getSolutionList().size());

		double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		
		assertEquals(20.0,greatT,0.00000000001);
		
	}

	@Test
	public void testCrossOver1() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcMeanReturnForAll();
		graph.crossOver1(5);
		assertEquals(20,graph.getSolutionList().size());

		double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		
		assertEquals(20.0,greatT,0.00000000001);
		
	}
	
	@Test
	public void testEmpBee() throws IOException{
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcSemiVarAndSkewnessForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		
		graph.employedBeePhase1();
			
		greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
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
	public void testMutation1() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcMeanReturnForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation1();
		
		greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
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
	public void testMutation2() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcMeanReturnForAll();
		
		String p1 = "",p2 = "";

		double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation2(0.2);
		
		greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
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
	public void testMutation3() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcSemiVarAndSkewnessForAll();
		
		String p1 = "",p2 = "";
    	double total, greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
					total += e.getRelation().getCoverValue(p.getId());
					p1+= e.getRelation().getCoverValue(p.getId());
				}
			}
			assertEquals(1.0,total,0.00000000001);
			greatT += total;
		}
		assertEquals(10.0,greatT,0.00000000001);
		graph.mutation3();
		
		greatT = 0;
		for (EvoCoverPortfolio p : graph.getSolutionList()) {
			total = 0;
			for (Vertex<EvoCoverLog, EvoCoverLink> v : graph.getVertexList()) {
				for (Edge<EvoCoverLog, EvoCoverLink> e : v.getEdgeList()) {
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
