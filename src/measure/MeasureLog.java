package measure;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MeasureLog {

	String tradeCode;
	List<Double> log;
	List<Double> returnLog;
	double mean;
	double variance;
	double standardDeviation;
	Integer level;
	int flag;

	private void calcReturnLog() {
		Double t0Price = log.get(0) + 0.0000000000000001;
		for (Double price : log) {
			returnLog.add((price - t0Price) / t0Price);
		}
	}

	public MeasureLog(String line) {
		mean = 0;
		variance = 0.000000000000001;		
		this.log = new ArrayList<>();
		this.returnLog = new ArrayList<>();
		String[] splitLine = line.split(",");
		this.tradeCode = splitLine[0];
		
		for (int i = 1; i < splitLine.length; i++) {
			if (splitLine[i].equals("null")) {
				log.add(null);
			} else {
				log.add(Double.parseDouble(splitLine[i]));
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

		Double last = log.get(log.size() - 1);
		for (int j = log.size() - 1; j > -1; j--) {
			if (last != null && log.get(j) == null) {
				log.set(j, last);
			}
			last = log.get(j);

		}
		last = log.get(0);
		for (int j = 0; j < log.size(); j++) {
			if (last != null && log.get(j) == null) {
				log.set(j, last);
			}
			last = log.get(j);
		}

	}

	public double calcCorrelation(MeasureLog other) {
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
		DecimalFormat df = new DecimalFormat("#0.0000000000");
		return tradeCode + "," + df.format(mean) + "," + df.format(variance);
	}

}
