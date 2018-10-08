package interpolation;

import java.util.LinkedList;
import java.util.List;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;

import pdfCreator.PDFCreator;

public class Steffensen {

	// public static void main(String[] args) {
	// System.out.println(aitken("x^3 + 2x^2 + 10x - 20", "1", "0.001"));
	// }

	public static double puntoFijo(String y, double xA) {
		return (xA - ((evaluateFunc(y, xA)) / (diffFunc(y, xA))));
	}

	public static void aitkenForPDF(String y, String x0, String error,
			String title) {
		if (error.equals("")) {
			error = "0.00009";
		}
		String content = answerToStringForPDF(
				aitken(y, new Double(x0), new Double(error)), y, "Aitken");
		PDFCreator.createPdfDoc(title, content);
	}

	public static String aitkenForPDF(String y, String x0, String error) {
		if (error.equals("")) {
			error = "0.00009";
		}
		return answerToStringForPDF(
				aitken(y, new Double(x0), new Double(error)), y, "Aitken");
	}

	public static String aitken(String y, String x0, String error) {
		if (error.equals("")) {
			error = "0.00009";
		}
		return answerToString(aitken(y, new Double(x0), new Double(error)));
	}

	public static List<List<Double>> aitken(String y, double x0, double error) {
		List<List<Double>> respuestas = new LinkedList<>();
		List<Double> xs = new LinkedList<>();
		List<Double> px = new LinkedList<>();
		List<Double> epsilon = new LinkedList<>();

		xs.add(x0);
		for (int i = 0; i < 3; i++) {
			xs.add(puntoFijo(y, xs.get(xs.size() - 1)));
		}

		px.add(calcPrimos(xs.get(0), xs.get(1), xs.get(2)));

		int i = 1;
		double errorActual = 0;
		do {
			px.add(calcPrimos(xs.get(i), xs.get(i + 1), xs.get(i + 2)));
			i++;
			xs.add(puntoFijo(y, xs.get(xs.size() - 1)));
			errorActual = Math.abs(px.get(i - 1) - px.get(i - 2));
			epsilon.add(errorActual);
		} while (errorActual > error);
		respuestas.add(xs);
		respuestas.add(px);
		respuestas.add(epsilon);
		return respuestas;
	}

	private static double calcPrimos(double x0, double x1, double x2) {
		double deltaX = 0;
		double deltaCuadX = 0;

		deltaX = x1 - x0;
		deltaCuadX = x2 - (2 * x1) + x0;
		return x0 - (Math.pow(deltaX, 2) / (deltaCuadX));
	}

	public static String steffensen(String y, String x0, String error) {
		if (error.equals("")) {
			error = "0.00009";
		}
		return answerToString(steffensen(y, new Double(x0), new Double(error)));
	}

	public static void steffensenForPDF(String y, String x0, String error,
			String title) {
		if (error.equals("")) {
			error = "0.00009";
		}
		String content = answerToStringForPDF(
				steffensen(y, new Double(x0), new Double(error)), y,
				"Steffensen");
		PDFCreator.createPdfDoc(title, content);
	}

	public static String steffensenForPDF(String y, String x0, String error) {
		if (error.equals("")) {
			error = "0.00009";
		}
		return answerToStringForPDF(
				steffensen(y, new Double(x0), new Double(error)), y,
				"Steffensen");
	}

	private static List<List<Double>> steffensen(String y, double x0,
			double error) {
		List<List<Double>> answer = new LinkedList<>();
		List<Double> px = new LinkedList<>();
		List<Double> xs = new LinkedList<>();
		List<Double> epsilon = new LinkedList<>();

		xs.add(x0);
		for (int i = 0; i < 2; i++) {
			xs.add(puntoFijo(y, xs.get(xs.size() - 1)));
		}

		px.add(calcPrimos(xs.get(0), xs.get(1), xs.get(2)));

		int i = 3;
		double errorActual = 0;
		do {
			xs.add(px.get(px.size() - 1));
			for (int j = 0; j < 2; j++) {
				xs.add(puntoFijo(y, xs.get(xs.size() - 1)));
			}
			px.add(calcPrimos(xs.get(i), xs.get(i + 1), xs.get(i + 2)));
			i += 3;
			errorActual = Math.abs(px.get(px.size() - 1)
					- px.get(px.size() - 2));
			epsilon.add(errorActual);
		} while (errorActual > error);

		answer.add(xs);
		answer.add(px);
		answer.add(epsilon);

		return answer;
	}

