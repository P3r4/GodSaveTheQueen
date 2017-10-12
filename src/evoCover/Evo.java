package evoCover;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Evo {

	CoverGraph graph;
	int solQtt;
	String resultDir;
	
	public Evo(int solQtt, String fileName, String resultDir) throws IOException{
		this.solQtt = solQtt;
		this.resultDir = resultDir;
		graph =  new CoverGraph(solQtt,fileName);
	}
	
	public void evo20(int genQtt, double alfa, int c, int limit, Measure measure) throws FileNotFoundException{
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.employedBeePhase20(Math.floorDiv(solQtt, 2),alfa, c,measure);
			graph.onlookerBeePhase20(Math.floorDiv(solQtt, 2), Math.floorDiv(solQtt, 2),new SemiVariance());
			graph.scoutBeePhase20(Math.floorDiv(solQtt, 2), limit);
		}
		graph.printResult(resultDir+"evo20result.csv", measure);
	}
	
	public void evo16(int genQtt, Measure measure) throws FileNotFoundException{
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation16(0.05);
		}
		graph.printResult(resultDir+"evo16result.csv",  measure);
	}
	
	public void evo10(int genQtt, Measure measure) throws FileNotFoundException{
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2),measure);
			graph.mutation10();
		}

		graph.printResult(resultDir+"evo10result.csv",  measure);
	}

	public void evo1615(int genQtt, Measure measure) throws FileNotFoundException{
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
		}

		graph.printResult(resultDir+"evo1615result.csv",  measure);
	}
	
	public void evo1015(int genQtt, Measure measure) throws FileNotFoundException{
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2),measure);
			graph.mutation15();
		}
		graph.printResult(resultDir+"evo1015result.csv",  measure);
	}	

}
