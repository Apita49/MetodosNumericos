package differentialEcuations;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class RungeKutta {

	public static void main(String[] args) {
		String[][] ej = rungeKutta("-2*exp(2*x) + 5*x*exp(2*x)", 0.2, 0.2,
				-1.4918, (int) (2 / 0.2));
		for (int i = 0; i < ej.length; i++) {
			System.out.println("x" + i + " = " + ej[i][0] + ", y" + i + " = "
					+ ej[i][1]);
		}

	}

	public static String rungeKutta(String f, String h, String x0, String y0,
			String eval, boolean iteraciones) {
		return "<html>"
				+ ansToString(
						rungeKutta(f, new Double(h), new Double(x0),
								new Double(y0), new Integer(eval)), iteraciones)
				+ "</html>";
	}

	public static void rungeKuttaForPDF(String f, String h, String x0,
			String y0, String eval, boolean iteraciones, String title) {
		String content = ansToStringForPDF(
				rungeKutta(f, new Double(h), new Double(x0), new Double(y0),
						new Integer(eval)), f, h, x0, y0, eval, iteraciones);

		PDFCreator.createPdfDoc(title, content);
	}

	public static String ansToString(String[][] ans, boolean iteraciones) {
		int maxLengths = maxLength(ans);
		String answer = "";

		if (iteraciones) {
			answer += "xi" + fillSpace(maxLengths - 2) + "|yi<br>";

			for (int i = 0; i < ans.length; i++) {
				answer += ans[i][0]
						+ fillSpace(maxLengths - ans[i][0].length())
						+ ans[i][1];
				if (i != ans.length - 1) {
					answer += "<br>";
				} else {
					answer += "<br>";
				}
			}
		} else {
			answer += "y(" + ans[ans.length - 1][0] + ") = "
					+ ans[ans.length - 1][1];
		}

		return answer;
	}

	public static String ansToStringForPDF(String[][] ans, String f, String h,
			String x0, String y0, String eval, boolean iteraciones) {
		int maxLengths = maxLength(ans);
		String answer = "Runge Kutta\n";

		if (iteraciones) {
			answer += "Tabla de soluciones de la ecuacion diferencial y' = "
					+ f + " con y(" + x0 + ") = " + y0 + ", h = " + h
					+ " yendo desde 0 hasta " + eval + "\n";
			answer += "xi" + fillSpaceForPDF(maxLengths - 2) + "|yi\n";

			for (int i = 0; i < ans.length; i++) {
				answer += ans[i][0]
						+ fillSpaceForPDF(maxLengths - ans[i][0].length())
						+ ans[i][1];
				answer += "\n";
			}
		} else {
			answer += "Solucion de la ecuacion diferencial y' = " + f
					+ " con y(" + x0 + ") = " + y0 + ", h = " + h
					+ " evaluado en " + ans[ans.length - 1][0] + "\ny("
					+ ans[ans.length - 1][0] + ") = " + ans[ans.length - 1][1]
					+ "\n";
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

	public static int maxLength(String[][] ans) {
		int maxLengthInX = 0;

		for (int j = 0; j < ans.length; j++) {
			if (maxLengthInX < ans[j][0].length()) {
				maxLengthInX = ans[j][0].length();
			}

		}

		return maxLengthInX + 3;
	}

	public static String[][] rungeKutta(String function, double h, double x0,
			double y0, int n) {
		String[][] ans = new String[n + 1][2];
		ans[0][0] = x0 + "";
		ans[0][1] = y0 + "";

		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		j.addStandardDiffRules();

		try {
			Node f = null;
			String ad = "";
			for (int i = 1; i <= n; i++) {
				ans[i][0] = ans[i - 1][0] + "+" + h;
				f = j.parse(ans[i][0]);
				ad = j.simplify(f).toString();
				ans[i][0] = ad.replaceAll("Constant: ", "");
				ans[i][1] = ans[i - 1][1] + "+" + "(" + h + "*"
						+ getPhy(function, ans[i - 1][0], ans[i - 1][1], h)
						+ ")";
				f = j.parse(ans[i][1]);
				ad = j.simplify(f).toString();
				ans[i][1] = ad.replaceAll("Constant: ", "");
			}

		} catch (ParseException e) {
			// nada
		}

		return ans;

	}

	public static String rungeKutta2ndoOrden(String f, String g, String h,
			String n, String x0, String y0, String t0, boolean iteraciones) {
		return "<html>"
				+ ansToString2ndoOrden(
						rungeKutta2ndoOrden(f, g, new Double(h),
								new Integer(n), new Double(x0), new Double(y0),
								new Double(t0)), iteraciones) + "</html>";
	}

	public static void rungeKutta2ndoOrdenForPDF(String f, String g, String h,
			String n, String x0, String y0, String t0, boolean iteraciones,
			String title) {
		String content = ansToString2ndoOrdenForPDF(
				rungeKutta2ndoOrden(f, g, new Double(h), new Integer(n),
						new Double(x0), new Double(y0), new Double(t0)), f, g,
				h, n, x0, y0, t0, iteraciones);
		PDFCreator.createPdfDoc(title, content);
	}

	public static void sedForPDF(String f, String g, String h, String n,
			String x0, String y0, String t0, boolean iteraciones, String title) {
		String content = ansToStringSEDForPDF(
				rungeKutta2ndoOrden(f, g, new Double(h), new Integer(n),
						new Double(x0), new Double(y0), new Double(t0)), f, g,
				h, n, x0, y0, t0, iteraciones);
		PDFCreator.createPdfDoc(title, content);
	}

	public static String ansToString2ndoOrden(String[][] ans,
			boolean iteraciones) {
		int[] maxLengths = maxLength2ndoOrden(ans);
		String answer = "";
		if (iteraciones) {
			answer += "ti" + fillSpace(maxLengths[0] - 2) + "|xi"
					+ fillSpace(maxLengths[1] - 3) + "|yi<br>";

			for (int i = 0; i < ans.length; i++) {
				answer += ans[i][0]
						+ fillSpace(maxLengths[0] - ans[i][0].length())
						+ ans[i][1]
						+ fillSpace(maxLengths[1] - ans[i][1].length())
						+ ans[i][2];
				if (i != ans.length - 1) {
					answer += "<br>";
				} else {
					answer += "<br>";
				}
			}
		} else {
			answer += "t = " + ans[ans.length - 1][0] + "; x = "
					+ ans[ans.length - 1][1] + "; y = "
					+ ans[ans.length - 1][2];
		}
		return answer;
	}

	public static String ansToString2ndoOrdenForPDF(String[][] ans, String f,
			String g, String h, String n, String x0, String y0, String t0,
			boolean iteraciones) {
		int[] maxLengths = maxLength2ndoOrden(ans);
		String answer = "Runge Kutta\n";

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

			for (int i = 0; i < ans.length; i++) {
				answer += ans[i][0]
						+ fillSpaceForPDF(maxLengths[0] - ans[i][0].length())
						+ ans[i][1]
						+ fillSpaceForPDF(maxLengths[1] - ans[i][1].length())
						+ ans[i][2] + "\n";
			}
		} else {
			answer += "Solucion de la ecuacion diferencial con f(x,y,t) = " + f
					+ "; g(x,y,t) = " + g + "; x0 = " + x0 + "; y0 = " + y0
					+ "; t0 = " + t0 + "; h = " + h + "; n = " + n + "\nt = "
					+ ans[ans.length - 1][0] + "; x = "
					+ ans[ans.length - 1][1] + "; y = "
					+ ans[ans.length - 1][2];
		}
		return answer;
	}

	public static String ansToStringSEDForPDF(String[][] ans, String f,
			String g, String h, String n, String x0, String y0, String t0,
			boolean iteraciones) {
		int[] maxLengths = maxLength2ndoOrden(ans);
		String answer = "";

		if (iteraciones) {
			answer += "Tabla de soluciones del sistema de ecuaciones diferenciales: f(x,y) = "
					+ f
					+ " g(x,y) = "
					+ g
					+ " con x0 = "
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
					+ fillSpace(maxLengths[0] - 2)
					+ "|xi"
					+ fillSpace(maxLengths[1] - 2) + "|yi\n";

			for (int i = 0; i < ans.length; i++) {
				answer += ans[i][0]
						+ fillSpace(maxLengths[0] - ans[i][0].length())
						+ ans[i][1]
						+ fillSpace(maxLengths[1] - ans[i][1].length())
						+ ans[i][2];
				if (i != ans.length - 1) {
					answer += "\n";
				}
			}
		} else {
			answer += "Solucion del sistema de ecuaciones diferenciales: f(x,y) = "
					+ f
					+ " g(x,y) = "
					+ g
					+ " con x0 = "
					+ x0
					+ "; y0 = "
					+ y0
					+ "; t0 = "
					+ t0
					+ "; h = "
					+ h
					+ "; n = "
					+ n
					+ "\nt = "
					+ ans[ans.length - 1][0]
					+ "; x = "
					+ ans[ans.length - 1][1]
					+ "; y = "
					+ ans[ans.length - 1][2];
		}
		return answer;
	}

	public static int[] maxLength2ndoOrden(String[][] ans) {
		int[] maxLengths = new int[2];
		int maxLengthInT = 0;
		int maxLengthInX = 0;

		for (int j = 0; j < ans.length; j++) {
			if (maxLengthInT < ans[j][0].length()) {
				maxLengthInT = ans[j][0].length();
			}

			if (maxLengthInX < ans[j][1].length()) {
				maxLengthInX = ans[j][1].length();
			}
		}

		maxLengths[0] = maxLengthInT + 3;
		maxLengths[1] = maxLengthInX + 3;
		return maxLengths;
	}

	public static String[][] rungeKutta2ndoOrden(String f, String g, double h,
			int n, double x0, double y0, double t0) {
		String[][] ans = new String[n + 1][3];
		// la primera columna es de t
		// la segunda columna es de x
		// la tercera columna es de y
		ans[0][0] = t0 + "";
		ans[0][1] = x0 + "";
		ans[0][2] = y0 + "";

		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		j.addStandardDiffRules();

		try {
			String ad = "";
			Node simplifier = null;

			for (int i = 1; i <= n; i++) {
				// la primera casilla es alphas
				// la segunda casilla es bethas
				String[] phy = getPhy(f, g, h, ans[i - 1][1], ans[i - 1][2],
						ans[i - 1][0]);

				ans[i][0] = ans[i - 1][0] + "+" + h;
				simplifier = j.parse(ans[i][0]);
				ad = j.simplify(simplifier).toString();
				ans[i][0] = ad.replaceAll("Constant: ", "");

				ans[i][1] = ans[i - 1][1] + "+(" + h + "*" + phy[0] + ")";
				simplifier = j.parse(ans[i][1]);
				ad = j.simplify(simplifier).toString();
				ans[i][1] = ad.replaceAll("Constant: ", "");

				ans[i][2] = ans[i - 1][2] + "+(" + h + "*" + phy[1] + ")";
				simplifier = j.parse(ans[i][2]);
				ad = j.simplify(simplifier).toString();
				ans[i][2] = ad.replaceAll("Constant: ", "");

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ans;
	}

	public static String[] getPhy(String f, String g, double h, String xi,
			String yi, String ti) {
		// la primera columna es alphas
		// la segunda columna es bethas
		String[][] k = new String[5][2];
		String[] phy = new String[2];

		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		j.addStandardDiffRules();

		try {
			String alpha = "";
			String betha = "";
			double ad1 = h / 2;
			String ad2 = "";
			Node simplifier = null;

			for (int i = 1; i < 5; i++) {
				if (i == 1) {
					alpha = f.replaceAll("exp", "e");
					alpha = alpha.replaceAll("t", "(" + ti + ")");
					alpha = alpha.replaceAll("x", "(" + xi + ")");
					alpha = alpha.replaceAll("y", "(" + yi + ")");
					alpha = alpha.replaceAll("e", "exp");
					simplifier = j.parse(alpha);
					ad2 = j.simplify(simplifier).toString();
					k[i][0] = ad2.replaceAll("Constant: ", "");

					betha = g.replaceAll("exp", "e");
					betha = betha.replaceAll("t", "(" + ti + ")");
					betha = betha.replaceAll("x", "(" + xi + ")");
					betha = betha.replaceAll("y", "(" + yi + ")");
					betha = betha.replaceAll("e", "exp");
					simplifier = j.parse(betha);
					ad2 = j.simplify(simplifier).toString();
					k[i][1] = ad2.replaceAll("Constant: ", "");
				} else if (i == 4) {
					alpha = f.replaceAll("exp", "e");
					alpha = alpha.replaceAll("t", "(" + ti + "+" + h + ")");
					alpha = alpha.replaceAll("x", "(" + xi + "+(" + h + "*"
							+ k[i - 1][0] + "))");
					alpha = alpha.replaceAll("y", "(" + yi + "+(" + h + "*"
							+ k[i - 1][1] + "))");
					alpha = alpha.replaceAll("e", "exp");
					simplifier = j.parse(alpha);
					ad2 = j.simplify(simplifier).toString();
					k[i][0] = ad2.replaceAll("Constant: ", "");

					betha = g.replaceAll("exp", "e");
					betha = betha.replaceAll("t", "(" + ti + "+" + h + ")");
					betha = betha.replaceAll("x", "(" + xi + "+(" + h + "*"
							+ k[i - 1][0] + "))");
					betha = betha.replaceAll("y", "(" + yi + "+(" + h + "*"
							+ k[i - 1][1] + "))");
					betha = betha.replaceAll("e", "exp");
					simplifier = j.parse(betha);
					ad2 = j.simplify(simplifier).toString();
					k[i][1] = ad2.replaceAll("Constant: ", "");
				} else {
					alpha = f.replaceAll("exp", "e");
					alpha = alpha.replaceAll("t", "(" + ti + "+" + ad1 + ")");
					alpha = alpha.replaceAll("x", "(" + xi + "+(" + ad1 + "*"
							+ k[i - 1][0] + "))");
					alpha = alpha.replaceAll("y", "(" + yi + "+(" + ad1 + "*"
							+ k[i - 1][1] + "))");
					alpha = alpha.replaceAll("e", "exp");
					simplifier = j.parse(alpha);
					ad2 = j.simplify(simplifier).toString();
					k[i][0] = ad2.replaceAll("Constant: ", "");

					betha = g.replaceAll("exp", "e");
					betha = betha.replaceAll("t", "(" + ti + "+" + ad1 + ")");
					betha = betha.replaceAll("x", "(" + xi + "+(" + ad1 + "*"
							+ k[i - 1][0] + "))");
					betha = betha.replaceAll("y", "(" + yi + "+(" + ad1 + "*"
							+ k[i - 1][1] + "))");
					betha = betha.replaceAll("e", "exp");
					simplifier = j.parse(betha);
					ad2 = j.simplify(simplifier).toString();
					k[i][1] = ad2.replaceAll("Constant: ", "");
				}
			}

			phy[0] = "(1/6)*" + "(" + k[1][0] + "+" + "2*" + k[2][0] + "+"
					+ "2*" + k[3][0] + "+" + k[4][0] + ")";
			simplifier = j.parse(phy[0]);
			ad2 = j.simplify(simplifier).toString();
			phy[0] = ad2.replaceAll("Constant: ", "");

			phy[1] = "(1/6)*" + "(" + k[1][1] + "+" + "2*" + k[2][1] + "+"
					+ "2*" + k[3][1] + "+" + k[4][1] + ")";
			simplifier = j.parse(phy[1]);
			ad2 = j.simplify(simplifier).toString();
			phy[1] = ad2.replaceAll("Constant: ", "");
		} catch (ParseException e) {
			// nada
		}

		return phy;
	}

	public static String getPhy(String function, String xi, String yi, double h) {
		String[] k = new String[5];
		String phy = "";

		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		j.addStandardDiffRules();

		try {
			String eval = "";
			double ad1 = h / 2;
			Node f = null;

			for (int i = 1; i < k.length; i++) {
				if (i == 1) {
					eval = function.replaceAll("exp", "e");
					eval = eval.replaceAll("x", "(" + xi + ")");
					eval = eval.replaceAll("y", "(" + yi + ")");
					eval = eval.replaceAll("e", "exp");
					f = j.parse(eval);
					String ad2 = j.simplify(f).toString();
					k[i] = ad2.replaceAll("Constant: ", "");
				} else if (i == 4) {
					eval = function.replaceAll("exp", "e");
					eval = eval.replaceAll("x", "(" + xi + "+" + h + ")");
					eval = eval.replaceAll("y", "(" + yi + "+" + "(" + h + "*"
							+ k[i - 1] + ")" + ")");
					eval = eval.replaceAll("e", "exp");
					f = j.parse(eval);
					String ad2 = j.simplify(f).toString();
					k[i] = ad2.replaceAll("Constant: ", "");
				} else {
					eval = function.replaceAll("exp", "e");
					eval = eval.replaceAll("x", "(" + xi + "+" + ad1 + ")");
					eval = eval.replaceAll("y", "(" + yi + "+" + "(" + ad1
							+ "*" + k[i - 1] + ")" + ")");
					eval = eval.replaceAll("e", "exp");
					f = j.parse(eval);
					String ad2 = j.simplify(f).toString();
					k[i] = ad2.replaceAll("Constant: ", "");
				}
			}

			phy = "(1/6)*" + "(" + k[1] + "+" + "2*" + k[2] + "+" + "2*" + k[3]
					+ "+" + k[4] + ")";
			f = j.parse(phy);
			String ad2 = j.simplify(f).toString();
			phy = ad2.replaceAll("Constant: ", "");
		} catch (ParseException e) {
			// nada
		}

		return phy;
	}

}