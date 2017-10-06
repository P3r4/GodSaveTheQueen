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
	public void testCrossOver10() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcSemiVarAndSkewnessForAll();
		graph.crossOver10(5);
		assertEquals(10,graph.getSolutionList().size());

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
		
		assertEquals(10.0,greatT,0.00000000001);
		
	}

	@Test
	public void testCrossOver16() throws IOException {
		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcSemiVarAndSkewnessForAll();
		graph.crossOver16(5);
		assertEquals(10,graph.getSolutionList().size());

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
		
		assertEquals(10.0,greatT,0.00000000001);
		
	}
	
	@Test
	public void testEmpBee20() throws IOException{
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
		
		graph.employedBeePhase20( 0.06 , 1);
			
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
	public void testMutation10() throws IOException {
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
		graph.mutation10();
		
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
	public void testMutation16() throws IOException {
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
		graph.mutation16(0.2);
		
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
	public void testMutation15() throws IOException {
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
		graph.mutation15();
		
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
