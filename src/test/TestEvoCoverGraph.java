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
	public void test1() throws IOException {
		// EvoCoverGraph graph = new
		// EvoCoverGraph(10,"./samples/digestedCSV/digestedBovespa2011k.csv");
		// graph.writeFilteredCSVFile("./samples/filteredCSV/", -0.7, 0.0, 0.2);

		EvoCoverGraph graph = new EvoCoverGraph(10, "./samples/filteredCSV/part1.csv");
		graph.randomInit();
		
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
		
		graph.calcMeanReturnForAll();
		
		graph.geneticMutation1();
		
		greatT = 0;
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
		
		graph.geneticMutation2(0.2);
		
		greatT = 0;
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
		
		graph.geneticMutation3();
		

		greatT = 0;
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

}
