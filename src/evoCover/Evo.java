package evoCover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Evo {

	EvoCoverGraph graph;
	
	public Evo(int qtt, String fileName) throws IOException{
		graph =  new EvoCoverGraph(qtt,fileName);
	}

	

}
