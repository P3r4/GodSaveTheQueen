package endpoint;

import java.io.IOException;

import evoCover.Delta;
import evoCover.Evo;
import evoCover.HV;
import evoCover.Mean;
import evoCover.Measure;
import evoCover.SemiVariance;
import evoCover.Skewness;
import evoCover.SortinoRatio;

public class ProcessEndpoint implements Endpoint{
	
	int solQtt; 
	int wQtt;
    int genQtt;
    int evo;
    int c; 
    int limit;
	String logFile;
	String resDir;
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

		if (me.equals("semivar")) {
			measure = new SemiVariance();
		} else if (me.equals("mean")) {
			measure = new Mean();
		} else if (me.equals("delta")) {
			measure = new Delta();
		} else if (me.equals("sortino")) {
			measure = new SortinoRatio();
		} else if (me.equals("hv")) {
			measure = new HV();
		} else if (me.equals("skewness")) {
			measure = new Skewness();
		} else {
			return "err: invalid -me ";
		}
		return "";
	}

	@Override
	public String run() {
		
		try {
			Evo e = new Evo(solQtt, logFile, resDir);
			if (evo == 20) {
				e.evo20(wQtt, genQtt, c, limit, measure);
			} else if (evo == 10) {
				e.evo10(wQtt, genQtt, measure);
			} else if (evo == 16) {
				e.evo16(wQtt, genQtt, measure);
			} else if (evo == 1015) {
				e.evo1015(wQtt, genQtt, measure);
			} else if (evo == 1615) {
				e.evo1615(wQtt, genQtt, measure);
			} else {
				return "err:  invalid -evo";
			}
			return "";
		} catch (IOException e) {
			return "err: " + e.getMessage();
		}
	}
}