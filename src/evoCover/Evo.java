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
		PrintWriter writer = new PrintWriter(resultDir +solQtt+ "evo20" + measure.getClass().getSimpleName() + "Result.csv");
		writer.print(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.employedBeePhase20(Math.floorDiv(solQtt, 2), alfa, c, measure);
			graph.onlookerBeePhase20(Math.floorDiv(solQtt, 2), Math.floorDiv(solQtt, 2), new SemiVariance());
			graph.scoutBeePhase20(Math.floorDiv(solQtt, 2), limit);
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo16(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+ "evo16" + measure.getClass().getSimpleName() + "Result.csv");
		writer.print(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation16(0.05);
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo10(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+ "evo10" + measure.getClass().getSimpleName() + "Result.csv");
		writer.print(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation10();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo1615(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+ "evo1615" + measure.getClass().getSimpleName() + "Result.csv");
		writer.print(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

	public void evo1015(int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit();
		PrintWriter writer = new PrintWriter(resultDir +solQtt+ "evo1015" + measure.getClass().getSimpleName() + "Result.csv");
		writer.print(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
			writer.print(graph.formatResult(measure));
		}
		writer.close();
	}

}
