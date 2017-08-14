package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BovespaDigest {

	String fileName;
	String header;
	String trailer;
	List<BovespaLog> bovespaLogList;
	Map<String, BovespaShare> bovespaShareMap;
	int size;

	public BovespaDigest(String fileName) throws Exception {
		this.fileName = fileName;
		this.bovespaLogList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.substring(0, 2).equals("01"))
				bovespaLogList.add(new BovespaLog(line));
		}
		reader.close();

		this.bovespaShareMap = new HashMap<>();
		for (BovespaLog bLog : this.bovespaLogList) {
			this.bovespaShareMap.put(bLog.tradeCode, new BovespaShare(bLog.tradeCode));
		}

		for (BovespaLog bLog : this.bovespaLogList) {
			this.bovespaShareMap.get(bLog.tradeCode).logList.add(bLog);
		}
		int i = 0;
		for (Entry<String, BovespaShare> e : bovespaShareMap.entrySet()) {
			i++;
			if (e.getValue().logList.size() > 0) {

				System.out.println(e.getKey() + ": " + e.getValue().logList.size() + ": "
						+ e.getValue().logList.get(0).factor + " : " + e.getValue().logList.get(0).marketCode + " : "
						+ e.getValue().logList.get(0).shareTypeCode);

			}
		}
		System.out.println(i);
		
		for(BovespaLog l : this.bovespaShareMap.get("TIMP3F").logList){
			System.out.println(l.logDate);
		}
		
	}

}
