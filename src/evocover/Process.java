package evocover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import plot.PlotData;

public class Process {

	CoverGraph graph;
	int solQtt;
	String resultDir;

	public Process(int solQtt, String fileName, String resultDir) throws IOException {
		this.solQtt = solQtt;
		this.resultDir = resultDir;
		graph = new CoverGraph(solQtt, fileName);
	}

	public PlotData evo20(int wQtt, int genQtt, int c, int limit, Measure measure) throws FileNotFoundException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData(resultDir +solQtt+"_"+genQtt+"_evo20_"+ measure.toString() + "_result.csv");
		data.add(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.add(graph.formatResult(measure));
			graph.employedBeePhase20(c, measure);
			graph.onlookerBeePhase20(measure);
			graph.scoutBeePhase20(wQtt,limit);
		}
		data.add(graph.formatResult(measure));
		return data;
	}

	public PlotData evo16(int wQtt, int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData(resultDir +solQtt+"_"+genQtt+"_evo16_"+ measure.toString() + "_result.csv");
		data.add(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.add(graph.formatResult(measure));
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation16(0.05);
		}
		data.add(graph.formatResult(measure));
		return data;
	}

	public PlotData evo10(int wQtt, int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData(resultDir +solQtt+"_"+genQtt+"_evo10_"+ measure.toString() + "_result.csv");
		data.add(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.add(graph.formatResult(measure));
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation10();
		}
		data.add(graph.formatResult(measure));
		return data;
	}

	public PlotData evo1615(int wQtt, int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData(resultDir +solQtt+"_"+genQtt+"_evo1615_"+ measure.toString() + "_result.csv");
		data.add(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.add(graph.formatResult(measure));
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
		}
		data.add(graph.formatResult(measure));
		return data;
	}

	public PlotData evo1015(int wQtt, int genQtt, Measure measure) throws FileNotFoundException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData(resultDir +solQtt+"_"+genQtt+"_evo1015_"+ measure.toString() + "_result.csv");
		data.add(graph.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.add(graph.formatResult(measure));
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
		}
		data.add(graph.formatResult(measure));
		return data;
	}

}