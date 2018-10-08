package differentialEcuations;

import java.util.LinkedList;
import java.util.List;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class Euler {

	public static String euler(String f, String h, String x0, String y0,
			String eval, boolean iteraciones) {
		try {
			return "<html>"
					+ ansToString(
							euler(f, new Double(h), new Double(x0), new Double(
									y0), new Integer(eval)), iteraciones)
					+ "</html>";
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void eulerForPDF(String f, String h, String x0, String y0,
			String eval, boolean iteraciones, String title) {
		String content = "";
		try {
			content = ansToStringForPDF(
					euler(f, new Double(h), new Double(x0), new Double(y0),
							new Integer(eval)), f, h, x0, y0, eval, iteraciones);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PDFCreator.createPdfDoc(title, content);
	}

	public static String ansToString(List<List<Double>> ans, boolean iteraciones) {
		int maxLengths = maxLength(ans);
		String answer = "";

		if (iteraciones) {
			answer += "xi" + fillSpace(maxLengths - 2) + "|yi<br>";

			for (int i = 0; i < ans.get(0).size(); i++) {
				answer += ans.get(0).get(i)
						+ fillSpace(maxLengths
								- ans.get(0).get(i).toString().length())
						+ ans.get(1).get(i);
				if (i != ans.get(0).get(ans.get(0).size() - 1)) {
					answer += "<br>";
				} else {
					answer += "<br>";
				}
			}
		} else {
			answer += "y(" + ans.get(0).get(ans.get(0).size() - 1) + ") = "
					+ ans.get(1).get(ans.get(1).size() - 1);
		}

		return answer;
	}

	public static String ansToStringForPDF(List<List<Double>> ans, String f,
			String h, String x0, String y0, String eval, boolean iteraciones) {
		int maxLengths = maxLength(ans);
		String answer = "Euler\n";

		if (iteraciones) {
			answer += "Tabla de soluciones de la ecuacion diferencial y' = "
					+ f + " con y(" + x0 + ") = " + y0 + ", h = " + h
					+ " yendo desde 0 hasta " + eval + "\n";
			answer += "xi" + fillSpaceForPDF(maxLengths - 2) + "|yi\n";

			for (int i = 0; i < ans.get(0).size(); i++) {
				answer += ans.get(0).get(i)
						+ fillSpaceForPDF(maxLengths
								- ans.get(0).get(i).toString().length())
						+ ans.get(1).get(i) + "\n";
			}
		} else {
			answer += "Solucion de la ecuacion diferencial y' = " + f
					+ " con y(" + x0 + ") = " + y0 + ", h = " + h
					+ " evaluado en " + ans.get(0).get(ans.get(0).size() - 1)
					+ "\ny(" + ans.get(0).get(ans.get(0).size() - 1) + ") = "
					+ ans.get(1).get(ans.get(1).size() - 1) + "\n";
		}

		return answer;
	}

	public static String fillSpace(int spaces) {
		String ans = "";

		for (int i = 0; i < spaces; i++) {
			ans += "&nbsp;";
		}

		return ans;
	}

	public static String fillSpaceForPDF(int spaces) {
		String ans = "";

		for (int i = 0; i < spaces; i++) {
			ans += " ";
		}

		return ans;
	}

	public static int maxLength(List<List<Double>> ans) {
		int maxLengthInX = 0;

		for (int j = 0; j < ans.get(0).size(); j++) {
			if (maxLengthInX < ans.get(0).get(j).toString().length()) {
				maxLengthInX = ans.get(0).get(j).toString().length();
			}

		}

		return maxLengthInX + 3;
	}

	public static List<List<Double>> euler(String f, double h, double x0,
			double y0, double eval) throws ParseException {
		List<List<Double>> ans = new LinkedList<>();
		ans.add(new LinkedList<Double>());
		ans.add(new LinkedList<Double>());
		ans.get(0).add(x0);
		ans.get(1).add(y0);
		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		Node func = j.parse(f);

		j.addVariable("x", x0);
		j.addVariable("y", y0);
		double yActual = 0;
		try {
			yActual = y0 + h * (double) (j.evaluate(func));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ans.get(0).add(x0 + h);
		ans.get(1).add(yActual);

		for (double i = x0 + h; i < eval * h; i += h) {
			j.addVariable("x", i);
			j.addVariable("y", yActual);
			try {
				yActual = yActual + h * (double) (j.evaluate(func));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ans.get(0).add(i + h);
			ans.get(1).add(yActual);
		}
		return ans;
	}

	public static String eulerSeg(String f, String g, String h, String n,
			String x0, String y0, String t0, boolean iteraciones) {
		try {
			return "<html>"
					+ ansToString2ndoOrden(
							eulerSeg(f, g, new Double(h), new Double(x0),
									new Double(y0), new Double(t0),
									new Integer(n)), iteraciones) + "</html>";
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void eulerSegForPDF(String f, String g, String h, String n,
			String x0, String y0, String t0, boolean iteraciones, String title) {
		String content = "";
		try {
			content = ansToString2ndoOrdenForPDF(
					eulerSeg(f, g, new Double(h), new Double(x0),
							new Double(y0), new Double(t0), new Integer(n)), f,
					g, h, n, x0, y0, t0, iteraciones);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PDFCreator.createPdfDoc(title, content);
	}

	public static String ansToString2ndoOrden(double[][] ans,
			boolean iteraciones) {
		int[] maxLengths = maxLengthSegOrden(ans);
		String answer = "";
		if (iteraciones) {
			answer += "ti" + fillSpace(maxLengths[0] - 2) + "|xi"
					+ fillSpace(maxLengths[1] - 3) + "|yi<br>";

			for (int i = 0; i < ans[0].length; i++) {
				answer += ans[0][i]
						+ fillSpace(maxLengths[0] - (ans[0][i] + "").length())
						+ ans[1][i]
						+ fillSpace(maxLengths[1] - (ans[1][i] + "").length())
						+ ans[2][i];
				if (i != ans[0].length - 1) {
					answer += "<br>";
				} else {
					answer += "<br>";
				}
			}
		} else {
			answer += "t = " + ans[0][ans[0].length - 1] + "; x = "
					+ ans[1][ans[1].length - 1] + "; y = "
					+ ans[2][ans[2].length - 1];
		}
		return answer;
	}

	public static String ansToString2ndoOrdenForPDF(double[][] ans, String f,
			String g, String h, String n, String x0, String y0, String t0,
			boolean iteraciones) {
		int[] maxLengths = maxLengthSegOrden(ans);
		String answer = "Euler\n";

		if (iteraciones) {
			answer += "Tabla de soluciones de la ecuacion diferencial con f(x,y,t) = "
					+ f
					+ "; g(x,y,t) = "
					+ g
					+ "; x0 = "
					+ x0
					+ "; y0 = "
					+ y0
					+ "; t0 = "
					+ t0
					+ "; h = "
					+ h
					+ "; n = "
					+ n
					+ "\nti"
					+ fillSpaceForPDF(maxLengths[0] - 2)
					+ "|xi"
					+ fillSpaceForPDF(maxLengths[1] - 3) + "|yi\n";

			for (int i = 0; i < ans[0].length; i++) {
				answer += ans[0][i]
						+ fillSpaceForPDF(maxLengths[0]
								- (ans[0][i] + "").toString().length())
						+ ans[1][i]
						+ fillSpaceForPDF(maxLengths[1]
								- (ans[1][i] + "").length()) + ans[2][i] + "\n";
			}
		} else {
			answer += "Solucion de la ecuacion diferencial con f(x,y,t) = " + f
					+ "; g(x,y,t) = " + g + "; x0 = " + x0 + "; y0 = " + y0
					+ "; t0 = " + t0 + "; h = " + h + "; n = " + n + "\nt = "
					+ ans[0][ans[0].length - 1] + "; x = "
					+ ans[1][ans[1].length - 1] + "; y = "
					+ ans[2][ans[2].length - 1];
		}
		return answer;
	}

	public static int[] maxLengthSegOrden(double[][] ans) {
		int[] maxLengths = new int[2];
		int maxLengthInT = 0;
		int maxLengthInX = 0;

		for (int j = 0; j < ans[0].length; j++) {
			if (maxLengthInT < (ans[0][j] + "").length()) {
				maxLengthInT = (ans[0][j] + "").length();
			}

			if (maxLengthInX < (ans[1][j] + "").length()) {
				maxLengthInX = (ans[1][j] + "").length();
			}
		}

		maxLengths[0] = maxLengthInT + 3;
		maxLengths[1] = maxLengthInX + 3;
		return maxLengths;
	}

	public static double[][] eulerSeg(String f, String g, double h, double z0,
			double y0, double t0, double eval) throws ParseException {
		double[][] ans = new double[3][(int) (eval + 1)];
		ans[0][0] = t0;
		ans[1][0] = z0;
		ans[2][0] = y0;
		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		Node F = j.parse(f);
		Node G = j.parse(g);
		double yActual = y0;
		double zActual = z0;
		int pos = 1;
		for (double i = t0; pos < ans[0].length; i += h) {
			j.addVariable("x", zActual);
			j.addVariable("y", yActual);
			j.addVariable("t", i);

			try {
				yActual = yActual + h * ((double) j.evaluate(G));
				zActual = zActual + h * ((double) j.evaluate(F));
				ans[1][pos] = zActual;
				ans[2][pos] = yActual;
				ans[0][pos] = i + h;
				pos++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ans;
	}

	// public static void main(String[] args) {
	// int[][] hoklahoma = new int[1][1];
	// hoklahoma[0][0] = 23;
	// System.out.println(hoklahoma[0][0]);
	// }

	// public static void main(String[] args) {
	//
	// List<Double> a = new LinkedList<>();
	// try {
	// //a = euler(f, 0.2, 0, 2, 20);
	// double[][] b = eulerSeg("cos(t)-4y", "x", 0.1, 0, 0, 0, 1);
	// for (int i = 0; i < b[0].length; i++) {
	// //System.out.println(a.get(i));
	// System.out.println(b[0][i] + "** "+ b[1][i] +" **"+ b[2][i]);
	// }
	//
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // try {
	// // List<List<Double>> a = euler("x-y", 0.2, 0, 2, 1);
	// //
	// // System.out.println();
	// // } catch (ParseException e) {
	// // e.printStackTrace();
	// // }
	//
	// }

}