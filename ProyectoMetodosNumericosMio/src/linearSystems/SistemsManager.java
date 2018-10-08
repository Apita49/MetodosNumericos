package linearSystems;

import pdfCreator.PDFCreator;

public class SistemsManager {
	private static String metodo;
	private static int ecuaciones;
	private static double[][] ecuacionesMat;

	public double[] calcularSistema(int metodo) {
		if (metodo == 1) {
			return Gauss.gauss(ecuacionesMat);
		}

		return null;
	}

	// [a,b,c]
	public static double[] sacarDatosIniciales(char[] datos) {
		double[] resp = new double[ecuaciones];
		String numero = "";
		for (int i = 1, j = 0; i < datos.length; i++) {
			if ((datos[i] >= '0' && datos[i] <= '9') || datos[i] == '+'
					|| datos[i] == '-' || datos[i] == '.') {
				numero += datos[i];
			} else if (datos[i] == ',' || datos[i] == ']') {
				resp[j] = new Double(numero);
				j++;
				numero = "";
			} else {
				return null;
			}
		}
		return resp;
	}

	public static void stringEnEcs(String ecs) {
		int i = 0;
		String ecAct = "";
		for (int j = 0; j < ecs.length(); j++) {
			if (ecs.charAt(j) != '\n') {
				ecAct += ecs.charAt(j);
			} else {
				introducirEc(ecAct.toCharArray(), i);
				ecAct = "";
				i++;
			}
		}
		ecAct = "";
	}

	private static void introducirEc(char[] ecuacion, int ecuacionActual) {
		String number = "";
		int posicion = 0;
		for (int i = 0; i < ecuacion.length; i++) {
			if ((ecuacion[i] >= '0' && ecuacion[i] <= '9')
					|| ecuacion[i] == '+' || ecuacion[i] == '-'
					|| ecuacion[i] == '.') {
				number += ecuacion[i];
			} else if (ecuacion[i] == 'x') {
				i++;
				ecuacionesMat[ecuacionActual][posicion] = new Double(number);
				posicion++;
				number = "";
			} else if (ecuacion[i] == ' ' || ecuacion[i] == '=') {
				continue;
			} else {
				return;
			}
		}
		ecuacionesMat[ecuacionActual][posicion] = new Double(number);
	}

	public static String ecsEnString() {
		String resp = "";
		for (int i = 0; i < ecuaciones; i++) {
			resp += ecuacionesMat[i][0] + "x" + 1;
			for (int j = 1; j < ecuacionesMat[0].length - 1; j++) {
				if (ecuacionesMat[i][j] >= 0) {
					resp += " + " + ecuacionesMat[i][j] + "x" + (j + 1);
				} else {
					resp += " - " + ecuacionesMat[i][j] + "x" + (j + 1);
				}
			}
			if (ecuacionesMat[i][ecuacionesMat.length - 1] >= 0) {
				resp += " = " + ecuacionesMat[i][ecuacionesMat.length];
			} else {
				resp += " = - " + ecuacionesMat[i][ecuacionesMat.length];
			}
			resp += '\n';
		}

		return resp;
	}

	public static String decodificarAnswer(Answer[] ans) {
		if (ans == null) {
			return "La matriz no converge y el error diverge";
		}
		String resp = "Mediante el metodo de ";
		if (metodo.equals("J")) {
			resp += "Jacobi:<br>";
		} else if (metodo.equals("GS")) {
			resp += "Gauss Seidel:<br>";
		}
		int[] maxLength = maxLength(ans);
		resp += "k" + fillSpace(maxLength[0] - 1) + "|"
				+ doArrayTitle(ans[0].getSolution().length)
				+ fillSpace(ans[0].getSolution().length * (maxLength[1] - 1))
				+ "|errork<br>";
		for (int i = 0; i < ans.length; i++) {
			resp += i + fillSpace(maxLength[0] - (i + "").length()) + "|[";
			for (int j = 0; j < ans[i].getSolution().length; j++) {
				resp += ans[i].getSolution()[j]
						+ fillSpace(maxLength[1]
								- ("" + ans[i].getSolution()[j]).length());
				if (j == ans[i].getSolution().length - 1) {
					resp += "]";
				} else {
					resp += ",";
				}
			}
			resp += "|";
			if (i == ans.length - 1) {
				resp += ans[i].getError() + "<br>";
			} else {
				resp += ans[i].getError() + "<br>";
			}
		}

		return resp;
	}

