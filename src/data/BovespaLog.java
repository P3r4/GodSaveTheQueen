package data;

public class BovespaLog {

	public BovespaLog(String logTxt) {
		this.setLogCode(logTxt);
		this.setLogDate(logTxt);
		this.setBdiCode(logTxt);
		this.setTradeCode(logTxt);
		this.setMarketCode(logTxt);
		this.setCompanyShortName(logTxt);
		this.setShareTypeCode(logTxt);
		this.setMarketTermInDays(logTxt);
		this.setCurrency(logTxt);
		this.setOpeningPrice(logTxt);
		this.setMaxPrice(logTxt);
		this.setMinPrice(logTxt);
		this.setMeanPrice(logTxt);
		this.setLastPrice(logTxt);
		this.setBestBuyPrice(logTxt);
		this.setBestSalePrice(logTxt);
		this.setQttOfTrades(logTxt);
		this.setQttOfTradedShares(logTxt);
		this.setVolumeOfTradedShares(logTxt);
		this.setFactor(logTxt);
	}

	String logCode;

	void setLogCode(String logTxtTxt) {
		this.logCode = logTxtTxt.substring(0, 2).trim();
	}

	int logDate;

	void setLogDate(String logTxt) {
		this.logDate = Integer.parseInt(logTxt.substring(2, 10).trim());
	}

	String bdiCode;

	void setBdiCode(String logTxt) {
		this.bdiCode = logTxt.substring(10, 12).trim();
	}

	String tradeCode;

	void setTradeCode(String logTxt) {
		this.tradeCode = logTxt.substring(12, 24).trim();
	}

	String marketCode;

	void setMarketCode(String logTxt) {
		this.marketCode = logTxt.substring(24, 27).trim();
	}

	String companyShortName;

	void setCompanyShortName(String logTxt) {
		this.companyShortName = logTxt.substring(27, 39).trim();
	}

	String shareTypeCode;

	void setShareTypeCode(String logTxt) {
		this.shareTypeCode = logTxt.substring(39, 49).trim();
	}

	String marketTermInDays;

	void setMarketTermInDays(String logTxt) {
		this.marketTermInDays = logTxt.substring(49, 52).trim();
	}

	String currency;

	void setCurrency(String logTxt) {
		this.currency = logTxt.substring(52, 56).trim();
	}

	float openingPrice;

	void setOpeningPrice(String logTxt) {
		this.openingPrice = Float.parseFloat(logTxt.substring(56, 67).trim());
	}

	float maxPrice;

	void setMaxPrice(String logTxt) {
		this.maxPrice = Float.parseFloat(logTxt.substring(69, 80).trim());
	}

	float minPrice;

	void setMinPrice(String logTxt) {
		this.minPrice = Float.parseFloat(logTxt.substring(82, 93).trim());
	}

	float meanPrice;

	void setMeanPrice(String logTxt) {
		this.meanPrice = Float.parseFloat(logTxt.substring(95, 106).trim());
	}

	float lastPrice;

	void setLastPrice(String logTxt) {
		this.lastPrice = Float.parseFloat(logTxt.substring(108, 119).trim());
	}

	float bestBuyPrice;

	void setBestBuyPrice(String logTxt) {
		this.bestBuyPrice = Float.parseFloat(logTxt.substring(121, 132).trim());
	}

	String bestSalePrice;

	void setBestSalePrice(String logTxt) {
		this.bestSalePrice = logTxt.substring(134, 145).trim();
	}

	String qttOfTrades;

	void setQttOfTrades(String logTxt) {
		this.qttOfTrades = logTxt.substring(147, 152).trim();
	}

	String qttOfTradedShares;

	void setQttOfTradedShares(String logTxt) {
		this.qttOfTradedShares = logTxt.substring(152, 170).trim();
	}

	String volumeOfTradedShares;

	void setVolumeOfTradedShares(String logTxt) {
		this.volumeOfTradedShares = logTxt.substring(170, 186).trim();
	}

	int factor;

	void setFactor(String logTxt) {
		this.factor = Integer.parseInt(logTxt.substring(210, 217).trim());
	}
}
