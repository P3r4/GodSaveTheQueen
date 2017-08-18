package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dataDigest.bovespa.BovespaDigest;

public class TestBovespaDigest {

	@Test
	public void testDigestedCSVFile() throws Exception {
		BovespaDigest digest =  new BovespaDigest("./samples/bovespa/bovespa2011.txt");
		digest.writeDigestedCSVFile("./samples/digestedCSV/digestedBovespa2011k.csv");
		
	}

}
