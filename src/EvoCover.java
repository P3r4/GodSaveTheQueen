import java.io.IOException;

import org.omg.PortableServer.POA;

import evoCover.Evo;

public class EvoCover {

	/**
	 * -solQtt -genQtt -logFile -resFile -evo 20 -alfa -c -limit -evo 16 -evo 10
	 * -evo 1015 -evo 1615
	 */

	private static int getAttIndex(String[] args, String att) {

		int i = 0;
		while (i < args.length && !args[i].equals(att))
			i++;
		return i;

	}

	public static void main(String[] args) {
		int solQtt = -1, genQtt = -1, evo = -1, c = -1, limit = -1;
		String logFile = null, resDir = null;
		double alfa = -1.0;

		int i;
		i = getAttIndex(args, "-logFile");
		if (i == args.length) {
			System.out.println("err: -logFile not set");
			return;
		} else {
			logFile = args[i + 1];
		}

		i = getAttIndex(args, "-resFile");
		if (i == args.length) {
			System.out.println("err: -resFile not set");
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

			i = getAttIndex(args, "-alfa");
			if (i == args.length) {
				System.out.println("err: -alfa not set");
				return;
			} else {
				alfa = Double.parseDouble(args[i + 1]);
			}

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

		try {
			Evo e = new Evo(solQtt, logFile, resDir);			
			if (evo == 20) {
				e.evo20(genQtt, alfa, c, limit);
			} else if (evo == 10) {
				e.evo10(genQtt);
			} else if (evo == 16) {
				e.evo16(genQtt);
			} else if (evo == 1015) {
				e.evo1015(genQtt);
			} else if (evo == 1615) {
				e.evo1615(genQtt);
			}
		} catch (IOException e) {
			System.out.println("err: " + e.getMessage());
			return;
		}

	}

}
