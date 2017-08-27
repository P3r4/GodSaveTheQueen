package test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import measure.MeasureGraph;

public class TestMeasureGraph {

	
	@Test
	public void test1() throws IOException{
		MeasureGraph builder = new MeasureGraph("./samples/digestedCSV/digestedBovespa2011k.csv");
		builder.writeCorrCSVFile("./samples/corrCSV/corrBovespa2011k.csv", -0.6);
		//builder.markEdgesAboveLimit(0.0);
	}
	
}
