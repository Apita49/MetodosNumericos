package interpolation;

import java.util.LinkedList;
import java.util.List;

import pdfCreator.PDFCreator;

public class NewtonDescendente {

	public static List<Integer> fact = new LinkedList<Integer>();

	public static void main(String[] args) {
		double[][] tabla = { { -2.0, -7.0 }, { -1.0, -4.0 }, { 0.0, -1.0 },
				{ 1.0, 8.0 }, { 2.0, 29.0 } };
		System.out.println(newtonDescendente(tabla));

	}

	public static void newtonDescendenteForPDF(double[][] tabla, double eval,
			String title) {
		String content = "Evaluando " + eval
				+ " por el metodo de Newton Descendente en la funcion definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nResultado = "
				+ newtonDescendente(tabla, eval);
		PDFCreator.createPdfDoc(title, content);

	}

	public static void newtonDescendenteForPDF(String funcion, String title) {
		String content = "Funcion generada por el metodo de Newton Descendente, definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nFuncion = " + funcion;
		PDFCreator.createPdfDoc(title, content);
	}

	public static double newtonDescendente(double[][] tabla, double eval) {
		int n = tabla.length;
		int indice = encontrarIndice(tabla, eval);
		double h = tabla[1][0] - tabla[0][0];
		double u = (eval - tabla[indice][0]) / h;
		if (fact.size() == 0) {
			fact.add(0);
			fact.add(1);
		}

		List<List<Double>> nablas = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			nablas.add(new LinkedList<Double>());
			nablas.get(0).add(tabla[i][1]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < nablas.get(i - 1).size() - 1; j++) {
				nablas.get(i)
						.add(nablas.get(i - 1).get(j + 1)
								- nablas.get(i - 1).get(j));
			}
		}
		double p = tabla[indice][1];
		for (int i = 1; i < nablas.size(); i++) {
			indice--;
			if (indice >= 0) {
				double productoU = u;
				for (int j = 1; j < i; j++) {
					productoU *= (u + j);
				}
				p += (nablas.get(i).get(indice) * productoU) / factorial(i);
			} else {
				break;
			}
		}
		return p;

	}

	public static String newtonDescendente(double[][] tabla) {
		int n = tabla.length;
		double h = tabla[1][0] - tabla[0][0];
		if (fact.size() == 0) {
			fact.add(0);
			fact.add(1);
		}

		List<List<Double>> nablas = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			nablas.add(new LinkedList<Double>());
			nablas.get(0).add(tabla[i][1]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < nablas.get(i - 1).size() - 1; j++) {
				nablas.get(i)
						.add(nablas.get(i - 1).get(j + 1)
								- nablas.get(i - 1).get(j));
			}
		}

		Double yn = tabla[n - 1][1];
		String p = yn.toString();
		for (int i = 1; i < nablas.size(); i++) {
			String productoX = "";
			Double ad = nablas.get(i).get(nablas.get(i).size() - 1)
					/ (factorial(i) * Math.pow(h, i));
			for (int j = 0; j < i; j++) {
				Double xi = tabla[n - 1 - j][0];
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

	public static Integer factorial(int x) {
		if (x == 1) {
			return 1;
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