package interpolation;

import pdfCreator.PDFCreator;

public class InterpolationsManager {
	public static String compararMetodosDeTabla(double eval) {
		return "<html>Newton Ascendente: "
				+ NewtonAscendente.newtonAscendente(GeneradorDeData.getData(),
						eval)
				+ "<br>Newton Descendente: "
				+ NewtonDescendente.newtonDescendente(
						GeneradorDeData.getData(), eval) + "<br>Lagrange: "
				+ LaGrange.laGrange(GeneradorDeData.getDataD(), eval)
				+ "</html>";
	}

	public static void compararMetodosDeTablaForPDF(double eval, String title) {
		String answer = "Mostrando el resultado de la tabla:\n"
				+ GeneradorDeData.dataEnString()
				+ " para el valor de "
				+ eval
				+ " mediante los metodos de Newton ascendente, Newton descendente y Lagrange\nNewton Ascendente: "
				+ NewtonAscendente.newtonAscendente(GeneradorDeData.getData(),
						eval)
				+ "\nNewton Descendente: "
				+ NewtonDescendente.newtonDescendente(
						GeneradorDeData.getData(), eval) + "\nLagrange: "
				+ LaGrange.laGrange(GeneradorDeData.getDataD(), eval);
		PDFCreator.createPdfDoc(title, answer);
	}

	public static String compararMetodosDeNoTabla(String y, String x0,
			String error) {
		if (error == null) {
			error = "0.00001";
		}
		if (error.equals("")) {
			error = "0.00001";
		}
		return "<html>Newton Raphson: <br>" + NewRapNP.calculate(y, x0, error)
				+ "<br>Aitken: <br>" + Steffensen.aitken(y, x0, error)
				+ "<br>Steffensen: <br>" + Steffensen.steffensen(y, x0, error)
				+ "</html>";
	}

	public static void compararMetodosDeNoTablaForPDF(String y, String x0,
			String error, String title) {
		String answer = "Mostrando el resultado de la funcion: " + y
				+ " con un x0 = " + x0 + " mediante:\nNewton Raphson: "
				+ NewRapNP.calculateForPDF(y, x0, error) + "\nAitken: "
				+ Steffensen.aitkenForPDF(y, x0, error) + "\nSteffensen: "
				+ Steffensen.steffensenForPDF(y, x0, error);
		PDFCreator.createPdfDoc(title, answer);
	}
}