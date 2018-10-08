package interpolation;

import pdfCreator.PDFCreator;

public class LaGrange {

	// public static void main(String[] args) {
	// Double[][] tabla = { { -1.0, 4.0 }, { 0.0, 1.0 }, { 1.0, 2.0 }, { 2.0,
	// 1.0 }, { 3.0, -8.0 } };
	// System.out.println(laGrange(tabla));
	// }

	public static double laGrange(Double[][] tabla, double eval) {
		double p = 0;
		for (int i = 0; i < tabla.length; i++) {
			p += (productoriaNums(tabla, i, eval) / productoriaNums(tabla, i,
					tabla[i][0])) * tabla[i][1];
		}
		return p;
	}

	public static void laGrangeForPDF(Double[][] tabla, double eval,
			String title) {
		String content = "Evaluando "
				+ eval
				+ " por el metodo de laGrange en la funcion definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nResultado = "
				+ laGrange(tabla, eval);
		PDFCreator.createPdfDoc(title, content);

	}

	public static void laGrangeForPDF(String funcion, String title) {
		String content = "Funcion generada por el metodo de laGrange, definida por la siguiente tabla;\n"
				+ GeneradorDeData.dataEnString() + "\nFuncion = " + funcion;
		PDFCreator.createPdfDoc(title, content);
	}

	public static String laGrange(Double[][] tabla) {
		String p = "";
		for (int i = 0; i < tabla.length; i++) {
			Double ad = tabla[i][1] / productoriaNums(tabla, i, tabla[i][0]);
			p = p + "(" + productoriaString(tabla, i, ad) + ")";
			if (i != tabla.length - 1) {
				p = p + " + ";
			}
		}
		return p;
	}

	public static Double productoriaNums(Double[][] xi, int r, double x) {
		double ans = 1;
		for (int i = 0; i < xi.length; i++) {
			if (i != r) {
				ans *= (x - xi[i][0]);
			}
		}

		return ans;
	}

	public static String productoriaString(Double[][] tabla, int r, Double ad) {
		String ans = "";
		for (int i = 0; i < tabla.length; i++) {
			if (i != r) {
				Double xi = tabla[i][0];
				if (i == 0 || (i == 1 && r == 0)) {
					xi *= ad;
					ans = ans + "(" + ad + "x - " + xi + ")";
				} else {
					ans = ans + "(" + "x - " + xi + ")";
				}
				if (i != tabla.length - 1) {
					if (r != tabla.length - 1 || i != tabla.length - 2) {
						ans += "*";
						// nada
					} else {
					}
				}
			}
		}

		return ans;
	}

}