package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import dataDigest.bovespa.BovespaDigest;

public class TestBovespaDigest {

	@Test
	public void testSortedDays() throws Exception {
		BovespaDigest digest =  new BovespaDigest("./samples/bovespa/bovespa2011.txt");
		Integer [] days = digest.getLogDaySet().toArray(new Integer[digest.getLogDaySet().size()]);
		
	    int last = days[0];
	    for(Integer day : days){
			assertTrue(last<=day);
			last = day;
	    }
	    
	    String [] codes = digest.getTradeCodeSet().toArray(new String[digest.getTradeCodeSet().size()]);
	    
	    String lastS = codes[0];
	    for(String code : codes){
	    	assertTrue(code.compareTo(lastS)>=0);
	    }
	    /*
		String path = "./samples/digestedCSV/digestedBovespa2011k.csv";
		File f = new File(path);
		f.delete();
		digest.writeDigestedCSVFile(path);
		*/
	}

}