	private static int[] maxLength(List<List<Double>> ans) {
		int[] maxs = new int[2];
		int maxNumLength = 0;
		int currentLength = 0;
		for (int i = 0; i < ans.size() - 1; i++) {
			for (int j = 0; j < ans.get(i).size(); j++) {
				currentLength = ans.get(i).get(j).toString().length();
				if (currentLength > maxNumLength) {
					maxNumLength = currentLength;
				}
			}
		}
		maxs[0] = ((ans.get(0).size() - 1) + "").length() + 3;
		maxs[1] = maxNumLength + 3;
		return maxs;
	}

	private static String fillSpace(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum; i++) {
			spaces += "&nbsp;";
		}

		return spaces;
	}

	private static String fillSpaceForPDF(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum; i++) {
			spaces += " ";
		}

		return spaces;
	}

	private static String answerToString(List<List<Double>> answers) {
		int[] maxLength = maxLength(answers);
		String stringAnswer = " i" + fillSpace(maxLength[0] - 1) + "|xi"
				+ fillSpace(maxLength[1] - 2) + "|x'i"
				+ fillSpace(maxLength[1] - 3) + "|Error<br>";
		for (int i = 0; i < answers.get(0).size(); i++) {
			stringAnswer += i + fillSpace(maxLength[0] - ("" + i).length())
					+ "|";
			for (int j = 0; j < answers.size(); j++) {
				if (i < answers.get(j).size()) {
					stringAnswer += answers.get(j).get(i)
							+ fillSpace(maxLength[1]
									- ("" + answers.get(j).get(i)).length());
					if (j < answers.size() - 1) {
						stringAnswer += "|";
					} else {
						stringAnswer += "<br>";
					}
				} else {
					if (j < answers.size() - 1) {
						stringAnswer += "NN" + fillSpace(maxLength[1] - 2)
								+ "|";
					} else {
						if (i < answers.get(0).size() - 1) {
							stringAnswer += "NN<br>";
						} else {
							stringAnswer += "NN<br>";
						}
					}
				}
			}
		}
		return stringAnswer;
	}

	private static String answerToStringForPDF(List<List<Double>> answers,
			String funcion, String metodo) {
		int[] maxLength = maxLength(answers);
		String stringAnswer = "Tabla de soluciones de la funcion: " + funcion
				+ " mediante el metodo de " + metodo + ":\n";
		stringAnswer += "i" + fillSpaceForPDF(maxLength[0] - 1) + "|xi"
				+ fillSpaceForPDF(maxLength[1] - 2) + "|x'i"
				+ fillSpaceForPDF(maxLength[1] - 3) + "|Error\n";
		for (int i = 0; i < answers.get(0).size(); i++) {
			stringAnswer += i
					+ fillSpaceForPDF(maxLength[0] - ("" + i).length()) + "|";
			for (int j = 0; j < answers.size(); j++) {
				if (i < answers.get(j).size()) {
					stringAnswer += answers.get(j).get(i)
							+ fillSpaceForPDF(maxLength[1]
									- ("" + answers.get(j).get(i)).length());
					if (j < answers.size() - 1) {
						stringAnswer += "|";
					} else {
						stringAnswer += "\n";
					}
				} else {
					if (j < answers.size() - 1) {
						stringAnswer += "NN"
								+ fillSpaceForPDF(maxLength[1] - 2) + "|";
					} else {
						if (i < answers.get(0).size() - 1) {
							stringAnswer += "NN\n";
						} else {
							stringAnswer += "NN";
						}
					}
				}
			}
		}
		return stringAnswer;
	}

	private static double evaluateFunc(String func, double var) {
		DJep _j = new DJep();
		_j.addStandardConstants();
		_j.addStandardFunctions();
		_j.addComplex();
		_j.setAllowUndeclared(true);
		_j.setAllowAssignment(true);
		_j.setImplicitMul(true);
		_j.addStandardDiffRules();

		_j.addVariable("x", var);
		try {
			Node uglyFunction = _j.parse(func);
			Node function = _j.simplify(uglyFunction);

			return (double) _j.evaluate(function);
		} catch (Exception e) {
			// TODO Generate a parse exception message
		}
		return 0;
	}

	private static double diffFunc(String func, double var) {
		DJep _j = new DJep();
		_j.addStandardConstants();
		_j.addStandardFunctions();
		_j.addComplex();
		_j.setAllowUndeclared(true);
		_j.setAllowAssignment(true);
		_j.setImplicitMul(true);
		_j.addStandardDiffRules();

		_j.addVariable("x", var);
		try {
			Node uglyFunction = _j.parse(func);
			Node function = _j.simplify(uglyFunction);
			Node diffFunction = _j.differentiate(function, "x");

			return (double) _j.evaluate(diffFunction);
		} catch (Exception e) {
			// TODO Generate a parse exception message
		}
		return 0;
	}

}