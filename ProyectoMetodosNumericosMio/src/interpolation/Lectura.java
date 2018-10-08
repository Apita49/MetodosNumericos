package interpolation;

import java.util.LinkedList;
import java.util.List;

public class Lectura {

	public static String leerTodo(String s) {
		List<String> str = new LinkedList<>();
		List<Polynomial> pol = new LinkedList<>();
		int i = 0;
		for (; i < s.length(); i++) {
			String adi = "";

			while (i < s.length() && s.charAt(i) != '+') {
				adi += s.charAt(i);
				i++;
			}
			str.add(adi);
		}
		while (!str.isEmpty()) {
			pol.add(leer(str.remove(0)));
		}

		Polynomial resultado = pol.remove(0);
		while (!pol.isEmpty()) {
			resultado = resultado.plus(pol.remove(0));
		}
		String res = resultado.toString();
		return res;

	}

	public static Polynomial leer(String s) {

		// SEPARAR EN TERMINOS
		char[] partes = s.toCharArray();
		List<String> terminos = new LinkedList<>();

		String adi = "";
		for (int i = 0; i < partes.length; i++) {
			if (partes[i] != '*') {
				adi += partes[i];
			} else {

				terminos.add(adi);
				adi = "";
			}
			if (i == partes.length - 1) {
				terminos.add(adi);
			}
		}
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		List<Polynomial> pol = new LinkedList<>();
		// double[][] valores = new double[2][terminos.size()];

		while (terminos.size() > 0) {
			String actual = terminos.remove(0);
			String actF = "";
			for (int i = 0; i < actual.length(); i++) {
				if (actual.charAt(i) != '(' && actual.charAt(i) != ')'
						&& actual.charAt(i) != ' ') {
					actF += actual.charAt(i);
				}
			}
			double coefx = 1;
			int exp = 1;
			int i = 0;
			if (actF.charAt(0) != 'x') {
				String adic = "";
				while (actF.charAt(i) != 'x') {
					adic += actF.charAt(i);
					i++;
					if (i == actF.length()) {
						return new Polynomial(new Double(adic), 0);
					}
				}
				coefx = new Double(adic);

				i++;
			} else {
				i++;
			}
			// char signo = ' ';
			if (actF.charAt(i) != '+' && actF.charAt(i) != '-') {
				i++;
				String adic = "";
				while (actF.charAt(i) != '+' && actF.charAt(i) != '-') {
					adic += actF.charAt(i);
					i++;
				}
				exp = new Integer(adic);
			}

			double indep = 0;
			String adic = "";

			for (; i < actF.length(); i++) {
				if (i <= actF.length() - 2 && actF.charAt(i) == '-'
						&& actF.charAt(i + 1) == '-') {
					adic += '+';
					i++;
				} else {
					adic += actF.charAt(i);
				}
			}
			indep = new Double(adic);
			Polynomial p = new Polynomial(coefx, exp);
			Polynomial q = new Polynomial(indep, 0);
			pol.add(p.plus(q));

		}

		// //////////////////////////////////////////////////////////////////////////////////////////////////////////

		Polynomial resultado = pol.remove(0);
		while (!pol.isEmpty()) {
			resultado = resultado.times(pol.remove(0));
		}
		return resultado;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////

	public static class Polynomial {
		private double[] coef; // coefficients
		private int deg; // degree of polynomial (0 for the zero polynomial)

		// a * x^b
		public Polynomial(double a, int b) {
			coef = new double[b + 1];
			coef[b] = a;
			deg = degree();
		}

		// return the degree of this polynomial (0 for the zero polynomial)
		public int degree() {
			int d = 0;
			for (int i = 0; i < coef.length; i++)
				if (coef[i] != 0)
					d = i;
			return d;
		}

		public Polynomial plus(Polynomial b) {
			Polynomial a = this;
			Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
			for (int i = 0; i <= a.deg; i++)
				c.coef[i] += a.coef[i];
			for (int i = 0; i <= b.deg; i++)
				c.coef[i] += b.coef[i];
			c.deg = c.degree();
			return c;
		}

		// return (a - b)
		public Polynomial minus(Polynomial b) {
			Polynomial a = this;
			Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
			for (int i = 0; i <= a.deg; i++)
				c.coef[i] += a.coef[i];
			for (int i = 0; i <= b.deg; i++)
				c.coef[i] -= b.coef[i];
			c.deg = c.degree();
			return c;
		}

		public Polynomial times(Polynomial b) {
			Polynomial a = this;
			Polynomial c = new Polynomial(0, a.deg + b.deg);
			for (int i = 0; i <= a.deg; i++)
				for (int j = 0; j <= b.deg; j++)
					c.coef[i + j] += (a.coef[i] * b.coef[j]);
			c.deg = c.degree();
			return c;
		}

		public String toString() {
			if (deg == 0)
				return "" + coef[0];
			if (deg == 1)
				return coef[1] + "x + " + coef[0];
			String s = coef[deg] + "x^" + deg;
			for (int i = deg - 1; i >= 0; i--) {
				if (coef[i] == 0)
					continue;
				else if (coef[i] > 0)
					s = s + " + " + (coef[i]);
				else if (coef[i] < 0)
					s = s + " - " + (-coef[i]);
				if (i == 1)
					s = s + "x";
				else if (i > 1)
					s = s + "x^" + i;
			}
			return s;
		}

	}

	public static void main(String[] args) {
		String s = leerTodo("1.0 + ((0.0x - -0.0)) + ((1.0x - -1.0)*(1.0x - 0.0)) + ((0.0x - -0.0)*(0.0x - 0.0)*(0.0x - 0.0)) + ((0.0x - -0.0)*(0.0x - 0.0)*(0.0x - 0.0)*(0.0x - 0.0))");
		System.out.println(s);
	}
}