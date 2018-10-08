package numericIntegration;

import java.util.LinkedList;
import java.util.List;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class Simpson {
	/*
	 * Metodo de Simpson Toma un string funcion( sin(x) ) double a y b son los
	 * limites int n es el numero de aproximaciones(tiene q ser par, sino
	 * devuelve null String ioA es el parametro para determinar si hay q
	 * calcular la integral o el area de la funcion: "area" para area,
	 * "integral" para integral El metodo devuelve un array[] en el q la
	 * posicion 0 = la respuesta de la integral, y la posicion 1 devuelve el
	 * error
	 */
	public static double[] calculate(String function, String a, String b,
			String n, boolean integral) {
		String ioA;
		if (integral == true) {
			ioA = "integral";
		} else {
			ioA = "area";
		}
		return metodoSimpson(function, new Double(a), new Double(b),
				new Integer(n), ioA);
	}

	public static void calculateForPDF(String function, String a, String b,
			String n, boolean integral, String title) {
		String content;
		String ioA;

		if (integral) {
			ioA = "integral";
			content = "Integral";
		} else {
			ioA = "area";
			content = "Area";
		}
		double[] resultado = metodoSimpson(function, new Double(a), new Double(
				b), new Integer(n), ioA);

		content += " de la funcion " + function + " desde " + a + " hasta " + b
				+ " mediante el metodo de Simpson, con " + n
				+ " iteraciones:\nResultado = " + resultado[0] + "\nError = "
				+ resultado[1];

		PDFCreator.createPdfDoc(title, content);
	}

	public static double[] metodoSimpson(String function, double a, double b,
			int n, String ioA) {
		if (n % 2 != 0) {
			System.out.println("n tiene que ser un numero par");
			return null;
		}

		DJep j = new DJep();
		j.addStandardConstants();
		j.addStandardFunctions();
		j.addComplex();
		j.setAllowUndeclared(true);
		j.setAllowAssignment(true);
		j.setImplicitMul(true);
		j.addStandardDiffRules();

		double c = 0;
		if (Math.abs(b - a) <= 0.01) {
			c = 0.00001;
		} else if (Math.abs(b - a) <= 1000) {
			c = 0.01;
		} else if (Math.abs(b - a) <= 10000) {
			c = 0.1;
		} else {
			c = 1;
		}

		double h = ((b - a) / n);
		double m = 0;
		List<Double> e = new LinkedList<>();
		List<Double> i = new LinkedList<>();
		List<Double> p = new LinkedList<>();

		try {
			Node f = j.parse(function);
			f = j.simplify(f);
			for (int k = 0; k <= n; k++) {
				j.addVariable("x", (a + (k * h)));
				Double ad = (Double) j.evaluate(f);
				if (k == 0 || k == n) {
					e.add(ad);
				} else if (k % 2 == 0) {
					p.add(ad);
				} else {
					i.add(ad);
				}
			}

			Node diff = j.differentiate(f, "x");
			for (int k = 0; k < 3; k++) {
				diff = j.differentiate(diff, "x");
			}
			Node d4 = j.simplify(diff);
			double max = 0;
			for (double k = a; k <= b; k += c) {
				j.addVariable("x", (a + k));
				Double ad = (Double) j.evaluate(d4);
				if (max < Math.abs(ad)) {
					max = Math.abs(ad);
				}
			}
			m = max;

		} catch (ParseException e1) {
			System.out.println("Error with parsing");
		} catch (Exception e2) {
			System.out.println("Error with casting");
		}

		double[] ans = new double[2];
		Double sumE = sum(e, ioA);
		Double sumI = sum(i, ioA);
		Double sumP = sum(p, ioA);
		Double ansInt = (h / 3.0) * (sumE + (4.0 * sumI) + (2.0 * sumP));
		Double epsilon = (1.0 / 180.0) * Math.pow(h, 4) * m * (b - a);
		ans[0] = ansInt;
		ans[1] = epsilon;

		return ans;

	}

	/*
	 * Metodo de Simpson recive un array de double en el que te dan los
	 * y0,y1,y2,...,yn en sus respectivos indices, es decir, y0 debe estar en la
	 * posicion 0 del array double a y b son los limites de la integral double h
	 * es la distancia entre los puntos que se toman String ioA es el parametro
	 * para determinar si hay q calcular la integral o el area de la funcion:
	 * "area" para area, "integral" para integral devuelve un double que es la
	 * respuesta de la integral
	 */
	public double metodoSimpson(double[] nums, double a, double b, double h,
			String ioA) {
		int n = nums.length;
		if (n % 2 != 0) {
			System.out.println("n tiene que ser un numero par");
			return 0.0;
		}

		List<Double> e = new LinkedList<>();
		List<Double> i = new LinkedList<>();
		List<Double> p = new LinkedList<>();

		for (int k = 0; k < n; k++) {
			if (k == 0 || k == n - 1) {
				e.add(nums[k]);
			} else if (k % 2 == 0) {
				p.add(nums[k]);
			} else {
				i.add(nums[k]);
			}
		}

		Double sumE = sum(e, ioA);
		Double sumI = sum(i, ioA);
		Double sumP = sum(p, ioA);
		Double ans = (h / 3.0) * (sumE + (4.0 * sumI) + (2.0 * sumP));

		return ans;
	}

	private static Double sum(List<Double> l, String ioA) {
		Double sum = 0.0;
		if (ioA == "area") {
			for (Double i : l) {
				sum += Math.abs(i);
			}
		} else {
			for (Double i : l) {
				sum += i;
			}
		}
		return sum;
	}

	// public static void main(String[] args) {
	// Simpson in = new Simpson();
	// // double[] nums = { 1, 7, 4, 3, 5, 2 };
	// // double y = in.metodoSimpson(nums, 0, 0.5, 0.1);
	// // System.out.println(y);
	// double[] x = in.metodoSimpson("cos(x)", 0, Math.PI, 100, "area");
	// System.out.println("I = " + x[0]);
	// System.out.println("E <= " + x[1]);
	// }

}