package numericIntegration;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class CuadraturaDeGauss {

	public static final double listX[][] = {
			{ 0.577350269189626, -0.577350269189626 },
			{ 0.774596669241483, -0.774596669241483, 0 },
			{ 0.861136311594053, -0.861136311594053, 0.339981043584856,
					-0.339981043584856 },
			{ 0.906179845938664, -0.906179845938664, 0.538469310105683,
					-0.538469310105683, 0 },
			{ 0.932469514203152, -0.932469514203152, 0.661209386466265,
					-0.661209386466265, 0.238619186083197, -0.238619186083197 } };

	public static final double listA[][] = {
			{ 1, 1 },
			{ 0.555555555555556, 0.555555555555556, 0.888888888888889 },
			{ 0.347854845137454, 0.347854845137454, 0.652145154862546,
					0.652145154862546 },
			{ 0.236926885056189, 0.236926885056189, 0.478628670499366,
					0.478628670499366, 0.568888888888889 },
			{ 0.171324492379170, 0.171324492379170, 0.360761573048139,
					0.360761573048139, 0.467913934572691, 0.467913934572691 } };

	// PARA UNA INTEGRAL
	public static double gauss1(String a, String b, String n, String f) {
		try {
			return gauss1(new Double(a), new Double(b), new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void gauss1ForPDF(String a, String b, String n, String f,
			String title) {
		try {
			String content = "Integral de la funcion " + f + " desde " + a
					+ " hasta " + b
					+ " mediante el metodo de la cuadratura de Gauss, con n = "
					+ n + "\nResultado = "
					+ gauss1(new Double(a), new Double(b), new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static double gauss1(double a, double b, int n, String f)
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

		for (int i = 0; i < n; i++) {

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

	public static double gauss2(String a, String b, String c, String d,
			String n, String f) {
		try {
			return gauss2(new Double(a), new Double(b), new Double(c),
					new Double(d), new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void gauss2ForPDF(String a, String b, String c, String d,
			String n, String f, String title) {
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
					+ " en \"y\", mediante el metodo de la cuadratura de Gauss, con n = "
					+ n
					+ "\nResultado = "
					+ gauss2(new Double(a), new Double(b), new Double(c),
							new Double(d), new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	// PARA DOS INTEGRALES
	public static double gauss2(double a, double b, double c, double d, int n,
			String f) throws ParseException {
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
		for (int i = 0; i < n; i++) {
			double adi = 0;
			for (int j = 0; j < n; j++) {
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

	public static double cuadraturaFunc(String a, String b, String c, String d,
			String n, String f) {
		try {
			return cuadraturaFunc(new Double(a), new Double(b), c, d,
					new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void cuadraturaFuncForPDF(String a, String b, String c,
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
					+ " en \"y\", mediante el metodo de la cuadratura de Gauss, con n = "
					+ n
					+ "\nResultado = "
					+ cuadraturaFunc(new Double(a), new Double(b), c, d,
							new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static double cuadraturaFunc(double a, double b, String c, String d,
			int n, String f) throws ParseException {
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
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
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

	public static double gauss3(String a, String b, String c, String d,
			String g, String h, String n, String f) {
		try {
			return gauss3(new Double(a), new Double(b), new Double(c),
					new Double(d), new Double(g), new Double(h),
					new Integer(n), f);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void gauss3ForPDF(String a, String b, String c, String d,
			String g, String h, String n, String f, String title) {
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
					+ " en \"z\" mediante el metodo de la cuadratura de Gauss, con n = "
					+ n
					+ "\nResultado = "
					+ gauss3(new Double(a), new Double(b), new Double(c),
							new Double(d), new Double(g), new Double(h),
							new Integer(n), f);
			PDFCreator.createPdfDoc(title, content);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	// PARA 3 INTEGRALES
	public static double gauss3(double a, double b, double c, double d,
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
		for (int i = 0; i < n; i++) {
			double adi = 0;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
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

	public static void main(String[] args) {
		double test = 0;
		try {
			test = gauss1(0, 1, 4, "x");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(test);
	}

}