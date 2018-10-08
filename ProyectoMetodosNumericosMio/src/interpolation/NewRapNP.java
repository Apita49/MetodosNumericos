package interpolation;

import java.util.LinkedList;
import java.util.List;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class NewRapNP {
	private static DJep _j;

	private static Node _function;
	private static Node _primeFunction;

	private static double _x0;
	private static double _error;

	public static String calculate(String y, String x0, String error) {
		return answerToString(calculate(y, new Double(x0), new Double(error)));
	}

	public static void calculateForPDF(String y, String x0, String error,
			String title) {
		String content = answerToStringForPDF(
				calculate(y, new Double(x0), new Double(error)), y);
		PDFCreator.createPdfDoc(title, content);
	}

	public static String calculateForPDF(String y, String x0, String error) {
		return answerToStringForPDF(
				calculate(y, new Double(x0), new Double(error)), y);
	}

	public static List<Double> calculate(String y, double x0, double error) {
		setValues(y, x0, error);

		return newtonRaphsonMethod();
	}

	private static void setValues(String y, double x0, double error) {
		_j = new DJep();
		_j.addStandardConstants();
		_j.addStandardFunctions();
		_j.addComplex();
		_j.setAllowUndeclared(true);
		_j.setAllowAssignment(true);
		_j.setImplicitMul(true);
		_j.addStandardDiffRules();

		_x0 = x0;
		_error = error;

		try {
			Node uglyFunction = _j.parse(y);
			_function = _j.simplify(uglyFunction);

			_primeFunction = _j.differentiate(_function, "x");
		} catch (ParseException e) {
			// TODO Generate a parse exception message
		}

	}

	private static List<Double> newtonRaphsonMethod() {
		List<Double> answers = new LinkedList<>();
		double error = _x0;
		double xA = _x0;
		double xS = 0;
		answers.add(_x0);

		double fValue = 0;
		double pFValue = 0;
		try {
			do {
				_j.addVariable("x", xA);
				fValue = (double) _j.evaluate(_function);
				pFValue = (double) _j.evaluate(_primeFunction);
				xS = (xA - (fValue / pFValue));
				answers.add(xS);
				error = (xS - xA);
				xA = xS;
			} while (!(error > -_error && error < _error));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return answers;
	}

	private static String answerToString(List<Double> answers) {
		String stringAnswer = "";
		for (int i = 0; i < answers.size() - 1; i++) {
			stringAnswer += "x" + i + " = " + answers.get(i) + "<br>";
		}
		stringAnswer += "x" + (answers.size() - 1) + " = "
				+ answers.get(answers.size() - 1);

		return stringAnswer;
	}

	private static String answerToStringForPDF(List<Double> answers, String y) {
		String stringAnswer = "Iteraciones de la solucion de la ecuacion " + y
				+ " mediante el metodo de Newton Raphson:\n";
		for (int i = 0; i < answers.size() - 1; i++) {
			stringAnswer += "x" + i + " = " + answers.get(i) + "\n";
		}
		stringAnswer += "x" + (answers.size() - 1) + " = "
				+ answers.get(answers.size() - 1);

		return stringAnswer;
	}

	// public static void main(String[] args) {
	// // List<Double> answer = NewRapNP.calculate("x^3 - 4*x^2 + 8*x - 100",
	// // 3, 0.000009);
	// // for(int i = 0; i < answer.size(); i++) {
	// // System.out.println("x" + i + " = " + answer.get(i));
	// // }
	// // System.out.println(NewRapNP.calculate("x^3 - 4*x^2 + 8*x - 100", "3",
	// // "0.000009"));
	// String get = "hola papu!";
	// StringSelection selec = new StringSelection(get);
	// Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	// clipboard.setContents(selec, selec);
	//
	// }
}