package endpoint;

import java.io.IOException;

import data.bovespa.BovespaDigest;

public class DigestEndpoint implements Endpoint{

	String logFile;
	
	String digFile;
	
	@Override
	public String run() {
		
		try {
			BovespaDigest digest = new BovespaDigest(logFile);
			digest.writeDigestedCSVFile(digFile);
		} catch (IOException e) {
			return "err: " + e.getStackTrace();
		}
		
		return "";
	}

	@Override
	public String init(Args args) {
		String att;
		att = args.getAttValue("-logFile");
		if(att == null){return "err: -logFile not set";}
		logFile = att;
		
		att = args.getAttValue("-digFile");
		if(att == null){return "err: -digFile not set";}
		digFile = att;
		return "";
	}

}
