package evoCover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Evo {

	EvoCoverGraph graph;
	int popQtt;
	
	public Evo(int qtt, String fileName) throws IOException{
		this.popQtt = qtt;
		graph =  new EvoCoverGraph(qtt,fileName);
	}
	
	public void evo1(int genQtt, double alfa, int c){
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.calcSemiVarAndSkewnessForAll();
			graph.employedBeePhase20(alfa, c);
			//phase2???
			//phase3???
		}
	}
	
	public void evo16(int genQtt){
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.calcSemiVarAndSkewnessForAll();
			graph.crossOver16(Math.floorDiv(popQtt, 2));
			graph.mutation16(0.05);
			//clearExtraSolutions
		}
	}
	
	public void evo10(int genQtt){
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.calcSemiVarAndSkewnessForAll();
			graph.crossOver10(Math.floorDiv(popQtt, 2));
			graph.mutation10();
			//clearExtraSolutions
		}
	}

	public void evo1615(int genQtt){
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.calcSemiVarAndSkewnessForAll();
			graph.crossOver16(Math.floorDiv(popQtt, 2));
			graph.mutation15();
			//clearExtraSolutions
		}
	}
	
	public void evo1015(int genQtt){
		graph.randomInit();
		for (int i = 0; i < genQtt; i++) {
			graph.calcSemiVarAndSkewnessForAll();
			graph.crossOver10(Math.floorDiv(popQtt, 2));
			graph.mutation15();
			//clearExtraSolutions
		}
	}	

}
