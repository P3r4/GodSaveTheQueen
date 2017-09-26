package test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import evoCover.EvoCoverGraph;

public class TestEvoCoverGraph {

	
	@Test
	public void test1() throws IOException{
//EvoCoverGraph graph = new EvoCoverGraph(10,"./samples/digestedCSV/digestedBovespa2011k.csv");
	//	graph.writeFilteredCSVFile("./samples/filteredCSV/", -0.7, 0.0, 0.2);
		
		EvoCoverGraph graph = new EvoCoverGraph(10,"./samples/filteredCSV/part1.csv");
		graph.randomInit();
		graph.calcMeanReturnForAllSolutions();
	}
	
}