	public static String decodificarAnswerForPDF(Answer[] ans) {
		if (ans == null) {
			return "La matriz no converge y el error diverge";
		}
		int[] maxLength = maxLength(ans);
		String resp = "Mediante el metodo de ";
		if (metodo.equals("J")) {
			resp += "Jacobi:\n";
		} else if (metodo.equals("GS")) {
			resp += "Gauss Seidel:\n";
		}

		resp += "k"
				+ fillSpaceForPDF(maxLength[0] - 1)
				+ "|"
				+ doArrayTitle(ans[0].getSolution().length)
				+ fillSpaceForPDF(ans[0].getSolution().length
						* (maxLength[1] - 1)) + "|errork\n";
		for (int i = 0; i < ans.length; i++) {
			resp += i + fillSpaceForPDF(maxLength[0] - (i + "").length())
					+ "|[";
			for (int j = 0; j < ans[i].getSolution().length; j++) {
				resp += ans[i].getSolution()[j]
						+ fillSpaceForPDF(maxLength[1]
								- ("" + ans[i].getSolution()[j]).length());
				if (j == ans[i].getSolution().length - 1) {
					resp += "]";
				} else {
					resp += ",";
				}
			}
			resp += "|";
			if (i == ans.length - 1) {
				resp += ans[i].getError();
			} else {
				resp += ans[i].getError() + "\n";
			}
		}

		return resp;
	}

	private static String doArrayTitle(int length) {
		String res = "[";
		for (int i = 0; i < length; i++) {
			int j = i;
			if (j > 25) {
				int ad = j / 26;
				j = j - ad * 26;
			}
			res += (char) ('a' + j);
			if (i == length - 1) {
				res += "]";
			} else {
				res += ",";
			}
		}
		return res;
	}

	public static String respEnString(double[] answer) {
		String resp = "Mediante el metodo de Gauss<br>";
		for (int i = 0; i < ecuaciones - 1; i++) {
			resp += "x" + (i + 1) + " = " + answer[i] + "<br>";
		}
		resp += "x" + (ecuaciones) + " = " + answer[ecuaciones - 1] + "<br>";

		return resp;
	}

	public static String respEnStringForPDF(double[] answer) {
		String resp = "Mediante el metodo de Gauss:\n";
		for (int i = 0; i < ecuaciones - 1; i++) {
			resp += "x" + (i + 1) + " = " + answer[i] + "\n";
		}
		resp += "x" + (ecuaciones) + " = " + answer[ecuaciones - 1];

		return resp;
	}

	public static String allSystemMethods(String k, String vectorX,
			boolean acotar) {
		String resp = "<html>" + MetodoDeGauss.jacobi(k, vectorX, acotar)
				+ "<br>" + MetodoDeGauss.gaussSeidel(k, vectorX, acotar)
				+ "<br>" + respEnString(Gauss.gauss(getEcuMat())) + "</html>";

		return resp;
	}

	public static void allSystemMethodsForPDF(String k, String vectorX,
			boolean acotar, String title) {
		String resp = "Solucion del Sistema\n" + SistemsManager.ecsEnString()
				+ "\n" + MetodoDeGauss.jacobiForPDF(k, vectorX, acotar)
				+ "\n\n" + MetodoDeGauss.gaussSeidelForPDF(k, vectorX, acotar)
				+ "\n\n" + respEnStringForPDF(Gauss.gauss(getEcuMat()));
		PDFCreator.createPdfDoc(title, resp);
	}

	public static void setMetodo(String met) {
		metodo = met;
	}

	public static String getMetodo() {
		return metodo;
	}

	public static void setEcuaciones(int ecu) {
		ecuaciones = ecu;
		ecuacionesMat = new double[ecuaciones][ecuaciones + 1];
	}

	public static int getEcuaciones() {
		return ecuaciones;
	}

	public static double[][] getEcuMat() {
		return ecuacionesMat;
	}

	private static String fillSpace(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum; i++) {
			spaces += "&nbsp;";
		}

		return spaces;
	}

	private static String fillSpaceForPDF(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum; i++) {
			spaces += " ";
		}

		return spaces;
	}

	private static int[] maxLength(Answer[] ans) {
		int[] maxs = new int[2];
		int maxkLength = 0;
		int currentLength = 0;
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].getSolution().length; j++) {
				currentLength = (ans[i].getSolution()[j] + "").length();
				if (currentLength > maxkLength) {
					maxkLength = currentLength;
				}
			}
		}
		maxs[0] = ((ans[0].getSolution().length - 1) + "").length() + 3;
		maxs[1] = maxkLength + 3;
		return maxs;
	}

	// public static void main(String[] args) {
	// setEcuaciones(3);
	// String ecs = "1x1 + 2x2 + 3x3 = 4\n5x1 + 6x2 + 7x3 = 8\n9x1 + 10x2 +
	// 11x3
	// = 12\n";
	// stringEnEcs(ecs);
	// System.out.println(ecsEnString());
	// double[] d = {3.0,5.0,7.0};
	// System.out.println(respEnString(d));
	// }

	// public static void main(String[] args) {
	// double[][] ecuacionesMat2 = { { 9.0, 2.0, -4.0, 13.0 },
	// { 3.0, -8.0, 2.0, 0.0 }, { 1.0, 2.0, -4.0, 0.0 } };
	// metodo = "GS";
	// ecuacionesMat = ecuacionesMat2;
	// ecuaciones = 3;
	// System.out.println(allSystemMethodsForPDF("15", "[0,0,0]", true));
	// }
}
