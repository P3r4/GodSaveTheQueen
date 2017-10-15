package endpoint;

public class Args {
	
	private String[] args;
	
	public Args(String[] args){
		this.args = args;
	}
	
	private int getAttIndex(String att) {
		int i = 0;
		while (i < args.length && !args[i].equals(att))
			i++;
		return i;
	}
	
	public String getAttValue(String attName){
		int i;
		String out;
		i = getAttIndex(attName);
		if (i == args.length) {
			out = null;
		} else {
			out = args[i + 1];
		}
		return out;
	}
	
}
