package dataDigest.bovespa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BovespaDigest {

	String fileName;
	List<String> marketCodeList;
	int size;
	List<BovespaLog> bovespaLogList;
	Map<String, BovespaShare> bovespaShareMap;
	Set<Integer> logDaySet;
	Set<String> tradeCodeSet;

	public Set<Integer> getLogDaySet() {
		return logDaySet;
	}

	public Set<String> getTradeCodeSet() {
		return tradeCodeSet;
	}

	private boolean marketCodeCondition(BovespaLog log) {
		int i = 0;
		while (i < marketCodeList.size() && !marketCodeList.get(i).equals(log.marketCode)) {
			i++;
		}
		return i < marketCodeList.size();
	}

	public BovespaDigest(String fileName) throws IOException {
		initFlags();
		initBovespaLogList(fileName);
		initBovespaShareMap();
		initWritingKeys();
	}

	private void initFlags() {
		marketCodeList = new ArrayList<>();
		marketCodeList.add("010");
		marketCodeList.add("020");
		size = 200;
	}

	private void initBovespaLogList(String fileName) throws IOException {
		this.fileName = fileName;
		bovespaLogList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.substring(0, 2).equals("01"))
				bovespaLogList.add(new BovespaLog(line));
		}
		reader.close();
	}

	private void initBovespaShareMap() {

		bovespaShareMap = new HashMap<>();
		for (BovespaLog log : bovespaLogList) {
			if (marketCodeCondition(log)) {
				bovespaShareMap.put(log.tradeCode, new BovespaShare(log.tradeCode));
			}
		}

		for (BovespaLog log : bovespaLogList) {
			if (marketCodeCondition(log)) {
				bovespaShareMap.get(log.tradeCode).logDayMap.put(log.logDay, log);
			}
		}
	}

	private void initWritingKeys() {
		logDaySet = new TreeSet<>();
		tradeCodeSet = new TreeSet<>();
		for (BovespaLog log : bovespaLogList) {
			if (marketCodeCondition(log) && bovespaShareMap.get(log.tradeCode).logDayMap.size() > size) {
				logDaySet.add(log.logDay);
				tradeCodeSet.add(log.tradeCode);
			}
		}
	}

	public void writeDigestedCSVFile(String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		String line = "tradeCode";
		Integer[] logDayArray = logDaySet.toArray(new Integer[logDaySet.size()]);
		for (Integer logDay : logDayArray) {
			line += "," + logDay;
		}
		writer.println(line);

		for (String tradeCode : tradeCodeSet.toArray(new String[tradeCodeSet.size()])) {
			line = tradeCode;
			for (Integer logDay : logDayArray) {
				if (bovespaShareMap.get(tradeCode).logDayMap.containsKey(logDay)) {
					line += "," + bovespaShareMap.get(tradeCode).logDayMap.get(logDay).getOpeningPrice();
				} else {
					line += ",null";
				}
			}
			writer.println(line);
		}

		writer.close();
	}

}
