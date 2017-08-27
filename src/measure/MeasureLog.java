package measure;

import java.util.ArrayList;
import java.util.List;

public class MeasureLog {

	
	String tradeCode;
	List<Double> log;
	double mean;
	double variance;
	double standardDeviation;
	int flag = 0;

	public MeasureLog(String tradeCode) {
		this.log = new ArrayList<>();
		this.tradeCode = tradeCode;
		mean = 0;
		variance = 0.000000000000001;
	}

	void calcMean() {
		double out = 0;
		for (Double xi : this.log) {
			out += xi;
		}
		out /= log.size();
		mean = out;
	}

	void calcStdDeviationAndVariance() {
		double out = 0;
		for (Double xi : this.log) {
			out += (xi - mean) * (xi - mean);
		}
		out /= (log.size() - 1);
		variance += out;
		standardDeviation = (float) Math.sqrt(variance);
	}

	void solveNull() {

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
		for (int i = 0; i < log.size(); i++) {
			term1 += (this.log.get(i)-this.mean)*(other.log.get(i)-other.mean);
		}
		double cov = term1/(log.size()-1);
		double x = cov/(this.standardDeviation*other.standardDeviation);
		/*String k = x + "";
		if(k.equals("NaN")){
			System.out.println(cov+" "+this.standardDeviation+" "+other.standardDeviation);	
		}*/
		return  x;
	}

}
