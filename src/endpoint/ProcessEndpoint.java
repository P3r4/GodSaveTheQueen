package endpoint;

import java.io.IOException;
import java.io.PrintWriter;

import evocover.Measure;
import evocover.Process;
import plot.PlotData;

public class ProcessEndpoint implements Endpoint{
	
	int solQtt; 
	int wQtt;
    int genQtt;
    int evo;
    int c; 
    int limit;
	String logFile;
	String resDir;
	String reviewFile;
	String me;
	Measure measure;

	@Override
	public String init(Args args) {

		String att;
		att = args.getAttValue("-solQtt");
		if(att == null){return "err: -solQtt not set";}
		solQtt = Integer.parseInt(att);
		
		att = args.getAttValue("-evo");
		if(att == null){return "err: -evo not set";}
		evo = Integer.parseInt(att);;
		
		att = args.getAttValue("-wQtt");
		if(att == null){return "err: -wQtt not set";}
		wQtt = Integer.parseInt(att);
	
		att = args.getAttValue("-genQtt");
		if(att == null){return "err: -genQtt not set";}
		genQtt = Integer.parseInt(att);
		
		att = args.getAttValue("-logFile");
		if(att == null){return "err: -logFile not set";}
		logFile = att;
		
		att = args.getAttValue("-resDir");
		if(att == null){return "err: -resDir not set";}
		resDir = att;
		
		att = args.getAttValue("-reviewFile");
		if(att == null){return "err: -reviewFile not set";}
		reviewFile = att;
		
		att = args.getAttValue("-me");
		if(att == null){return "err: -me not set";}
		me = att;

		if (evo == 20) {
			
			att = args.getAttValue("-c");
			if(att == null){return "err: -c not set";}
			c = Integer.parseInt(att);
			
			att = args.getAttValue("-limit");
			if(att == null){return "err: -limit not set";}
			limit = Integer.parseInt(att);

		}

		if (me.equals("dsrisk")) {
			measure = Measure.DsRisk;
		} else if (me.equals("mean")) {
			measure = Measure.Mean;
		} else if (me.equals("delta")) {
			measure = Measure.Delta;
		} else if (me.equals("sortino")) {
			measure = Measure.SortinoRatio;
		} else if (me.equals("hv")) {
			measure = Measure.HV;
		} else if (me.equals("skewness")) {
			measure = Measure.Skewness;
		} else {
			return "err: invalid -me ";
		}
		return "";
	}

	@Override
	public String run() {
		
		try {
			PlotData data;
			Process p = new Process(solQtt, logFile, resDir,reviewFile);
			if (evo == 20) {
				data = p.evo20(wQtt, genQtt, c, limit, measure);
			} else if (evo == 10) {
				data = p.evo10(wQtt, genQtt, measure);
			} else if (evo == 16) {
				data = p.evo16(wQtt, genQtt, measure);
			} else if (evo == 1015) {
				data = p.evo1015(wQtt, genQtt, measure);
			} else if (evo == 1615) {
				data = p.evo1615(wQtt, genQtt, measure);
			} else {
				return "err:  invalid -evo";
			}
			data.printData();
			data.printReview();
			return "";
		} catch (IOException e) {
			return "err: " + e.getMessage();
		}
	}
}