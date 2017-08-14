package test;
import static org.junit.Assert.*;

import org.junit.Test;

import data.BovespaDigest;

public class TestBovespaDigest {

	@Test
	public void test() throws Exception {
		new BovespaDigest("samples/bovespa/bovespa2011.txt");
	}

}
