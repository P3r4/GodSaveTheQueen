package endpoint;

public class Start {

	public static void main(String[] args) {
		Endpoint config;
		Args _args = new Args(args);
		String att = _args.getAttValue("-tool");
		String mssg = "";

		if (att == null)
			System.out.println("err: -tool not set");
		else {
			if (att.equals("filter")) {
				config = new FilterEndpoint();
				mssg = config.init(_args);
				if (mssg.equals(""))
					mssg = config.run();

			} else if (att.equals("digest")) {
				config = new DigestEndpoint();
				mssg = config.init(_args);
				if (mssg.equals(""))
					mssg = config.run();

			} else if (att.equals("process")) {
				config = new ProcessEndpoint();
				mssg = config.init(_args);
				if (mssg.equals(""))
					mssg = config.run();

			} else {
				System.out.println("err: invalid -tool");
			}
			if (mssg.equals(""))
				System.out.println(":)");
			else
				System.out.println(mssg);
			
		}
	}

}
