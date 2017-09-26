package evoCover;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EvoCoverLog {

	String tradeCode;
	List<Double> priceLog;
	List<Double> returnLog;
	double mean;
	double variance;
	double standardDeviation;
	Integer level;
	int flag;

	private void calcReturnLog() {
		Double t0Price = priceLog.get(0) + 0.0000000000000001;
		for (Double price : priceLog) {
			returnLog.add((price - t0Price) / t0Price);
		}
	}

	public EvoCoverLog(String line) {
		mean = 0;
		variance = 0.000000000000001;		
		this.priceLog = new ArrayList<>();
		this.returnLog = new ArrayList<>();
		String[] splitLine = line.split(",");
		this.tradeCode = splitLine[0];
		
		for (int i = 1; i < splitLine.length; i++) {
			if (splitLine[i].equals("null")) {
				priceLog.add(null);
			} else {
				priceLog.add(Double.parseDouble(splitLine[i]));
			}
		}
		solveNull();
		calcReturnLog();
		calcMean();
		calcStdDevAndVariance();
		
	}

	private void calcMean() {
		double out = 0;
		for (Double xi : this.returnLog) {
			out += xi;
		}
		out /= returnLog.size();
		mean = out;
	}

	private void calcStdDevAndVariance() {
		double out = 0;
		for (Double xi : this.returnLog) {
			out += (xi - mean) * (xi - mean);
		}
		out /= (returnLog.size() - 1);
		variance += out;
		standardDeviation = (float) Math.sqrt(variance);
	}

	private void solveNull() {
		System.out.println(priceLog.size());
		Double last = priceLog.get(priceLog.size() - 1);
		for (int j = priceLog.size() - 1; j > -1; j--) {
			if (last != null && priceLog.get(j) == null) {
				priceLog.set(j, last);
			}
			last = priceLog.get(j);

		}
		last = priceLog.get(0);
		for (int j = 0; j < priceLog.size(); j++) {
			if (last != null && priceLog.get(j) == null) {
				priceLog.set(j, last);
			}
			last = priceLog.get(j);
		}

	}

	public double calcCorrelation(EvoCoverLog other) {
		double term1 = 0;
		for (int i = 0; i < returnLog.size(); i++) {
			term1 += (this.returnLog.get(i) - this.mean) * (other.returnLog.get(i) - other.mean);
		}
		double cov = term1 / (returnLog.size() - 1);
		double corr = cov / (this.standardDeviation * other.standardDeviation);

		return corr;
	}

	public double getMean(){
		return mean;
	}
	
	public double getVariance(){
		return variance;
	}
	
	public double getStdDev(){
		return standardDeviation;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.00");
		String line = "";
		for(Double price : priceLog){
			line += ","+df.format(price);
		}
		return tradeCode + line;
	}

}
