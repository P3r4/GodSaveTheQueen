package dataDigest.bovespa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BovespaDigest {

	String fileName;
	List<BovespaLog> bovespaLogList;
	Map<String, BovespaShare> bovespaShareMap;
	Set<Integer> logDaySet;
	Set<String> tradeCodeSet;
	int size;
	float tradeCodeXLogDay[][];

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
		this.logDaySet = new HashSet<>();
		this.tradeCodeSet = new HashSet<>();
		for (BovespaLog log : this.bovespaLogList) {
			this.bovespaShareMap.put(log.tradeCode, new BovespaShare(log.tradeCode));
			this.logDaySet.add(log.logDay);
			this.tradeCodeSet.add(log.tradeCode);
		}

		for (String tradeCode : this.tradeCodeSet.toArray(new String[this.tradeCodeSet.size()])) {
			for (Integer logDay : this.logDaySet.toArray(new Integer[this.logDaySet.size()])) {
				this.bovespaShareMap.get(tradeCode).logDayMap.put(logDay, null);
			}
		}

		for (BovespaLog log : this.bovespaLogList) {
			this.bovespaShareMap.get(log.tradeCode).logDayMap.put(log.logDay, log);
		}
	}

	public void writeTradeCodeXLogDayCSVFile(String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		String line = "row,tradeCode";
		for (Integer logDay : this.logDaySet.toArray(new Integer[this.logDaySet.size()])) {
			line += "," + logDay;
		}
		writer.println(line);

		int i = 0;
		BovespaLog log;
		for (String tradeCode : this.tradeCodeSet.toArray(new String[this.tradeCodeSet.size()])) {
			line = "" + i + "," + tradeCode;
			for (Integer logDay : this.logDaySet.toArray(new Integer[this.logDaySet.size()])) {
				log = this.bovespaShareMap.get(tradeCode).logDayMap.get(logDay);
				if (log == null)
					line += ",null";
				else
					line += ","+ log.openingPrice;
			}
			writer.println(line);
		}

		writer.close();
	}

}
