package test;
import static org.junit.Assert.*;

import org.junit.Test;

import measure.MeasureLog;

public class TestMeasureLog {
	
	
	@Test
	public void test1(){

		//X: 41, 19, 23, 40, 55, 57, 33
		//Y: 94, 60, 74, 71, 82, 76, 61
		//http://www.investopedia.com/terms/c/correlation.asp
		//http://www.investopedia.com/exam-guide/cfa-level-1/portfolio-management/portfolio-calculations.asp
		
		String line = "CPLE6,41.0,19.0,23.0,40.0,55.0,57.0,33.0";
		MeasureLog log =  new MeasureLog(line);
		
		assertEquals(1, log.calcCorrelation(log),0.0001);
		
		assertEquals(-0.06620209059,log.getMean(),0.0001);
		assertEquals(0.1262570465,log.getVariance(),0.0001);
		assertEquals(0.3553266758,log.getStdDev(),0.0001);
	
		String line2 = "KBCX6,94.0,60.0,74.0,71.0,82.0,76.0,61.0";
		MeasureLog other =  new MeasureLog(line2);
		
		assertEquals(1, other.calcCorrelation(other),0.0001);
		assertEquals(-0.2127659574,other.getMean(),0.0001);
		assertEquals(0.01588199789,other.getVariance(),0.0001);
		assertEquals(0.1260237989,other.getStdDev(),0.0001);
		
		assertEquals(0.54, log.calcCorrelation(other),0.001);

	}
	
}
