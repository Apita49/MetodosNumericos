package numericIntegration;

import pdfCreator.PDFCreator;

public class IntegralsManager {
	public static String calculateAllSimpleIntegrals(String a, String b,
			String n, String nP, String function) {
		String answer = "<html>Mediante Trapecios: "
				+ Trapecios.calculate(function, a, b, nP, true)[0]
				+ "<br>Mediante Simpson: "
				+ Simpson.calculate(function, a, b, nP, true)[0]
				+ "<br>Mediante Newton Cotes: "
				+ NewtonCotes.newtonCotes(a, b, n, function)
				+ "<br>Mediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss1(a, b, n, function) + "</html>";
		return answer;
	}

	public static void calculateAllSimpleIntegralsForPDF(String a, String b,
			String n, String nP, String function, String title) {
		String answer = "Resultados de la integral de la funcion: \""
				+ function + "\" evaluada desde " + a + " hasta " + b
				+ " mediante los metodos de: Trapecios y Simpson con n = " + nP
				+ ", Newton Cotes y cuadratura de Gauss con n = " + n
				+ ".\nMediante Trapecios: "
				+ Trapecios.calculate(function, a, b, nP, true)[0]
				+ "\nMediante Simpson: "
				+ Simpson.calculate(function, a, b, nP, true)[0]
				+ "\nMediante Newton Cotes: "
				+ NewtonCotes.newtonCotes(a, b, n, function)
				+ "\nMediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss1(a, b, n, function);
		PDFCreator.createPdfDoc(title, answer);
	}

	public static String calculateAllSimpleAreas(String a, String b, String n,
			String function) {
		String answer = "<html>Mediante Trapecios: "
				+ Trapecios.calculate(function, a, b, n, false)[0]
				+ "<br>Mediante Simpson: "
				+ Simpson.calculate(function, a, b, n, false)[0] + "</html>";
		return answer;
	}

	public static void calculateAllSimpleAreasForPDF(String a, String b,
			String n, String function, String title) {
		String answer = "Resultados del calculo del area de la funcion: \""
				+ function + "\" evaluada desde " + a + " hasta " + b
				+ " con un n = " + n + "\nMediante Trapecios: "
				+ Trapecios.calculate(function, a, b, n, false)[0]
				+ "\nMediante Simpson: "
				+ Simpson.calculate(function, a, b, n, false)[0];
		PDFCreator.createPdfDoc(title, answer);
	}

	public static String calculateAllDoubleIntegrals(String a, String b,
			String c, String d, String n, String f) {
		String answer = "<html>Mediante Newton Cotes: "
				+ NewtonCotes.newtonCotes2(a, b, c, d, n, f)
				+ "<br>Mediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss2(a, b, c, d, n, f) + "</html>";
		return answer;
	}

	public static void calculateAllDoubleIntegralsForPDF(String a, String b,
			String c, String d, String n, String f, String title) {
		String answer = "Resultados de la doble integral de la funcion: \"" + f
				+ "\" evaluada en x desde " + a + " hasta " + b
				+ " y evaluada en \"y\" desde " + c + " hasta " + d
				+ " con un n = " + n + "\nMediante Newton Cotes: "
				+ NewtonCotes.newtonCotes2(a, b, c, d, n, f)
				+ "\nMediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss2(a, b, c, d, n, f);
		PDFCreator.createPdfDoc(title, answer);
	}

	public static String calculateAllDoubleWithFunctionIntegrals(String a,
			String b, String c, String d, String n, String f) {
		String answer = "<html>Mediante Newton Cotes: "
				+ NewtonCotes.newtonCotesFunc(a, b, c, d, n, f)
				+ "<br>Mediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.cuadraturaFunc(a, b, c, d, n, f)
				+ "</html>";
		return answer;
	}

	public static void calculateAllDoubleWithFunctionIntegralsForPDF(String a,
			String b, String c, String d, String n, String f, String title) {
		String answer = "Resultados de la doble integral de la funcion: \"" + f
				+ "\" evaluada en x desde " + a + " hasta " + b
				+ " y evaluada en \"y\" desde " + c + " hasta " + d
				+ " con un n = " + n + "\nMediante Newton Cotes: "
				+ NewtonCotes.newtonCotesFunc(a, b, c, d, n, f)
				+ "\nMediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.cuadraturaFunc(a, b, c, d, n, f);
		PDFCreator.createPdfDoc(title, answer);
	}

	public static String calculateAllTripleIntegrals(String a, String b,
			String c, String d, String g, String h, String n, String f) {
		String answer = "<html>Mediante Newton Cotes: "
				+ NewtonCotes.newtonCotes3(a, b, c, d, g, h, n, f)
				+ "<br>Mediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss3(a, b, c, d, g, h, n, f) + "</html>";
		return answer;
	}

	public static void calculateAllTripleIntegralsForPDF(String a, String b,
			String c, String d, String g, String h, String n, String f,
			String title) {
		String answer = "Resultados de la triple integral de la funcion: \""
				+ f + "\" evaluada en x desde " + a + " hasta " + b
				+ ", evaluada en \"y\" desde " + c + " hasta " + d
				+ " y evaluada en \"z\" desde " + g + " hasta " + h
				+ " con un n = " + n + "\nMediante Newton Cotes: "
				+ NewtonCotes.newtonCotes3(a, b, c, d, g, h, n, f)
				+ "\nMediante la cuadratura de Gauss: "
				+ CuadraturaDeGauss.gauss3(a, b, c, d, g, h, n, f);
		PDFCreator.createPdfDoc(title, answer);
	}

	// public static void main(String[] args) {
	// System.out.println(calculateAllTripleIntegrals("sin(x)sin(y)sin(z)", "0",
	// "3.1416", "0", "3.1416", "0", "3.1416", "6"));
	// }
}