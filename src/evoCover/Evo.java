package evoCover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Evo {

	CoverGraph graph;
	int solQtt;
	String resultDir;

	public Evo(int solQtt, String fileName, String resultDir) throws IOException {
		this.solQtt = solQtt;
		this.resultDir = resultDir;
		graph = new CoverGraph(solQtt, fileName);
	}

	public void evo20(int genQtt, double alfa, int c, int limit, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+"_"+genQtt+"_evo20_"+ measure.getClass().getSimpleName() + "_result.csv");
		writer.println(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.employedBeePhase20(Math.floorDiv(solQtt, 2), alfa, c, measure);
			graph.onlookerBeePhase20(Math.floorDiv(solQtt, 2), Math.floorDiv(solQtt, 2), measure);
			graph.scoutBeePhase20(Math.floorDiv(solQtt, 2), limit);
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo16(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+"_"+genQtt+"_evo16_"+ measure.getClass().getSimpleName() + "_result.csv");
		writer.println(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation16(0.05);
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo10(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+"_"+genQtt+"_evo10_"+ measure.getClass().getSimpleName() + "_result.csv");
		writer.println(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation10();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo1615(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+"_"+genQtt+"_evo1615_"+ measure.getClass().getSimpleName() + "_result.csv");
		writer.println(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo1015(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+"_"+genQtt+"_evo1015_"+ measure.getClass().getSimpleName() + "_result.csv");
		writer.println(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

}
