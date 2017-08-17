package test;
import static org.junit.Assert.*;

import org.junit.Test;

import dataDigest.bovespa.BovespaDigest;
import dataDigest.bovespa.BovespaLog;

public class TestBovespaLog {

	@Test
	public void test1() {
		
		String logTxt = "012003021202VALE3       010VALE R DOCE ON           R$  000000001050100000000105010000000010250000000001036800000000103210000000010"
				+ "321000000001043800142000000000000069500000000000720641400000000000000009999123100000010000000000000BRVALEACNOR0159";	
		BovespaLog log = new BovespaLog(logTxt);
		assertEquals("01", log.getLogCode());
		assertEquals(20030212, log.getLogDay());
		assertEquals("VALE3", log.getTradeCode());
		assertEquals("010", log.getMarketCode());
		assertEquals("02", log.getBdiCode());
		assertEquals("ON", log.getShareTypeCode());
		assertEquals("VALE R DOCE", log.getCompanyShortName());
		assertEquals("R$", log.getCurrency());
		assertEquals(105.00, log.getOpenigPrice(), 0);
		assertEquals(105.00, log.getMaxPrice(), 0);
		assertEquals(102.00, log.getMinPrice(), 0);
		assertEquals(103.00, log.getMeanPrice(), 0);
		assertEquals(103.00, log.getLastPrice(), 0);
		assertEquals(103.00, log.getBestBuyPrice(), 0);
		assertEquals(104.00, log.getBestSalePrice(), 0);
		assertEquals(1, log.getFactor());
		
		
	}
	
	@Test
	public void test2() {
		
		String logTxt = "012003021202ITSA3       010ITAUSA      ON      N1   R$  000000000033200000000003320000000000332000000000033200000000003320000000000329"
				+ "000000000000000002000000000000005000000000000001660000000000000000009999123100000010000000000000BRITSAACNOR0268";	
		BovespaLog log = new BovespaLog(logTxt);
		assertEquals("01", log.getLogCode());
		assertEquals(20030212, log.getLogDay());
		assertEquals("ITSA3", log.getTradeCode());
		assertEquals("010", log.getMarketCode());
		assertEquals("02", log.getBdiCode());
		assertEquals("ON      N1", log.getShareTypeCode());
		assertEquals("ITAUSA", log.getCompanyShortName());
		assertEquals("R$", log.getCurrency());
		assertEquals(3.00, log.getOpenigPrice(), 0);
		assertEquals(3.00, log.getMaxPrice(), 0);
		assertEquals(3.00, log.getMinPrice(), 0);
		assertEquals(3.00, log.getMeanPrice(), 0);
		assertEquals(3.00, log.getLastPrice(), 0);
		assertEquals(3.00, log.getBestBuyPrice(), 0);
		assertEquals(0.00, log.getBestSalePrice(), 0);
		assertEquals(1, log.getFactor());
		
		
	}
	
	@Test
	public void test3() {
		
		String logTxt = "012003021202TLPP4       010TELESP      PN *         R$  0000000003080000000000319800000000029900000000003135000000000307500"
				+ "00000003075000000000310000160000000000137800000000000000432035400000000000000009999123100010000000000000000BRTLPPACNPR5109";

		BovespaLog log = new BovespaLog(logTxt);
		assertEquals("01", log.getLogCode());
		assertEquals(20030212, log.getLogDay());
		assertEquals("TLPP4", log.getTradeCode());
		assertEquals("010", log.getMarketCode());
		assertEquals("02", log.getBdiCode());
		assertEquals("PN *", log.getShareTypeCode());
		assertEquals("TELESP", log.getCompanyShortName());
		assertEquals("R$", log.getCurrency());
		assertEquals(30.00, log.getOpenigPrice(), 0);
		assertEquals(31.00, log.getMaxPrice(), 0);
		assertEquals(29.00, log.getMinPrice(), 0);
		assertEquals(31.00, log.getMeanPrice(), 0);
		assertEquals(30.00, log.getLastPrice(), 0);
		assertEquals(30.00, log.getBestBuyPrice(), 0);
		assertEquals(31.00, log.getBestSalePrice(), 0);
		assertEquals(1000, log.getFactor());
		
		
	}

}
