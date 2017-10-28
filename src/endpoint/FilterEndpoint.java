package endpoint;

import java.io.IOException;

import evocover.CoverGraph;
import evocover.Filter;

public class FilterEndpoint implements Endpoint{

	double corrTLimit;
	double meanBLimit;
	double dsRiskTLimit;
	String file; 
	String resDir;

	
	@Override
	public String run() {
		try {
			System.out.println("oioioioioioioioioi");
			Filter filter = new Filter(file);
			filter.markGraph(corrTLimit, meanBLimit, dsRiskTLimit);
			filter.writeFilteredCSVFile(resDir, corrTLimit, meanBLimit, dsRiskTLimit);
			return "";
		} catch (IOException e) {
			return "err: " + e.getStackTrace();
		}
	}


	@Override
	public String init(Args args) {

		String att;
		att = args.getAttValue("-corrTLimit");
		if(att == null){return "err: -corrTLimit not set";}
		corrTLimit = Double.parseDouble(att);
		
		att = args.getAttValue("-meanBLimit");
		if(att == null){return "err: -meanBLimit not set";}
		meanBLimit = Double.parseDouble(att);
			
		att = args.getAttValue("-dsRiskTLimit");
		if(att == null){return "err: -dsRiskTLimit not set";}
		dsRiskTLimit = Double.parseDouble(att);
		
		att = args.getAttValue("-file");
		if(att == null){return "err: -file not set";}
		file = att;
		
		att = args.getAttValue("-resDir");
		if(att == null){return "err: -resDir not set";}
		resDir = att;
		
		return "";
	}

}
