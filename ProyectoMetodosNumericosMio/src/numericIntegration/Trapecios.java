package numericIntegration;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import pdfCreator.PDFCreator;

public class Trapecios {
	private static DJep _j;

	private static String _y;
	private static Node _function;
	private static Node _secondDiff;

	private static double _a;
	private static double _b;
	private static int _n;
	private static double _h;

	public static double[] calculate(String y, String a, String b, String n,
			boolean integral) {
		return calculate(y, new Double(a), new Double(b), new Integer(n),
				integral);
	}

	public static void calculateForPDF(String y, String a, String b, String n,
			boolean integral, String title) {
		String content;
		double[] resultado = calculate(y, new Double(a), new Double(b),
				new Integer(n), integral);
		if (integral) {
			content = "Integral";
		} else {
			content = "Area";
		}
		content += " de la funcion " + y + " desde " + a + " hasta " + b
				+ " mediante el metodo de Trapecios, con " + n
				+ " iteraciones:\nResultado = " + resultado[0] + "\nError = "
				+ resultado[1];

		PDFCreator.createPdfDoc(title, content);
	}

	public static double[] calculate(String y, double a, double b, int n,
			boolean integral) {
		double[] answers = new double[2];

		setValues(y, a, b, n);

		if (integral) {
			answers[0] = calcIntegral();
		} else {
			answers[0] = calcArea();
		}
		answers[1] = calcError();

		return answers;

	}

	private static void setValues(String y, double a, double b, int n) {
		_j = new DJep();
		_j.addStandardConstants();
		_j.addStandardFunctions();
		_j.addComplex();
		_j.setAllowUndeclared(true);
		_j.setAllowAssignment(true);
		_j.setImplicitMul(true);
		_j.addStandardDiffRules();

		_y = y;
		_a = a;
		_b = b;
		_n = n;
		_h = (_b - _a) / _n;

		try {
			Node uglyFunction = _j.parse(_y);
			_function = _j.simplify(uglyFunction);
			_secondDiff = _j.differentiate(_j.differentiate(_function, "x"),
					"x");
		} catch (ParseException e) {
			// TODO Generate a parse exception message
		}

	}

	private static double calcIntegral() {
		double fInA = 0;
		double fInB = 0;
		double sum = 0;
		try {
			_j.addVariable("x", _a);
			fInA = (double) _j.evaluate(_function);
			_j.addVariable("x", _b);
			fInB = (double) _j.evaluate(_function);

			for (int k = 1; k <= _n - 1; k++) {
				_j.addVariable("x", _a + (k * _h));
				sum += (double) _j.evaluate(_function);
			}
		} catch (Exception e) {
			// TODO Generate the evaluation exception
		}

		double answer = _h * (((fInA + fInB) / 2) + sum);
		return answer;
	}

	private static double calcArea() {
		double fInA = 0;
		double fInB = 0;
		double sum = 0;
		try {
			_j.addVariable("x", _a);
			fInA = Math.abs((double) _j.evaluate(_function));
			_j.addVariable("x", _b);
			fInB = Math.abs((double) _j.evaluate(_function));

			for (double k = 1; k < _n; k++) {
				_j.addVariable("x", _a + (k * ((_b - _a) / _n)));
				sum += Math.abs((double) _j.evaluate(_function));
			}
		} catch (Exception e) {
			// TODO Generate the evaluation exception
		}

		double answer = _h * (((fInA + fInB) / 2) + sum);
		return answer;
	}

	private static double calcError() {
		double max = 0;

		try {
			double increment = 0;
			if ((_b - _a) < 1000) {
				increment = 0.01;
			} else {
				increment = 1;
			}

			double secondDiffValue;
			for (double x = _a; x <= _b; x += increment) {
				_j.addVariable("x", x);
				secondDiffValue = Math.abs((double) _j.evaluate(_secondDiff));

				if (max < secondDiffValue) {
					max = secondDiffValue;
				}
			}

		} catch (Exception e) {
			// TODO Generate the evaluation exception
		}

		double error = ((Math.pow(_h, 2) / 12) * (_b - _a) * max);
		return error;
	}

	// public static void main(String[] args) {
	// double[] answers = Trapecios.calculate("ln(x)", 0.1, 0.2, 10, true);
	// System.out.println("i: " + answers[0]);
	// System.out.println("e: " + answers[1]);
	// }

}