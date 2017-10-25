package evocover;


import java.io.IOException;
import plot.PlotData;

public class Process {

	CoverGraph graph;
	int solQtt;
	String resultDir;
	String reviewFileName;
	
	public Process(int solQtt, String fileName, String resultDir, String reviewFileName) throws IOException {
		this.solQtt = solQtt;
		this.resultDir = resultDir;
		this.reviewFileName =  reviewFileName;
		graph = new CoverGraph(solQtt, fileName);
	}

	public PlotData evo20(int wQtt, int genQtt, int c, int limit, Measure measure) throws IOException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData();
		Review review = new Review(this.reviewFileName);
		data.setDataFileName(resultDir +solQtt+"_"+genQtt+"_evo20_"+ measure.toString() + "_result.csv");
		data.setReviewFileName(resultDir +solQtt+"_"+genQtt+"_evo20_"+ measure.toString() + "_review.csv");
		data.addData(graph.formatHeader(measure));
		data.addReview(review.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.addData(graph.formatResult(measure));
			graph.employedBeePhase20(c, measure);
			graph.onlookerBeePhase20(measure);
			graph.scoutBeePhase20(wQtt,limit);
		}
		data.addReview(graph.formatReview(review, measure));
		data.addData(graph.formatResult(measure));
		return data;
	}

	public PlotData evo16(int wQtt, int genQtt, Measure measure) throws IOException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData();
		Review review = new Review(this.reviewFileName);
		data.setDataFileName(resultDir +solQtt+"_"+genQtt+"_evo16_"+ measure.toString() + "_result.csv");
		data.setReviewFileName(resultDir +solQtt+"_"+genQtt+"_evo16_"+ measure.toString() + "_review.csv");
		data.addData(graph.formatHeader(measure));
		data.addReview(review.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.addData(graph.formatResult(measure));
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation16(0.05);
		}
		data.addReview(graph.formatReview(review, measure));
		data.addData(graph.formatResult(measure));
		return data;
	}

	public PlotData evo10(int wQtt, int genQtt, Measure measure) throws IOException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData();
		Review review = new Review(this.reviewFileName);
		data.setDataFileName(resultDir +solQtt+"_"+genQtt+"_evo10_"+ measure.toString() + "_result.csv");
		data.setReviewFileName(resultDir +solQtt+"_"+genQtt+"_evo10_"+ measure.toString() + "_review.csv");
		data.addData(graph.formatHeader(measure));
		data.addReview(review.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.addData(graph.formatResult(measure));
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation10();
		}
		data.addReview(graph.formatReview(review, measure));
		data.addData(graph.formatResult(measure));
		return data;
	}

	public PlotData evo1615(int wQtt, int genQtt, Measure measure) throws IOException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData();
		Review review = new Review(this.reviewFileName);
		data.setDataFileName(resultDir +solQtt+"_"+genQtt+"_evo1615_"+ measure.toString() + "_result.csv");
		data.setReviewFileName(resultDir +solQtt+"_"+genQtt+"_evo1615_"+ measure.toString() + "_review.csv");
		data.addData(graph.formatHeader(measure));
		data.addReview(review.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.addData(graph.formatResult(measure));
			graph.crossOver16(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
		}
		data.addData(graph.formatResult(measure));
		data.addReview(graph.formatReview(review, measure));
		return data;
	}

	public PlotData evo1015(int wQtt, int genQtt, Measure measure) throws IOException {
		graph.randomInit(wQtt);
		PlotData data = new PlotData();
		Review review = new Review(this.reviewFileName);
		data.setDataFileName(resultDir +solQtt+"_"+genQtt+"_evo1015_"+ measure.toString() + "_result.csv");
		data.setReviewFileName(resultDir +solQtt+"_"+genQtt+"_evo1015_"+ measure.toString() + "_review.csv");
		data.addData(graph.formatHeader(measure));
		data.addReview(review.formatHeader(measure));
		for (int i = 0; i < genQtt; i++) {
			data.addData(graph.formatResult(measure));
			graph.crossOver10(Math.floorDiv(solQtt, 2), measure);
			graph.mutation15();
		}
		data.addData(graph.formatResult(measure));
		data.addReview(graph.formatReview(review, measure));
		return data;
	}

}
