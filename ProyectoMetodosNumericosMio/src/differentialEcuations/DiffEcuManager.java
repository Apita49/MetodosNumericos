package differentialEcuations;

import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class DiffEcuManager {
	public static String compararMetodos(String f, String h, String x0,
			String y0, String eval, boolean iteraciones) {
		try {
			return "<html>Runge Kutta:<br>"
					+ RungeKutta.ansToString(RungeKutta.rungeKutta(f,
							new Double(h), new Double(x0), new Double(y0),
							new Integer(eval)), iteraciones)
					+ "<br>Euler:<br>"
					+ Euler.ansToString(Euler.euler(f, new Double(h),
							new Double(x0), new Double(y0), new Integer(eval)),
							iteraciones)
					+ "<br>Euler Modificado:<br>"
					+ EulerModificado.ansToString(EulerModificado
							.eulerModificado(f, new Double(h), new Double(x0),
									new Double(y0), new Integer(eval)),
							iteraciones)
					+ "<br>Euler Mejorado:<br>"
					+ EulerMejorado.ansToString(EulerMejorado.eulerMejorado(f,
							new Double(h), new Double(x0), new Double(y0),
							new Integer(eval)), iteraciones);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void compararMetodosForPDF(String f, String h, String x0,
			String y0, String eval, boolean iteraciones, String title) {
		String content = "";
		try {
			content = RungeKutta.ansToStringForPDF(RungeKutta.rungeKutta(f,
					new Double(h), new Double(x0), new Double(y0), new Integer(
							eval)), f, h, x0, y0, eval, iteraciones)
					+ Euler.ansToStringForPDF(Euler.euler(f, new Double(h),
							new Double(x0), new Double(y0), new Integer(eval)),
							f, h, x0, y0, eval, iteraciones)
					+ EulerMejorado.ansToStringForPDF(EulerMejorado
							.eulerMejorado(f, new Double(h), new Double(x0),
									new Double(y0), new Integer(eval)), f, h,
							x0, y0, eval, iteraciones)
					+ EulerModificado.ansToStringForPDF(EulerModificado
							.eulerModificado(f, new Double(h), new Double(x0),
									new Double(y0), new Integer(eval)), f, h,
							x0, y0, eval, iteraciones);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PDFCreator.createPdfDoc(title, content);
	}

	public static String compararMetodos2ndoOrden(String f, String g, String h,
			String n, String x0, String y0, String t0, boolean iteraciones) {
		try {
			return "<html>Runge Kutta:<br>"
					+ RungeKutta.ansToString2ndoOrden(RungeKutta
							.rungeKutta2ndoOrden(f, g, new Double(h),
									new Integer(n), new Double(x0), new Double(
											y0), new Double(t0)), iteraciones)
					+ "<br>Euler:<br>"
					+ Euler.ansToString2ndoOrden(Euler.eulerSeg(f, g,
							new Double(h), new Double(x0), new Double(y0),
							new Double(t0), new Integer(n)), iteraciones)
					+ "<br>Euler Mejorado:<br>"
					+ EulerMejorado.ansToString2ndoOrden(EulerMejorado
							.eulerMejoradoSeg(f, g, new Double(h), new Double(
									x0), new Double(y0), new Double(t0),
									new Integer(n)), iteraciones)
					+ "<br>Euler Modificado:<br>"
					+ EulerModificado.ansToString2ndoOrden(EulerModificado
							.eulerModificadoSeg(f, g, new Double(h),
									new Double(x0), new Double(y0), new Double(
											t0), new Integer(n)), iteraciones)
					+ "</html>";
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void compararMetodos2ndoOrdenForPDF(String f, String g,
			String h, String n, String x0, String y0, String t0,
			boolean iteraciones, String title) {
		String content = "";
		try {
			content = RungeKutta.ansToString2ndoOrdenForPDF(RungeKutta
					.rungeKutta2ndoOrden(f, g, new Double(h), new Integer(n),
							new Double(x0), new Double(y0), new Double(t0)), f,
					g, h, n, x0, y0, t0, iteraciones)
					+ "\n"
					+ Euler.ansToString2ndoOrdenForPDF(Euler.eulerSeg(f, g,
							new Double(h), new Double(x0), new Double(y0),
							new Double(t0), new Integer(n)), f, g, h, n, x0,
							y0, t0, iteraciones)
					+ "\n"
					+ EulerMejorado.ansToString2ndoOrdenForPDF(EulerMejorado
							.eulerMejoradoSeg(f, g, new Double(h), new Double(
									x0), new Double(y0), new Double(t0),
									new Integer(n)), f, g, h, n, x0, y0, t0,
							iteraciones)
					+ "\n"
					+ EulerModificado.ansToString2ndoOrdenForPDF(
							EulerModificado
									.eulerModificadoSeg(f, g, new Double(h),
											new Double(x0), new Double(y0),
											new Double(t0), new Integer(n)), f,
							g, h, n, x0, y0, t0, iteraciones);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PDFCreator.createPdfDoc(title, content);
	}
}