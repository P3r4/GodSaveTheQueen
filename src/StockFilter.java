

public class StockFilter {
	
	private static int getAttIndex(String[] args, String att) {

		int i = 0;
		while (i < args.length && !args[i].equals(att))
			i++;
		return i;

	}

	public static void main(String[] args) {
		
	}
}
