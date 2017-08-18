package measure;

public class Correlation implements Measure{

	private float[] vectorA;
	private float[] vectorB;
	
	
	public Correlation(float[] vectorA, float[] vectorB){
		this.vectorA = vectorA;
		this.vectorB = vectorB;
	}
	
	@Override
	public float getResult() {		
		return 0.0f;
	}

}
