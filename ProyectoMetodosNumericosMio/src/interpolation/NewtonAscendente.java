package interpolation;

import java.util.LinkedList;
import java.util.List;

import pdfCreator.PDFCreator;

public class NewtonAscendente {

	public static List<Double> fact = new LinkedList<>();

	public static void main(String[] args) {
		double[][] tabla = { { -3.0, 66.0 }, { -1.0, 2.0 }, { 0.0, 3.0 },
				{ 1.0, 2.0 }, { 3.0, 66.0 } };
		System.out.println(newtonAscendente(tabla));

	}

	public static void newtonAscendenteForPDF(double[][] tabla, double eval,
			String title) {
		String content = "Evaluando "
				+ eval
				+ " por el metodo de Newton Ascendente en la funcion definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nResultado = "
				+ newtonAscendente(tabla, eval);
		PDFCreator.createPdfDoc(title, content);
	}

	public static void newtonAscendenteForPDF(String funcion, String title) {
		String content = "Funcion generada por el metodo de Newton Ascendente, definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nFuncion = " + funcion;
		PDFCreator.createPdfDoc(title, content);
	}

	public static double newtonAscendente(double[][] tabla, double eval) {
		int n = tabla.length;
		double h = tabla[1][0] - tabla[0][0];
		int indice = encontrarIndice(tabla, eval);
		double u = (eval - tabla[indice][0]) / h;
		if (fact.size() == 0) {
			fact.add(0.0);
			fact.add(1.0);
		}

		List<List<Double>> deltas = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			deltas.add(new LinkedList<Double>());
			deltas.get(0).add(tabla[i][1]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < deltas.get(i - 1).size() - 1; j++) {
				deltas.get(i)
						.add(deltas.get(i - 1).get(j + 1)
								- deltas.get(i - 1).get(j));
			}
		}

		double p = tabla[indice][1];
		for (int i = 1; i < deltas.size(); i++) {
			if (indice < deltas.get(i).size()) {
				double productoU = u;
				for (int j = 1; j < i; j++) {
					productoU *= (u - j);
				}
				p += (deltas.get(i).get(indice) * productoU) / factorial(i);
			} else {
				break;
			}
		}

		return p;

	}

	public static String newtonAscendente(double[][] tabla) {
		int n = tabla.length;
		double h = tabla[1][0] - tabla[0][0];
		if (fact.size() == 0) {
			fact.add(0.0);
			fact.add(1.0);
		}

		List<List<Double>> deltas = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			deltas.add(new LinkedList<Double>());
			deltas.get(0).add(tabla[i][1]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < deltas.get(i - 1).size() - 1; j++) {
				deltas.get(i)
						.add(deltas.get(i - 1).get(j + 1)
								- deltas.get(i - 1).get(j));
			}
		}

		Double y0 = tabla[0][1];
		String p = y0.toString();
		for (int i = 1; i < deltas.size(); i++) {
			String productoX = "";
			Double ad = deltas.get(i).get(0) / (factorial(i) * h);
			for (int j = 0; j < i; j++) {
				Double xi = tabla[j][0];
				if (j == 0) {
					xi *= ad;
					productoX = productoX + "(" + ad + "x - " + xi + ")";
				} else {
					productoX = productoX + "(" + "x - " + xi + ")";
				}
				if (j != i - 1) {
					productoX += "*";
				}
			}
			p = p + " + (" + productoX + ")";

		}

		return p;

	}

	public static Double factorial(int x) {
		if (x == 1) {
			return 1.0;
		} else if (fact.size() - 1 >= x) {
			return fact.get(x);
		} else {
			for (int i = fact.size() - 1; i <= x; i++) {
				fact.add((i + 1) * factorial(i));
			}

		}

		return fact.get(x);
	}

	public static int encontrarIndice(double[][] xi, double eval) {
		int indice = 0;
		double dif = Double.MAX_VALUE;

		for (int i = 0; i < xi.length; i++) {
			double x = Math.abs(eval - xi[i][0]);
			if (x < dif) {
				dif = x;
				indice = i;
			}
		}

		return indice;

	}

}