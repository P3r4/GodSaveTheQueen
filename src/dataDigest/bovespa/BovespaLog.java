package dataDigest.bovespa;

public class BovespaLog {

	public BovespaLog(String logTxt) {
		this.setLogCode(logTxt);
		this.setLogDay(logTxt);
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
		this.setQttOfTradedItens(logTxt);
		this.setVolumeOfTradedShares(logTxt);
		this.setFactor(logTxt);
	}

	public float getOpeningPrice(){
		return this.openingPrice;
	}
	
	String logCode;

	public String getLogCode(){
		return this.logCode;
	}
	
	void setLogCode(String logTxtTxt) {
		this.logCode = logTxtTxt.substring(0, 2).trim();
	}

	int logDay;

	public int getLogDay(){
		return this.logDay;
	}
	
	void setLogDay(String logTxt) {
		this.logDay = Integer.parseInt(logTxt.substring(2, 10).trim());
	}

	String bdiCode;

	public String getBdiCode(){
		return this.bdiCode;
	}
	
	void setBdiCode(String logTxt) {
		this.bdiCode = logTxt.substring(10, 12).trim();
	}

	String tradeCode;

	public String getTradeCode(){
		return this.tradeCode;
	}
	
	void setTradeCode(String logTxt) {
		this.tradeCode = logTxt.substring(12, 24).trim();
	}

	String marketCode;

	public String getMarketCode(){
		return this.marketCode;
	}
	
	void setMarketCode(String logTxt) {
		this.marketCode = logTxt.substring(24, 27).trim();
	}

	String companyShortName;

	public String getCompanyShortName(){
		return this.companyShortName;
	}
	
	void setCompanyShortName(String logTxt) {
		this.companyShortName = logTxt.substring(27, 39).trim();
	}

	String shareTypeCode;

	public String getShareTypeCode(){
		return this.shareTypeCode;
	}
	
	void setShareTypeCode(String logTxt) {
		this.shareTypeCode = logTxt.substring(39, 49).trim();
	}

	String marketTermInDays;

	public String getMarketTermInDays(){
		return this.marketTermInDays;
	}
	
	void setMarketTermInDays(String logTxt) {
		this.marketTermInDays = logTxt.substring(49, 52).trim();
	}

	String currency;
	
	public String getCurrency(){
		return this.currency;
	}
	
	void setCurrency(String logTxt) {
		this.currency = logTxt.substring(52, 56).trim();
	}

	float openingPrice;

	public float getOpenigPrice(){
		return this.openingPrice;
	}
	
	void setOpeningPrice(String logTxt) {
		this.openingPrice = Float.parseFloat(logTxt.substring(56, 67).trim());
	}

	float maxPrice;

	public float getMaxPrice(){
		return this.maxPrice;
	}
	
	void setMaxPrice(String logTxt) {
		this.maxPrice = Float.parseFloat(logTxt.substring(69, 80).trim());
	}

	float minPrice;

	public float getMinPrice(){
		return this.minPrice;
	}
	
	void setMinPrice(String logTxt) {
		this.minPrice = Float.parseFloat(logTxt.substring(82, 93).trim());
	}

	float meanPrice;

	public float getMeanPrice(){
		return this.meanPrice;
	}
	
	void setMeanPrice(String logTxt) {
		this.meanPrice = Float.parseFloat(logTxt.substring(95, 106).trim());
	}

	float lastPrice;

	public float getLastPrice(){
		return this.lastPrice;
	}
	
	void setLastPrice(String logTxt) {
		this.lastPrice = Float.parseFloat(logTxt.substring(108, 119).trim());
	}

	float bestBuyPrice;

	public float getBestBuyPrice(){
		return this.bestBuyPrice;
	}
	
	void setBestBuyPrice(String logTxt) {
		this.bestBuyPrice = Float.parseFloat(logTxt.substring(121, 132).trim());
	}

	float bestSalePrice;

	public float getBestSalePrice(){
		return this.bestSalePrice;
	}
	
	void setBestSalePrice(String logTxt) {
		this.bestSalePrice = Float.parseFloat(logTxt.substring(134, 145).trim());
	}

	int qttOfTrades;

	public int getQttOfTrades(){
		return this.qttOfTrades;
	}
	
	void setQttOfTrades(String logTxt) {
		this.qttOfTrades = Integer.parseInt(logTxt.substring(147, 152).trim());
	}

	String qttOfTradedItens;

	public String getQttOfTradedItens(){
		return this.qttOfTradedItens;
	}
	
	void setQttOfTradedItens(String logTxt) {
		this.qttOfTradedItens = logTxt.substring(152, 170).trim();
	}

	String volumeOfTradedShares;

	public String getVolumeOfTradedShares(){
		return this.volumeOfTradedShares;
	}
	
	void setVolumeOfTradedShares(String logTxt) {
		this.volumeOfTradedShares = logTxt.substring(170, 186).trim();
	}

	int factor;

	public int getFactor() {
		return this.factor;
	}
	
	void setFactor(String logTxt) {
		this.factor = Integer.parseInt(logTxt.substring(210, 217).trim());
	}
}
