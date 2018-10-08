package numericIntegration;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class NewtonCotes {

	public static final String[] functions = { "(x+1)*x*(x-1)",
			"(x+1)*(x+(1/3))*x*(x-(1/3))*(x-1)",
			"(x+1)*(x+(1/2))*x*(x-(1/2))*(x-1)",
			"(x+1)*(x+0.6)*(x+0.2)*(x-0.2)*(x-0.6)*(x-1)",
			"(x+1)*(x+0.66)*(x+0.33)*x*(x-0.33)*(x-0.66)*(x-1)" };

	public static final double listX[][] = {
			{ -1, 0, 1 },
			{ -1, (double) -1 / 3, (double) 1 / 3, 1 },
			{ -1, (double) -1 / 2, 0, (double) 1 / 2, 1 },
			{ -1, -0.6, -0.2, 0.2, 0.6, 1 },
			{ -1, (double) -2 / 3, (double) -1 / 3, 0, (double) 1 / 3,
					(double) 2 / 3, 1 } };

	public static final double listA[][] = {
			{ (double) 1 / 3, (double) 4 / 3, (double) 1 / 3 },
			{ (double) 1 / 4, (double) 3 / 4, (double) 3 / 4, (double) 1 / 4 },
			{ (double) 7 / 45, (double) 32 / 45, (double) 4 / 15,
					(double) 32 / 45, (double) 7 / 45 },
			{ (double) 19 / 144, (double) 25 / 48, (double) 25 / 72,
					(double) 25 / 72, (double) 25 / 48, (double) 19 / 144 },
			{ (double) 41 / 420, (double) 18 / 35, (double) 9 / 140,
					(double) 68 / 105, (double) 9 / 140, (double) 18 / 35,
					(double) 41 / 420 } };

	public static double newtonCotesFunc(String a, String b, String c,
			String d, String n, String f) {
		try {
			return newtonCotesFunc(new Double(a), new Double(b), c, d,
					new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void newtonCotesFuncForPDF(String a, String b, String c,
			String d, String n, String f, String title) {
		try {
			String content = "Integral de la funcion "
					+ f
					+ " desde "
					+ a
					+ " hasta "
					+ b
					+ " en \"x\" y desde "
					+ c
					+ " hasta "
					+ d
					+ " en \"y\", mediante el metodo de Newton Cotes, con n = "
					+ n
					+ "\nResultado = "
					+ newtonCotesFunc(new Double(a), new Double(b), c, d,
							new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static double newtonCotesFunc(double a, double b, String c,
			String d, int n, String f) throws ParseException {
		double ans = 0;
		DJep dj = new DJep();
		dj.addStandardConstants();
		dj.addStandardFunctions();
		dj.addComplex();
		dj.setAllowUndeclared(true);
		dj.setAllowAssignment(true);
		dj.setImplicitMul(true);
		dj.addStandardDiffRules();

		Node func = dj.parse(f);
		Node funcC = dj.parse(c);
		Node funcD = dj.parse(d);

		double left = ((double) b + (double) a) / 2;
		double right = ((double) b - (double) a) / 2;
		try {
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					double mid = left + right * listX[n - 2][i];
					Node x = dj.parse("x=" + mid);
					dj.evaluate(x);

					double evalC = (double) dj.evaluate(funcC);
					double evalD = (double) dj.evaluate(funcD);

					double adi = (evalC + evalD) / 2;
					double adi2 = (evalD - evalC) / 2;

					double mid2 = adi + adi2 * listX[n - 2][j];

					Node y = dj.parse("y=" + mid2);
					dj.evaluate(y);
					double evalF = (double) dj.evaluate(func);

					double res = listA[n - 2][i] * listA[n - 2][j] * adi2
							* evalF;
					ans += res;

				}
			}
		} catch (Exception e) {

		}
		return right * ans;
	}

	public static double newtonCotes(String a, String b, String n, String f) {
		try {
			return newtonCotes(new Double(a), new Double(b), new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void newtonCotesForPDF(String a, String b, String n,
			String f, String title) {
		try {
			String content = "Integral de la funcion "
					+ f
					+ " desde "
					+ a
					+ " hasta "
					+ b
					+ " mediante el metodo de Newton Cotes, con n = "
					+ n
					+ "\nResultado = "
					+ newtonCotes(new Double(a), new Double(b), new Integer(n),
							f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static double newtonCotes2(String a, String b, String c, String d,
			String n, String f) {
		try {
			return newtonCotes2(new Double(a), new Double(b), new Double(c),
					new Double(d), new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void newtonCotes2ForPDF(String a, String b, String c,
			String d, String n, String f, String title) {
		try {
			String content = "Integral de la funcion "
					+ f
					+ " desde "
					+ a
					+ " hasta "
					+ b
					+ " en \"x\" y desde "
					+ c
					+ " hasta "
					+ d
					+ " en \"y\", mediante el metodo de Newton Cotes, con n = "
					+ n
					+ "\nResultado = "
					+ newtonCotes2(new Double(a), new Double(b), new Double(c),
							new Double(d), new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static double newtonCotes3(String a, String b, String c, String d,
			String g, String h, String n, String f) {
		try {
			return newtonCotes3(new Double(a), new Double(b), new Double(c),
					new Double(d), new Double(g), new Double(h),
					new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void newtonCotes3ForPDF(String a, String b, String c,
			String d, String g, String h, String n, String f, String title) {
		try {
			String content = "Integral de la funcion "
					+ f
					+ " desde "
					+ a
					+ " hasta "
					+ b
					+ " en \"x\", desde "
					+ c
					+ " hasta "
					+ d
					+ " en \"y\" y desde "
					+ g
					+ " hasta "
					+ h
					+ " en \"z\" mediante el metodo de Newton Cotes, con n = "
					+ n
					+ "\nResultado = "
					+ newtonCotes3(new Double(a), new Double(b), new Double(c),
							new Double(d), new Double(g), new Double(h),
							new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	// PARA UNA INTEGRAL
	public static double newtonCotes(double a, double b, int n, String f)
			throws ParseException {
		/* initialisation */
		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);

		Node func = j.parse(f);
		double ans = 0;

		for (int i = 0; i <= n; i++) {

			j.addVariable("x", ((double) b + (double) a) / 2.0
					+ (((double) b - (double) a) * listX[n - 2][i]) / 2.0);
			try {
				double adi = listA[n - 2][i] * (double) j.evaluate(func);
				ans += adi;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return (ans * (((double) b - (double) a) / 2.0));

	}

	// PARA DOS INTEGRALES
	public static double newtonCotes2(double a, double b, double c, double d,
			int n, String f) throws ParseException {
		/* initialisation */
		DJep dj = new DJep();
		dj.addStandardConstants();
		dj.addStandardFunctions();
		dj.addComplex();
		dj.setAllowUndeclared(true);
		dj.setAllowAssignment(true);
		dj.setImplicitMul(true);

		// Sets up standard rules for differentiating sin(x) etc.
		dj.addStandardDiffRules();
		Node func = dj.parse(f);

		double ans = 0;
		for (int i = 0; i <= n; i++) {
			double adi = 0;
			for (int j = 0; j <= n; j++) {
				dj.addVariable("x", (((double) b + (double) a) / 2.0)
						+ ((((double) b - (double) a) * listX[n - 2][i]) / 2.0));

				dj.addVariable("y", (((double) d + (double) c) / 2.0)
						+ ((((double) d - (double) c) * listX[n - 2][j]) / 2.0));
				try {
					adi += (listA[n - 2][i] * listA[n - 2][j])
							* (double) dj.evaluate(func);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			ans += adi;
		}
		return ((((((double) b - (double) a) * ((double) d - (double) c)) / 4.0) * ans));

	}

	// PARA 3 INTEGRALES
	public static double newtonCotes3(double a, double b, double c, double d,
			double g, double h, int n, String f) throws ParseException {
		/* initialisation */
		DJep dj = new DJep();
		dj.addStandardConstants();
		dj.addStandardFunctions();
		dj.addComplex();
		dj.setAllowUndeclared(true);
		dj.setAllowAssignment(true);
		dj.setImplicitMul(true);

		// Sets up standard rules for differentiating sin(x) etc.
		dj.addStandardDiffRules();
		Node func = dj.parse(f);

		double ans = 0;
		for (int i = 0; i <= n; i++) {
			double adi = 0;
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					dj.addVariable(
							"x",
							(((double) b + (double) a) / 2.0)
									+ ((((double) b - (double) a) * listX[n - 2][i]) / 2.0));

					dj.addVariable(
							"y",
							(((double) d + (double) c) / 2.0)
									+ ((((double) d - (double) c) * listX[n - 2][j]) / 2.0));
					dj.addVariable(
							"z",
							(((double) h + (double) g) / 2.0)
									+ ((((double) h - (double) g) * listX[n - 2][k]) / 2.0));
					try {
						adi += (listA[n - 2][i] * listA[n - 2][j] * listA[n - 2][k])
								* (double) dj.evaluate(func);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			ans += adi;
		}
		return (((((double) b - (double) a) * ((double) d - (double) c) * ((double) h - (double) g)) / 8.0) * ans);

	}

	// public static void main(String[] args) {
	// double test = 0;
	// try {
	// test = NewtonCotes.NewtonContesFunc(0, 1.5, "0", "2*x", 4, "cos(x^2)");
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// System.out.println(test);
	// }
}