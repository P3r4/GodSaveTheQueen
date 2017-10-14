import java.io.IOException;

import evoCover.Delta;
import evoCover.Evo;
import evoCover.HV;
import evoCover.Mean;
import evoCover.Measure;
import evoCover.SemiVariance;
import evoCover.Skewness;
import evoCover.SortinoRatio;

public class EvoCover {

	/**
	 * -solQtt -genQtt -logFile -resDir -evo 20 -alfa -c -limit -evo 16 -evo 10
	 * -evo 1015 -evo 1615 -me
	 */

	private static int getAttIndex(String[] args, String att) {

		int i = 0;
		while (i < args.length && !args[i].equals(att))
			i++;
		return i;

	}

	public static void main(String[] args) {
		int solQtt = -1, wQtt = -1, genQtt = -1, evo = -1, c = -1, limit = -1;
		String logFile = null, resDir = null, measure = null;

		int i;
		i = getAttIndex(args, "-me");
		if (i == args.length) {
			System.out.println("err: -me not set");
			return;
		} else {
			measure = args[i + 1];
		}
		
		i = getAttIndex(args, "-wQtt");
		if (i == args.length) {
			System.out.println("err: -wQtt not set");
			return;
		} else {
			wQtt = Integer.parseInt(args[i + 1]);
		}
		
		i = getAttIndex(args, "-logFile");
		if (i == args.length) {
			System.out.println("err: -logFile not set");
			return;
		} else {
			logFile = args[i + 1];
		}

		i = getAttIndex(args, "-resDir");
		if (i == args.length) {
			System.out.println("err: -resDir not set");
			return;
		} else {
			resDir = args[i + 1];
		}

		i = getAttIndex(args, "-solQtt");
		if (i == args.length) {
			System.out.println("err: -solQtt not set");
			return;
		} else {
			solQtt = Integer.parseInt(args[i + 1]);
		}

		i = getAttIndex(args, "-genQtt");
		if (i == args.length) {
			System.out.println("err: -genQtt not set");
			return;
		} else {
			genQtt = Integer.parseInt(args[i + 1]);
		}

		i = getAttIndex(args, "-evo");
		if (i == args.length) {
			System.out.println("err: -evo not found");
			return;
		} else {
			evo = Integer.parseInt(args[i + 1]);
		}

		if (evo == 20) {
			i = getAttIndex(args, "-c");
			if (i == args.length) {
				System.out.println("err: -c not set");
				return;
			} else {
				c = Integer.parseInt(args[i + 1]);
			}

			i = getAttIndex(args, "-limit");
			if (i == args.length) {
				System.out.println("err: -limit not set");
				return;
			} else {
				limit = Integer.parseInt(args[i + 1]);
			}

		}

		Measure m;
		if(measure.equals("semivar")){
			m = new SemiVariance();
		} else if(measure.equals("mean")){
			m = new Mean();
		} else if(measure.equals("delta")){
			m = new Delta();
		} else if(measure.equals("sortino")){
			m = new SortinoRatio();
		} else if(measure.equals("hv")){
			m = new HV();
		} else if(measure.equals("skewness")){
			m = new Skewness();
		}else{
			System.out.println("err: invalid -me" );
			return;
		}
		
		
		try {
			Evo e = new Evo(solQtt, logFile, resDir);			
			if (evo == 20) {
				e.evo20(wQtt, genQtt, c, limit,m);
			} else if (evo == 10) {
				e.evo10(wQtt,genQtt,m);
			} else if (evo == 16) {
				e.evo16(wQtt,genQtt, m);
			} else if (evo == 1015) {
				e.evo1015(wQtt,genQtt,m);
			} else if (evo == 1615) {
				e.evo1615(wQtt,genQtt, m);
			} else{
				System.out.println("err:  invalid -evo");
				return;
			}
		} catch (IOException e) {
			System.out.println("err: " + e.getMessage());
			return;
		}

	}

}
