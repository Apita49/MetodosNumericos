package linearSystems;

public class Answer {
	private double[] solution;
	private double error;

	public Answer(double[] a, double e) {
		solution = a;
		error = e;
	}
	
	public double[] getSolution() {
		return solution;
	}
	
	public double getError() {
		return error;
	}
}